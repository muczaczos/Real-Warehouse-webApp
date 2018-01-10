package com.muczo.mvc.warehouse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.Activity;
import com.muczo.mvc.warehouse.blueprint.Provider;
import com.muczo.mvc.warehouse.db.CustomersDbUtil;
import com.muczo.mvc.warehouse.db.Documents1DbUtil;
import com.muczo.mvc.warehouse.db.Documents2DbUtil;
import com.muczo.mvc.warehouse.db.EmployeesDbUtil;
import com.muczo.mvc.warehouse.db.PriceDbUtil;
import com.muczo.mvc.warehouse.db.ProductsDbUtil;
import com.muczo.mvc.warehouse.db.ProvidersDbUtil;
import com.muczo.mvc.warehouse.db.ReciepientsDbUtil;
import com.muczo.mvc.warehouse.db.WarehousesDbUtil;
import com.muczo.mvc.warehouse.helperclasses.PrintDocument;

/**
 * Servlet implementation class ProviderControllerServlet
 */
@WebServlet("/ProviderControllerServlet")
public class ProviderControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Documents1DbUtil documents1DbUtil;
	private ProvidersDbUtil providersDbUtil;


	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		// create our warehouse db util ... and pass in the conn pool / datasource
		try {

			documents1DbUtil = new Documents1DbUtil(dataSource);
			providersDbUtil = new ProvidersDbUtil(dataSource);
		
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);

		HttpSession session = request.getSession(false);
		try {
			if (session.getAttribute("userName") != "") {
				System.out.println(session.getAttribute("userName"));
				try {
					// read the "command" parameter
					String theCommand = request.getParameter("command");

					// if the command is missing, then default to listing students
					if (theCommand == null) {
						theCommand = "LIST-PROVIDERS";
					}

					// route to the appropriate method
					switch (theCommand) {

					///////////////////// Providers....///////////////
					case "LIST-PROVIDERS":
						listProviders(request, response);
						break;

					case "ADD-PROVIDER":
						addProvider(request, response);
						break;

					case "LOAD-PROVIDER":
						loadProvider(request, response);
						break;

					case "UPDATE-PROVIDER":
						updateProvider(request, response);
						break;

					case "DELETE-PROVIDER":
						deleteProvider(request, response);
						break;

					default:
						listProviders(request, response);
					}

				} catch (Exception exc) {
					// throw new ServletException(exc);
					System.out.println(exc.toString());
				}

			} else {
				out.print("Proszê siê najpierw zalogowaæ!");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
			dispatcher.forward(request, response);
		}
		out.close();
	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// PROVIDERS ZONE //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	private void listProviders(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// get provider from db util
			List<Provider> providers = providersDbUtil.getProviders();

			// add provider to the request
			request.setAttribute("PROVIDERS_LIST", providers);

			session = request.getSession();
			session.setAttribute("Providers", providers);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-provider.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void addProvider(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read provider info from form data
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String telephone = request.getParameter("telephone");

			// create a new provider object
			Provider theProvider = new Provider(name, address, telephone);

			// add the provider to the database
			providersDbUtil.addProvider(theProvider);

			// write activity to db
			List<Provider> provider = providersDbUtil.getProviders();
			int id = provider.get(provider.size() - 1).getId();

			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"add provider", id);

			// send back to main page (the provider list)
			listProviders(request, response);

		}

	}

	private void loadProvider(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read provider id from form data
			String theProviderId = request.getParameter("providerId");

			// get provider from database (db util)
			Provider theProvider = providersDbUtil.getProvider(theProviderId);

			// place provider in the request attribute
			request.setAttribute("THE_PROVIDER", theProvider);

			// send to jsp page: update-provider-form.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-provider-form.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void updateProvider(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read provider info from form data
			int id = Integer.parseInt(request.getParameter("providerId"));
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String telephone = request.getParameter("telephone");

			// create a new provider object
			Provider theProvider = new Provider(id, name, address, telephone);

			// perform update on database
			providersDbUtil.updateProvider(theProvider);

			// write activity to db
			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"update provider", id);

			// send them back to the "list providers" page
			listProviders(request, response);

		}

	}

	private void deleteProvider(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read provider info from form data
			int id = Integer.parseInt(request.getParameter("providerId"));

			// perform delete on database
			providersDbUtil.deleteProvider(id);

			// write activity to db
			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"del provider", id);

			// send them back to the "list providers" page
			listProviders(request, response);

		}

	}

}
