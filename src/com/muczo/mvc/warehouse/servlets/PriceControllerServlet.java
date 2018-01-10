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
import com.muczo.mvc.warehouse.blueprint.PriceList;
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
 * Servlet implementation class PriceControllerServlet
 */
@WebServlet("/PriceControllerServlet")
public class PriceControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Documents1DbUtil documents1DbUtil;
	private PriceDbUtil priceDbUtil;

	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		// create our warehouse db util ... and pass in the conn pool / datasource
		try {

			documents1DbUtil = new Documents1DbUtil(dataSource);
			priceDbUtil = new PriceDbUtil(dataSource);

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
						theCommand = "LIST-PRICE";
					}

					// route to the appropriate method
					switch (theCommand) {

					///////////////////// Price List....///////////////
					case "LIST-PRICE":
						listPrices(request, response);
						break;

					case "ADD-PRICE":
						addPrice(request, response);
						break;

					case "LOAD-PRICE":
						loadPrice(request, response);
						break;

					case "UPDATE-PRICE":
						updatePrice(request, response);
						break;

					case "DELETE-PRICE":
						deletePrice(request, response);
						break;

					default:
						listPrices(request, response);
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

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// PRICE LIST ZONE /////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	private void listPrices(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// get price from db util
			List<PriceList> prices = priceDbUtil.getPrices();

			// add prices to the request
			request.setAttribute("PRICE_LIST", prices);

			session = request.getSession();
			session.setAttribute("Prices", prices);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-price.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void addPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read price info from form data
			String customer = request.getParameter("customer");
			String product = request.getParameter("product");
			Double price = Double.parseDouble(request.getParameter("price"));

			// create a new price object
			PriceList thePrice = new PriceList(true, customer, product, price);

			// add the price to the database
			priceDbUtil.addPrice(thePrice);

			// write activity to db
			List<PriceList> priceLists = priceDbUtil.getPrices();
			int id = priceLists.get(priceLists.size() - 1).getId();

			session = request.getSession();

			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"add price", id);

			// send back to main page (the reciepient list)
			listPrices(request, response);

		}

	}

	private void loadPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read price id from form data
			String thePriceId = request.getParameter("priceId");

			// get price from database (db util)
			PriceList thePrice = priceDbUtil.getPrice(thePriceId);

			// place price in the request attribute
			request.setAttribute("THE_PRICE", thePrice);

			// send to jsp page: update-price-form.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-price-form.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void updatePrice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read price info from form data
			int id = Integer.parseInt(request.getParameter("priceId"));
			String customer = request.getParameter("customer");
			String product = request.getParameter("product");
			Double price = Double.parseDouble(request.getParameter("price"));

			// create a new price object
			PriceList thePrice = new PriceList(true, id, customer, product, price);

			// perform update on database
			priceDbUtil.updatePrice(thePrice);

			// write activity to db
			session = request.getSession();

			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"update price", id);

			// send them back to the "list price" page
			listPrices(request, response);

		}

	}

	private void deletePrice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read price info from form data
			int id = Integer.parseInt(request.getParameter("priceId"));

			// perform delete on database
			priceDbUtil.deletePrice(id);

			// write activity to db
			session = request.getSession();

			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"del price", id);

			// send them back to the "list price" page
			listPrices(request, response);

		}

	}

	/////////////////////// others /////////////////////////////

}
