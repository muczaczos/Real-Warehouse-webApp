package com.muczo.mvc.warehouse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.muczo.mvc.warehouse.blueprint.Customer;
import com.muczo.mvc.warehouse.blueprint.Document;
import com.muczo.mvc.warehouse.blueprint.Document2;
import com.muczo.mvc.warehouse.blueprint.Employee;
import com.muczo.mvc.warehouse.blueprint.PriceList;
import com.muczo.mvc.warehouse.blueprint.Product;
import com.muczo.mvc.warehouse.blueprint.Provider;
import com.muczo.mvc.warehouse.blueprint.Reciepient;
import com.muczo.mvc.warehouse.blueprint.Warehouse;
import com.muczo.mvc.warehouse.db.CustomersDbUtil;
import com.muczo.mvc.warehouse.db.Documents1DbUtil;
import com.muczo.mvc.warehouse.db.Documents2DbUtil;
import com.muczo.mvc.warehouse.db.EmployeesDbUtil;
import com.muczo.mvc.warehouse.db.PriceDbUtil;
import com.muczo.mvc.warehouse.db.ProductsDbUtil;
import com.muczo.mvc.warehouse.db.ProvidersDbUtil;
import com.muczo.mvc.warehouse.db.ReciepientsDbUtil;
import com.muczo.mvc.warehouse.db.WarehousesDbUtil;
import com.muczo.mvc.warehouse.helperclasses.CalculateInvoice;
import com.muczo.mvc.warehouse.helperclasses.PrintDocument;

/**
 * Servlet implementation class CustomerControllerServlet
 */
@WebServlet("/CustomerControllerServlet")
public class CustomerControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Documents1DbUtil documents1DbUtil;
	private CustomersDbUtil customersDbUtil;

	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		// create our warehouse db util ... and pass in the conn pool / datasource
		try {

			customersDbUtil = new CustomersDbUtil(dataSource);
			documents1DbUtil = new Documents1DbUtil(dataSource);

		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// COMMANDS ZONE //////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("link.html").include(request, response);

		HttpSession session = request.getSession(false);
		try {
			if (session.getAttribute("userName") != null) {

				try {
					// read the "command" parameter
					String theCommand = request.getParameter("command");

					// if the command is missing, then default to listing students
					if (theCommand == null) {
						theCommand = "LIST-CUSTOMERS";
					}

					// route to the appropriate method
					switch (theCommand) {

					///////////////////// Customers....///////////////
					case "LIST-CUSTOMERS":
						listCustomers(request, response);
						break;

					case "ADD-CUSTOMER":
						addCustomer(request, response);
						break;

					case "LOAD-CUSTOMER":
						loadCustomer(request, response);
						break;

					case "UPDATE-CUSTOMER":
						updateCustomer(request, response);
						break;

					case "DELETE-CUSTOMER":
						deleteCustomer(request, response);
						break;

					default:
						listCustomers(request, response);
					}

				} catch (Exception exc) {
					throw new ServletException(exc);
				}

			} else {
				out.print("Proszê siê najpierw zalogowaæ!");
			}
		} catch (Exception e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
			dispatcher.forward(request, response);
		}
		out.close();
	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// CUSTOMERS ZONE //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	private void listCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// get customer from db util
			List<Customer> customers = customersDbUtil.getCustomers();

			// add customer to the request
			request.setAttribute("CUSTOMERS_LIST", customers);

			session = request.getSession();
			session.setAttribute("Customers", customers);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-customer.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read customer info from form data
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String telephone = request.getParameter("telephone");

			// create a new customer object
			Customer theCustomer = new Customer(name, address, telephone);

			// add the customer to the database
			customersDbUtil.addCustomer(theCustomer);

			// write activity to db
			List<Customer> customer = customersDbUtil.getCustomers();
			int id = customer.get(customer.size() - 1).getId();

			try {
				session = request.getSession();
				Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
						"add customer", id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// send back to main page (the customer list)
			listCustomers(request, response);
		}
	}

	private void loadCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read customer id from form data
			String theCustomerId = request.getParameter("customerId");

			// get customer from database (db util)
			Customer theCusdtomer = customersDbUtil.getCustomer(theCustomerId);

			// place student in the request attribute
			request.setAttribute("THE_CUSTOMER", theCusdtomer);

			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-customer-form.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read customer info from form data
			int id = Integer.parseInt(request.getParameter("customerId"));
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String telephone = request.getParameter("telephone");

			// create a new customer object
			Customer theCustomer = new Customer(id, name, address, telephone);

			// perform update on database
			customersDbUtil.updateCustomer(theCustomer);

			try {

				// write activity to db
				session = request.getSession();
				Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
						"update customer", id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// send them back to the "list customers" page
			listCustomers(request, response);
		}
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read customer info from form data
			int id = Integer.parseInt(request.getParameter("customerId"));

			// perform delete on database
			customersDbUtil.deleteCustomer(id);

			try {
				// write activity to db
				session = request.getSession();
				Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
						"del customer", id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// send them back to the "list customers" page
			listCustomers(request, response);
		}

	}

}
