package com.muczo.mvc.warehouse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.muczo.mvc.warehouse.blueprint.Document;
import com.muczo.mvc.warehouse.blueprint.Invoice;
import com.muczo.mvc.warehouse.blueprint.PriceList;
import com.muczo.mvc.warehouse.db.Documents1DbUtil;
import com.muczo.mvc.warehouse.db.InvoiceDbUtil;
import com.muczo.mvc.warehouse.db.PriceDbUtil;
import com.muczo.mvc.warehouse.helperclasses.CalculateInvoice;

/**
 * Servlet implementation class InvoiceControllerServlet
 */
@WebServlet("/InvoiceControllerServlet")
public class InvoiceControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Documents1DbUtil documents1DbUtil;
	private InvoiceDbUtil invoiceDbUtil;
	private PriceDbUtil priceDbUtil;

	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		// create our warehouse db util ... and pass in the conn pool / datasource
		try {

			documents1DbUtil = new Documents1DbUtil(dataSource);
			invoiceDbUtil = new InvoiceDbUtil(dataSource);
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
						theCommand = "LIST-INVOICES";
					}

					// route to the appropriate method
					switch (theCommand) {

					///////////////////// Invoices....//////////////
					case "LIST-INVOICES":
						listInvoices(request, response);
						break;

					case "PRECREATE-INVOICE":
						precreateInvoice(request, response);
						break;

					case "CALCULATE-INVOICE":
						calculateInvoice(request, response);
						break;

					case "ADD-INVOICE":
						addInvoice(request, response);
						break;

					case "LOAD-INVOICE":
						loadInvoice(request, response);
						break;

					case "UPDATE-INVOICE":
						updateInvoice(request, response);
						break;

					case "DELETE-INVOICE":
						deleteInvoice(request, response);
						break;

					default:
						listInvoices(request, response);
					}

				} catch (Exception exc) {
					// throw new ServletException(exc);
					System.out.println(exc.toString());
				}

			} else {
				out.print("Prosz� si� najpierw zalogowa�!");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
			dispatcher.forward(request, response);
		}
		out.close();
	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// INVOICES ZONE ///////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	private void listInvoices(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// get invoice from db util
			List<Invoice> invoices = invoiceDbUtil.getInvoices();

			// add invoices to the request
			request.setAttribute("INVOICE_LIST", invoices);

			session = request.getSession();
			session.setAttribute("Invoices", invoices);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-invoice.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void addInvoice(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {
			
			// read invoice info from form data
			String customer = request.getParameter("customer");
			String date = request.getParameter("date");
			int invNumber = Integer.parseInt(request.getParameter("invNumber"));
			int startDocRange = Integer.parseInt(request.getParameter("startDocRange"));
			int endDocRange = Integer.parseInt(request.getParameter("endDocRange"));
			Double grossAmount = Double.parseDouble(request.getParameter("grossAmount"));

			// create a new invoice object
			Invoice invoice = new Invoice(customer, date, invNumber, startDocRange, endDocRange, grossAmount);

			// add the price to the database
			invoiceDbUtil.addInvoice(invoice);

			// write activity to db
			List<Invoice> invoiceList = invoiceDbUtil.getInvoices();
			int id = invoiceList.get(priceLists.size() - 1).getId();

			session = request.getSession();

			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"add price", id);

			// send back to main page (the reciepient list)
			listPrices(request, response);
		}

	}

	private void loadInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read price id from form data
			String theInvoiceId = request.getParameter("invoiceId");

			// get price from database (db util)
			Invoice invoice = invoiceDbUtil.getInvoice(theInvoiceId);

			// place price in the request attribute
			request.setAttribute("THE_INVOICE", invoice);

			// send to jsp page: update-price-form.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-invoice-form.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void updateInvoice(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

		}

	}

	private void deleteInvoice(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

		}

	}

	private void precreateInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// request.getSession().setAttribute("theLocale",
			// request.getParameter("theLocale"));
			System.out.println(request.getParameter("theLocale"));

			String theCustomer = request.getParameter("invcustomer");

			request.setAttribute("invcustomer", theCustomer);

			// get products from db util
			List<Document> documents = documents1DbUtil.getCustomerDocuments(theCustomer);

			// add product to the request
			request.setAttribute("CUSTOM_DOCUMENTS_LIST", documents);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-invoice.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void calculateInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			String theCustomer = request.getParameter("inv2customer");
			String[] ids = request.getParameterValues("docId");

			String invoice = CalculateInvoice.calculate(ids, theCustomer, priceDbUtil, documents1DbUtil);
			session = request.getSession();
			session.setAttribute("amount", invoice);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-invoice.jsp");
			dispatcher.forward(request, response);
		}

	}

	////////////////////////////////////// ohters //////////////////////////

}
