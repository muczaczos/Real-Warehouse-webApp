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
import com.muczo.mvc.warehouse.blueprint.Document2;
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
 * Servlet implementation class Document2ControllerServlet
 */
@WebServlet("/Document2ControllerServlet")
public class Document2ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Documents2DbUtil documents2DbUtil;

	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		// create our warehouse db util ... and pass in the conn pool / datasource
		try {

			documents2DbUtil = new Documents2DbUtil(dataSource);

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
						theCommand = "LIST-DOCUMENTS2";
					}

					// route to the appropriate method
					switch (theCommand) {

					///////////////////// Documents2....//////////////
					case "LIST-DOCUMENTS2":
						listDocuments2(request, response);
						break;

					case "ADD-DOCUMENT2":
						addDocument2(request, response);
						break;

					case "LOAD-DOCUMENT2":
						loadDocument2(request, response);
						break;

					case "UPDATE-DOCUMENT2":
						updateDocument2(request, response);
						break;

					case "DELETE-DOCUMENT2":
						deleteDocument2(request, response);
						break;

					default:
						listDocuments2(request, response);
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
	////////////////////////// DOCUMENTS2 ZONE ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	private void listDocuments2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// get documents from db util
			List<Document2> documents2 = documents2DbUtil.getDocuments2();
			// add documents to the request
			request.setAttribute("DOCUMENTS2_LIST", documents2);

			session.setAttribute("documents2", documents2);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-doc2.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void addDocument2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		int qty1 = 0, qty2 = 0, qty3 = 0, qty4 = 0, qty5 = 0, qty6 = 0, qty7 = 0;
		if (session.getAttribute("userName") != null) {

			// read document2 info from form data
			String provider = request.getParameter("provider");
			String date = request.getParameter("date");
			String documentNumber = request.getParameter("documentNumber");
			String product1 = request.getParameter("product1");
			if (!request.getParameter("qty1").equals("")) {
				qty1 = Integer.parseInt(request.getParameter("qty1"));
			}
			String product2 = request.getParameter("product2");
			if (!request.getParameter("qty2").equals("")) {
				qty2 = Integer.parseInt(request.getParameter("qty2"));
			}
			String product3 = request.getParameter("product3");
			if (!request.getParameter("qty3").equals("")) {
				qty3 = Integer.parseInt(request.getParameter("qty3"));
			}
			String product4 = request.getParameter("product4");
			if (!request.getParameter("qty4").equals("")) {
				qty4 = Integer.parseInt(request.getParameter("qty4"));
			}
			String product5 = request.getParameter("product5");
			if (!request.getParameter("qty5").equals("")) {
				qty5 = Integer.parseInt(request.getParameter("qty5"));
			}
			String product6 = request.getParameter("product6");
			if (!request.getParameter("qty6").equals("")) {
				qty6 = Integer.parseInt(request.getParameter("qty6"));
			}
			String product7 = request.getParameter("product7");
			if (!request.getParameter("qty7").equals("")) {
				qty7 = Integer.parseInt(request.getParameter("qty7"));
			}

			System.out.println("product1: " + product1 );
			
			// create a new price object
			Document2 theDocument2 = new Document2(provider, date, documentNumber, product1, qty1, product2, qty2, product3, qty3, product4,
					qty4, product5, qty5, product6, qty6, product7, qty7);

			System.out.println("product1: " + theDocument2.getProduct1() + " product2: " + theDocument2.getProduct2());
			
			// add the price to the database
			documents2DbUtil.addDocument2(theDocument2);

			// write activity to db
			List<Document2> documents2 = documents2DbUtil.getDocuments2();
			int id = documents2.get(documents2.size() - 1).getId();

			// write activity to db
			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"add doc2", id);

			// send back to main page (the documents2 list)
			// listProducts(request, response);
			listDocuments2(request, response);

		}

	}

	private void loadDocument2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read price id from form data
			String theDocument2id = request.getParameter("doc2Id");

			// get price from database (db util)
			Document2 theDocument2 = documents2DbUtil.getDocument2(theDocument2id);

			// place price in the request attribute
			request.setAttribute("THE_DOCUMENT2", theDocument2);

			// send to jsp page: update-price-form.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-document2-form.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void updateDocument2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read document2 info from form data
			int id = Integer.parseInt(request.getParameter("doc2Id"));
			String provider = request.getParameter("provider");
			String date = request.getParameter("date");
			String documentNumber = request.getParameter("documentNumber");
			String product1 = request.getParameter("product1");
			int qty1 = Integer.parseInt(request.getParameter("qty1"));
			String product2 = request.getParameter("product2");
			int qty2 = Integer.parseInt(request.getParameter("qty2"));
			String product3 = request.getParameter("product3");
			int qty3 = Integer.parseInt(request.getParameter("qty3"));
			String product4 = request.getParameter("product4");
			int qty4 = Integer.parseInt(request.getParameter("qty4"));
			String product5 = request.getParameter("product5");
			int qty5 = Integer.parseInt(request.getParameter("qty5"));
			String product6 = request.getParameter("product6");
			int qty6 = Integer.parseInt(request.getParameter("qty6"));
			String product7 = request.getParameter("product7");
			int qty7 = Integer.parseInt(request.getParameter("qty7"));

			// create a new Document2 object
			Document2 theDocument = new Document2(id, provider, date, documentNumber, product1, qty1, product2, qty2, product3, qty3,
					product4, qty4, product5, qty5, product6, qty6, product7, qty7);

			// perform update on database
			documents2DbUtil.updateDocument2(theDocument);

			// write activity to db
			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"update doc2", id);

			// send them back to the "list documents2" page
			listDocuments2(request, response);

		}

	}

	private void deleteDocument2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read price info from form data
			int id = Integer.parseInt(request.getParameter("doc2Id"));

			// perform delete on database
			documents2DbUtil.deleteDocument2(id);

			// write activity to db
			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"del doc2", id);

			// send them back to the "list price" page
			listDocuments2(request, response);

		}

	}

}
