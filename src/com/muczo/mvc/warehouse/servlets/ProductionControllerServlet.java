package com.muczo.mvc.warehouse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.muczo.mvc.warehouse.blueprint.Invoice;
import com.muczo.mvc.warehouse.blueprint.PriceList;
import com.muczo.mvc.warehouse.blueprint.Product;
import com.muczo.mvc.warehouse.blueprint.Provider;
import com.muczo.mvc.warehouse.blueprint.Reciepient;
import com.muczo.mvc.warehouse.blueprint.Warehouse;
import com.muczo.mvc.warehouse.db.CustomersDbUtil;
import com.muczo.mvc.warehouse.db.Documents1DbUtil;
import com.muczo.mvc.warehouse.db.Documents2DbUtil;
import com.muczo.mvc.warehouse.db.EmployeesDbUtil;
import com.muczo.mvc.warehouse.db.InvoiceDbUtil;
import com.muczo.mvc.warehouse.db.PriceDbUtil;
import com.muczo.mvc.warehouse.db.ProductsDbUtil;
import com.muczo.mvc.warehouse.db.ProvidersDbUtil;
import com.muczo.mvc.warehouse.db.ReciepientsDbUtil;
import com.muczo.mvc.warehouse.db.WarehousesDbUtil;
import com.muczo.mvc.warehouse.helperclasses.OtherHelpers;
import com.muczo.mvc.warehouse.helperclasses.PrintDocument;

/**
 * Servlet implementation class ProductionControllerServlet
 */
@WebServlet("/ProductionControllerServlet")
public class ProductionControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Documents1DbUtil documents1DbUtil;
	private Documents2DbUtil documents2DbUtil;
	private ProvidersDbUtil providersDbUtil;
	private ProductsDbUtil productsDbUtil;
	private CustomersDbUtil customersDbUtil;
	private ReciepientsDbUtil reciepientsDbUtil;
	private PriceDbUtil priceDbUtil;
	private WarehousesDbUtil warehousesDbUtil;
	private EmployeesDbUtil employeesDbUtil;
	private InvoiceDbUtil invoiceDbUtil;

	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Override
	public void init() throws ServletException {
		super.init();

		// create our warehouse db util ... and pass in the conn pool / datasource
		try {

			documents1DbUtil = new Documents1DbUtil(dataSource);
			documents2DbUtil = new Documents2DbUtil(dataSource);
			providersDbUtil = new ProvidersDbUtil(dataSource);
			productsDbUtil = new ProductsDbUtil(dataSource);
			customersDbUtil = new CustomersDbUtil(dataSource);
			reciepientsDbUtil = new ReciepientsDbUtil(dataSource);
			priceDbUtil = new PriceDbUtil(dataSource);
			warehousesDbUtil = new WarehousesDbUtil(dataSource);
			employeesDbUtil = new EmployeesDbUtil(dataSource);
			invoiceDbUtil = new InvoiceDbUtil(dataSource);
			PrintDocument.dataSource = dataSource;

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
						theCommand = "FIRST-LIST";
					}

					// route to the appropriate method
					switch (theCommand) {

					case "FIRST-LIST":
						firstList(request, response);
						break;

					case "ADD-PRODUCTION":
						production(request, response);
						break;

					default:
						firstList(request, response);
					}

				} catch (Exception exc) {
					// throw new ServletException(exc);
					System.out.println(exc.toString());
				}

			} else {
	
			}
		} catch (Exception e) {
		
		}
		
	}

	private void firstList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// get warehouses from db util
			List<Warehouse> warehouses = warehousesDbUtil.getWarehouses();
			// add producst to the request
			request.setAttribute("WAREHOUSES_LIST", warehouses);

			session.setAttribute("Warehouses", warehouses);

			String warehouse1 = request.getParameter("warehouse1");
			String warehouse2 = request.getParameter("warehouse2");

			// get products from db util
			List<Product> products1 = productsDbUtil.getProducts(warehouse1);
			List<Product> products2 = productsDbUtil.getProducts(warehouse2);

			// add products to the request
			request.setAttribute("PRODUCT_LIST1", products1);
			request.setAttribute("PRODUCT_LIST2", products2);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-production.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void production(HttpServletRequest request, HttpServletResponse response) throws Exception{

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read document info from form data
			String product1 = request.getParameter("product1");
			String product2 = request.getParameter("product2");
			int qty = Integer.parseInt(request.getParameter("qty"));

			OtherHelpers.correctQtyWhenAddDoc(dataSource, OtherHelpers.getProductByName(product1, dataSource), qty);
			OtherHelpers.correctQtyWhenAddDoc2(dataSource, OtherHelpers.getProductByName(product2, dataSource), qty);

			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"production " + product1, qty);

			// send back to main page (the documents list)
			firstList(request, response);
		}

	}

}
