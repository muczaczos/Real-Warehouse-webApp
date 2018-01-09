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
import com.muczo.mvc.warehouse.blueprint.Reciepient;
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
 * Servlet implementation class ReciepientControllerServlet
 */
@WebServlet("/ReciepientControllerServlet")
public class ReciepientControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Documents1DbUtil documents1DbUtil;
	private ReciepientsDbUtil reciepientsDbUtil;

	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		// create our warehouse db util ... and pass in the conn pool / datasource
		try {

			documents1DbUtil = new Documents1DbUtil(dataSource);
			reciepientsDbUtil = new ReciepientsDbUtil(dataSource);

		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
						theCommand = "LIST-RECIEPIENTS";
					}

					// route to the appropriate method
					switch (theCommand) {

					///////////////////// Reciepients....///////////////
					case "LIST-RECIEPIENTS":
						listReciepients(request, response);
						break;

					case "ADD-RECIEPIENT":
						addReciepient(request, response);
						break;

					case "LOAD-RECIEPIENT":
						loadReciepient(request, response);
						break;

					case "UPDATE-RECIEPIENT":
						updateReciepient(request, response);
						break;

					case "DELETE-RECIEPIENT":
						deleteReciepient(request, response);
						break;

								default:
									listReciepients(request, response);
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
	////////////////////////// RECIEPIENTS ZONE ////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	private void listReciepients(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// get reciepients from db util
			List<Reciepient> reciepients = reciepientsDbUtil.getReciepients();

			// add reciepients to the request
			request.setAttribute("RECIEPIENTS_LIST", reciepients);

			session = request.getSession();
			session.setAttribute("Reciepients", reciepients);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-reciepient.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void addReciepient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		System.out.println(session.getAttribute("userName").toString());

		if (session.getAttribute("userName") != null) {

			// read reciepient info from form data
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String telephone = request.getParameter("telephone");

			// create a new reciepient object
			Reciepient theReciepient = new Reciepient(name, address, telephone);

			// add the reciepient to the database
			reciepientsDbUtil.addReciepient(theReciepient);

			// write activity to db
			List<Reciepient> reciepients = reciepientsDbUtil.getReciepients();
			int id = reciepients.get(reciepients.size() - 1).getId();

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			try {

				Activity activity = new Activity(session.getAttribute("userName").toString(), "add reciepient",
						dtf.format(now), id);
				documents1DbUtil.monitorActivity(activity);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// send back to main page (the reciepient list)
			listReciepients(request, response);
		}
	}

	private void loadReciepient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read reciepient id from form data
			String theRciepientId = request.getParameter("reciepientId");

			// get reciepient from database (db util)
			Reciepient theReciepient = reciepientsDbUtil.getReciepient(theRciepientId);

			// place reciepient in the request attribute
			request.setAttribute("THE_RECIEPIENT", theReciepient);

			// send to jsp page: update-reciepient-form.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-reciepient-form.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void updateReciepient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read reciepient info from form data
			int id = Integer.parseInt(request.getParameter("customerId"));
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String telephone = request.getParameter("telephone");

			// create a new reciepient object
			Reciepient theReciepient = new Reciepient(id, name, address, telephone);

			// perform update on database
			reciepientsDbUtil.updateReciepient(theReciepient);

			// write activity to db
			session = request.getSession();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			try {
				Activity activity = new Activity(session.getAttribute("userName").toString(), "update reciepient",
						dtf.format(now), id);
				documents1DbUtil.monitorActivity(activity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// send them back to the "list reciepient" page
			listReciepients(request, response);
		}
	}

	private void deleteReciepient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read reciepient info from form data
			int id = Integer.parseInt(request.getParameter("reciepientId"));

			// perform delete on database
			reciepientsDbUtil.deleteReciepient(id);

			// write activity to db
			session = request.getSession();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			try {
				Activity activity = new Activity(session.getAttribute("userName").toString(), "del reciepient",
						dtf.format(now), id);
				documents1DbUtil.monitorActivity(activity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// send them back to the "list reciepient" page
			listReciepients(request, response);
		}
	}
}
