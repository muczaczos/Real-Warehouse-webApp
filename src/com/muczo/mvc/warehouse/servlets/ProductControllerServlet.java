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
import com.muczo.mvc.warehouse.helperclasses.PrintDocument;

/**
 * Servlet implementation class ProductControllerServlet
 */
@WebServlet("/ProductControllerServlet")
public class ProductControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Documents1DbUtil documents1DbUtil;
	private ProductsDbUtil productsDbUtil;

	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		// create our warehouse db util ... and pass in the conn pool / datasource
		try {

			documents1DbUtil = new Documents1DbUtil(dataSource);
			productsDbUtil = new ProductsDbUtil(dataSource);
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
						theCommand = "LIST-PRODUCTS";
					}

					// route to the appropriate method
					switch (theCommand) {

					///////////////////// Products....///////////////
					case "LIST-PRODUCTS":
						listProducts(request, response);
						break;

					case "ADD-PRODUCT":
						addProduct(request, response);
						break;

					case "LOAD-PRODUCT":
						loadProduct(request, response);
						break;

					case "UPDATE-PRODUCT":
						updateProduct(request, response);
						break;

					case "DELETE-PRODUCT":
						deleteProduct(request, response);
						break;

					default:
						listProducts(request, response);
					}

				} catch (Exception exc) {
					// throw new ServletException(exc);
					System.out.println(exc.toString());
				}

			} else {
				out.print("Proszê siê najpierw zalogowaæ!");
			}
		} catch (Exception e) {
			System.out.println("dupinka");
			
		//	RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
		//	dispatcher.forward(request, response);
		}
		out.close();
	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// PRODUCTS ZONE //////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// get products from db util
			List<Product> products = productsDbUtil.getProducts();

			// add product to the request
			request.setAttribute("PRODUCTS_LIST", products);

			session = request.getSession();
			session.setAttribute("Products", products);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-product.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read product info from form data
			String name = request.getParameter("productName");
			String warehouse = request.getParameter("warehouse");

			// create a new product object
			Product theProduct = new Product(name, warehouse, 0);

			// add the product to the database
			productsDbUtil.addProduct(theProduct);

			// write activity to db
			List<Product> products = productsDbUtil.getProducts();
			int id = products.get(products.size() - 1).getId();

			session = request.getSession();
			try {
				Activity.monitorSpecificActivity(session, request, dataSource,
						session.getAttribute("userName").toString(), "add product", id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// send back to main page (the product list)
			listProducts(request, response);

		}

	}

	private void loadProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read product id from form data
			String theProductId = request.getParameter("productId");

			// get product from database (db util)
			Product theProduct = productsDbUtil.getProduct(theProductId);

			// place product in the request attribute
			request.setAttribute("THE_PRODUCT", theProduct);

			// send to jsp page: update-product-form.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-product-form.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read product info from form data
			int id = Integer.parseInt(request.getParameter("productId"));
			String name = request.getParameter("productName");
			String warehouse = request.getParameter("warehouse");

			// create a new product object
			Product theProduct = new Product(id, name, warehouse, 0);

			// perform update on database
			productsDbUtil.updateProduct(theProduct);

			// write activity to db
			session = request.getSession();
			try {
				Activity.monitorSpecificActivity(session, request, dataSource,
						session.getAttribute("userName").toString(), "update product", id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// send them back to the "list customers" page
			listProducts(request, response);

		}

	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read product info from form data
			int id = Integer.parseInt(request.getParameter("productId"));

			// perform delete on database
			productsDbUtil.deleteProduct(id);

			// write activity to db
			session = request.getSession();

			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"del product", id);

			// send them back to the "list product" page
			listProducts(request, response);

		}

	}

	////////////////////////////////////// ohters //////////////////////////

}
