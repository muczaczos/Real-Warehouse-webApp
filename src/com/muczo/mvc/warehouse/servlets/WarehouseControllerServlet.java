package com.muczo.mvc.warehouse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import com.muczo.mvc.warehouse.blueprint.ProductList;
import com.muczo.mvc.warehouse.blueprint.Provider;
import com.muczo.mvc.warehouse.blueprint.Reciepient;
import com.muczo.mvc.warehouse.blueprint.Warehouse;
import com.muczo.mvc.warehouse.db.CustomersDbUtil;
import com.muczo.mvc.warehouse.db.EmployeesDbUtil;
import com.muczo.mvc.warehouse.db.PriceDbUtil;
import com.muczo.mvc.warehouse.db.ProductsDbUtil;
import com.muczo.mvc.warehouse.db.ProvidersDbUtil;
import com.muczo.mvc.warehouse.db.ReciepientsDbUtil;
import com.muczo.mvc.warehouse.db.WarehouseDbUtil;
import com.muczo.mvc.warehouse.db.WarehousesDbUtil;

/**
 * Servlet implementation class WarehouseControllerServlet
 */
@WebServlet("/WarehouseControllerServlet")
public class WarehouseControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WarehouseDbUtil warehouseDbUtil;
	private ProvidersDbUtil providersDbUtil;
	private ProductsDbUtil productsDbUtil;
	private CustomersDbUtil customersDbUtil;
	private ReciepientsDbUtil reciepientsDbUtil;
	private PriceDbUtil priceDbUtil;
	private WarehousesDbUtil warehousesDbUtil;
	private EmployeesDbUtil employeesDbUtil;

	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		// create our warehouse db util ... and pass in the conn pool / datasource
		try {

			warehouseDbUtil = new WarehouseDbUtil(dataSource);
			providersDbUtil = new ProvidersDbUtil(dataSource);
			productsDbUtil = new ProductsDbUtil(dataSource);
			customersDbUtil = new CustomersDbUtil(dataSource);
			reciepientsDbUtil = new ReciepientsDbUtil(dataSource);
			priceDbUtil = new PriceDbUtil(dataSource);
			warehousesDbUtil = new WarehousesDbUtil(dataSource);
			employeesDbUtil = new EmployeesDbUtil(dataSource);

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
						theCommand = "FIRST-LIST";
					}

					// route to the appropriate method
					switch (theCommand) {

					case "FIRST-LIST":
						firstList(request, response);
						break;

					///////////////////// Documents....//////////////
					case "LIST-DOCUMENTS":
						listDocuments(request, response);
						break;

					case "ADD-DOCUMENT":
						addDocument(request, response);
						break;

					case "LOAD-DOCUMENT":
						loadDocument(request, response);
						break;

					case "UPDATE-DOCUMENT":
						updateDocument(request, response);
						break;

					case "DELETE-DOCUMENT":
						deleteDocument(request, response);
						break;

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

					case "PRINT-DOCUMENT":
						printDocument(request, response);
						break;

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

					///////////////////// Employees List....///////////
					case "LIST-EMPLOYEE":
						listEmployees(request, response);
						break;

					case "ADD-EMPLOYEE":
						addEmployee(request, response);
						break;

					case "LOAD-EMPLOYEE":
						loadEmployee(request, response);
						break;

					case "UPDATE-EMPLOYEE":
						updateEmployee(request, response);
						break;

					case "DELETE-EMPLOYEE":
						deleteEmployee(request, response);
						break;

					///////////////////// Warehouses List....///////////
					case "LIST-WAREHOUSES":
						listWarehouses(request, response);
						break;

					case "ADD-WAREHOUSE":
						addWarehouse(request, response);
						break;

					case "LOAD-WAREHOUSE":
						loadWarehouse(request, response);
						break;

					case "UPDATE-WAREHOUSE":
						updateWarehouse(request, response);
						break;

					case "DELETE-WAREHOUSE":
						deleteWarehouse(request, response);
						break;

					default:
						listProducts(request, response);
					}

				} catch (Exception exc) {
					throw new ServletException(exc);
				}

			} else {
				out.print("Prosz� si� najpierw zalogowa�!");
			}
		} catch (Exception e) {
			out.print("Napewno wpisa�e� has�o?");
			out.print(e.toString());
		}
		out.close();
	}

	private void firstList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		request.setAttribute("TODAY_DATE", sdf.format(date).toString());
		session.setAttribute("Date", sdf.format(date).toString());

		// get documents from db util
		List<Document> documents = warehouseDbUtil.getDocuments();
		// add documents to the request
		request.setAttribute("DOCUMENTS_LIST", documents);

		// get documents from db util
		List<Document2> documents2 = warehouseDbUtil.getDocuments2();
		// add documents to the request
		request.setAttribute("DOCUMENTS2_LIST", documents2);

		session.setAttribute("documents2", documents2);

		// get providers from db util
		List<Provider> providers = providersDbUtil.getProviders();
		// add customers to the request
		request.setAttribute("PROVIDERS_LIST", providers);

		session.setAttribute("Providers", providers);

		// get customers from db util
		List<Customer> customers = customersDbUtil.getCustomers();
		// add customers to the request
		request.setAttribute("CUSTOMERS_LIST", customers);

		session.setAttribute("Customers", customers);

		// get reciepients from db util
		List<Reciepient> reciepients = reciepientsDbUtil.getReciepients();
		// add reciepients to the request
		request.setAttribute("RECIEPIENTS_LIST", reciepients);

		session.setAttribute("Reciepients", reciepients);

		// get products from db util
		List<Product> products = productsDbUtil.getProducts();
		// add producst to the request
		request.setAttribute("PRODUCTS_LIST", products);

		session.setAttribute("Products", products);

		// get products from db util
		List<PriceList> prices = priceDbUtil.getPrices();
		// add producst to the request
		request.setAttribute("PRICES_LIST", prices);

		session.setAttribute("Prices", prices);

		// get employees from db util
		List<Employee> employees = employeesDbUtil.getEmployees();
		// add producst to the request
		request.setAttribute("EMPLOYEES_LIST", employees);

		session.setAttribute("Employees", employees);

		// get warehouses from db util
		List<Warehouse> warehouses = warehousesDbUtil.getWarehouses();
		// add producst to the request
		request.setAttribute("WAREHOUSES_LIST", warehouses);

		session.setAttribute("Warehouses", warehouses);

		String warehouse1 = request.getParameter("warehouse1");
		String warehouse2 = request.getParameter("warehouse2");
		String warehouse3 = request.getParameter("warehouse3");
		String warehouse4 = request.getParameter("warehouse4");
		String warehouse5 = request.getParameter("warehouse5");
		String warehouse6 = request.getParameter("warehouse6");
		String warehouse7 = request.getParameter("warehouse7");

		String preCustomer = request.getParameter("customer");
		int noOfDoc = warehouseDbUtil.nextCustomerDoc(preCustomer);
		session.setAttribute("noOfDoc", noOfDoc);
		session.setAttribute("preCustomer", preCustomer);

		// get products from db util
		List<Product> products1 = productsDbUtil.getProducts(warehouse1);
		List<Product> products2 = productsDbUtil.getProducts(warehouse2);
		List<Product> products3 = productsDbUtil.getProducts(warehouse3);
		List<Product> products4 = productsDbUtil.getProducts(warehouse4);
		List<Product> products5 = productsDbUtil.getProducts(warehouse5);
		List<Product> products6 = productsDbUtil.getProducts(warehouse6);
		List<Product> products7 = productsDbUtil.getProducts(warehouse7);

		// add products to the request
		request.setAttribute("PRODUCT_LIST1", products1);
		request.setAttribute("PRODUCT_LIST2", products2);
		request.setAttribute("PRODUCT_LIST3", products3);
		request.setAttribute("PRODUCT_LIST4", products4);
		request.setAttribute("PRODUCT_LIST5", products5);
		request.setAttribute("PRODUCT_LIST6", products6);
		request.setAttribute("PRODUCT_LIST7", products7);

		// listReciepients(request, response);
		// listCustomers(request, response);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-doc.jsp");
		dispatcher.forward(request, response);
	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// DOCUMENTS ZONE /////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	private void listDocuments(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get products from db util
		List<Document> documents = warehouseDbUtil.getDocuments();

		// add product to the request
		request.setAttribute("DOCUMENTS_LIST", documents);

		HttpSession session = request.getSession();
		session.setAttribute("Documents", documents);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-doc.jsp");
		dispatcher.forward(request, response);

	}

	private void addDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int qty1 = 0, qty2 = 0, qty3 = 0, qty4 = 0, qty5 = 0, qty6 = 0, qty7 = 0;

		// read document info from form data
		String customer = request.getParameter("preCustomer");
		String reciepient = request.getParameter("reciepient");
		int docId = Integer.parseInt(request.getParameter("docId"));

		String date = request.getParameter("date");
		DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
		Date d = format.parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
		String formatDate = formatter.format(d);

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
		String info = request.getParameter("info");

		// create a new document object
		Document theDocument = new Document(true, customer, reciepient, docId, formatDate, product1, qty1, product2,
				qty2, product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7, info);

		// add the document to the database
		warehouseDbUtil.addDocument(theDocument);

		// write activity to db
		List<Document> documents = warehouseDbUtil.getDocuments();
		int id = documents.get(0).getId();

		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "add doc1", dtf.format(now),
					id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// send back to main page (the documents list)
		listDocuments(request, response);

	}

	private void loadDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read document id from form data
		String theDocumentId = request.getParameter("documentId");

		// get document from database (db util)
		Document theDocument = warehouseDbUtil.getDocument(theDocumentId);

		// place document in the request attribute
		request.setAttribute("THE_DOCUMENT", theDocument);

		// send to jsp page: update-document-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-document-form.jsp");
		dispatcher.forward(request, response);

	}

	private void updateDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read document info from form data
		int id = Integer.parseInt(request.getParameter("documentId"));
		String customer = request.getParameter("customer");
		String reciepient = request.getParameter("reciepient");
		int docId = Integer.parseInt(request.getParameter("noOfDoc"));
		String date = request.getParameter("date");
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
		String info = request.getParameter("info");

		// create a new document object
		Document theDocument = new Document(true, id, customer, reciepient, docId, date, product1, qty1, product2, qty2,
				product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7, info);

		// perform update on database
		warehouseDbUtil.updateDocument(theDocument);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "update doc1",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("update dupate");
		// send them back to the "list document" page
		listDocuments(request, response);

	}

	private void deleteDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read document info from form data
		int id = Integer.parseInt(request.getParameter("documentId"));

		// perform delete on database
		warehouseDbUtil.deleteDocument(id);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "del doc1", dtf.format(now),
					id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list document" page
		listDocuments(request, response);

	}

	private void printDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {

		warehouseDbUtil.printDocument(request.getParameter("documentId"));

		// write activity to db
		int id = Integer.parseInt(request.getParameter("documentId"));

		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "print doc1", dtf.format(now),
					id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Process p = Runtime.getRuntime().exec("wscript test.vbs");

	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// DOCUMENTS2 ZONE ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	private void listDocuments2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		// get documents from db util
		List<Document2> documents2 = warehouseDbUtil.getDocuments2();
		// add documents to the request
		request.setAttribute("DOCUMENTS2_LIST", documents2);

		session.setAttribute("documents2", documents2);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-doc2.jsp");
		dispatcher.forward(request, response);

	}

	private void addDocument2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read document2 info from form data
		String provider = request.getParameter("provider");
		String product = request.getParameter("product");
		int qty = Integer.parseInt(request.getParameter("qty"));

		// create a new price object
		Document2 theDocument2 = new Document2(provider, product, qty);

		// add the price to the database
		warehouseDbUtil.addDocument2(theDocument2);

		// write activity to db
		List<Document2> documents2 = warehouseDbUtil.getDocuments2();
		int id = documents2.get(documents2.size() - 1).getId();

		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "add doc2", dtf.format(now),
					id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send back to main page (the documents2 list)
		listProducts(request, response);
		listDocuments2(request, response);
		

	}

	private void loadDocument2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read price id from form data
		String theDocument2id = request.getParameter("doc2Id");

		// get price from database (db util)
		Document2 theDocument2 = warehouseDbUtil.getDocument2(theDocument2id);

		// place price in the request attribute
		request.setAttribute("THE_DOCUMENT2", theDocument2);

		// send to jsp page: update-price-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-document2-form.jsp");
		dispatcher.forward(request, response);

	}

	private void updateDocument2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read document2 info from form data
		int id = Integer.parseInt(request.getParameter("doc2Id"));
		String provider = request.getParameter("provider");
		String product = request.getParameter("product");
		int qty = Integer.parseInt(request.getParameter("qty"));

		// create a new price object
		Document2 theDocument = new Document2(id, provider, product, qty);

		// perform update on database
		warehouseDbUtil.updateDocument2(theDocument);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "update doc1",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list price" page
		listDocuments2(request, response);

	}

	private void deleteDocument2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read price info from form data
		int id = Integer.parseInt(request.getParameter("doc2Id"));

		// perform delete on database
		warehouseDbUtil.deleteDocument2(id);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "del doc1", dtf.format(now),
					id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list price" page
		listDocuments2(request, response);

	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// INVOICES ZONE ///////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////

	private void deleteInvoice(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void updateInvoice(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void loadInvoice(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void addInvoice(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	private void calculateInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ArrayList<String> productList = new ArrayList<String>();
		ArrayList<String> product = new ArrayList<String>();
		ArrayList<Integer> qty = new ArrayList<Integer>();
		ArrayList<ProductList> sumList = new ArrayList<ProductList>();
		String theCustomer = request.getParameter("inv2customer");
		String grossAmount;

		String[] ids = request.getParameterValues("docId");
		for (String id : ids) {

			Document document = warehouseDbUtil.getDocument(id);

			product.add(document.getProduct1());
			qty.add(document.getQty1());

			if (document.getQty2() > 0) {
				product.add(document.getProduct2());
				qty.add(document.getQty2());

				if (document.getQty3() > 0) {
					product.add(document.getProduct3());
					qty.add(document.getQty3());

					if (document.getQty4() > 0) {
						product.add(document.getProduct4());
						qty.add(document.getQty4());

						if (document.getQty5() > 0) {
							product.add(document.getProduct5());
							qty.add(document.getQty5());

							if (document.getQty6() > 0) {
								product.add(document.getProduct6());
								qty.add(document.getQty6());

								if (document.getQty7() > 0) {
									product.add(document.getProduct7());
									qty.add(document.getQty7());
								}
							}
						}
					}
				}
			}

		}

		for (int j = 0; j < product.size(); j++) {

			if (!productList.contains(product.get(j))) {

				for (int k = j; k < product.size() - 1; k++) {

					if (product.get(j).equals(product.get(k + 1))) {

						int suma = qty.get(j) + qty.get(k + 1);
						qty.set(j, suma);
					}
				}
				productList.add(product.get(j));
				sumList.add(new ProductList(product.get(j), qty.get(j)));
			}

		}
		StringBuilder sb = new StringBuilder();
		Double sum = 0.00;

		for (ProductList element : sumList) {

			sb.append(element.getProduct() + "    szt." + element.getQty() + "     "
					+ priceDbUtil.loadPriceForCustomer(element.getProduct(), theCustomer) + "z�" + "\n");
			sb.append(element.getQty() * priceDbUtil.loadPriceForCustomer(element.getProduct(), theCustomer));
			sum += element.getQty() * priceDbUtil.loadPriceForCustomer(element.getProduct(), theCustomer);

		}

		sum *= 1.23;

		// Rounding number to up and two digits after dot.
		NumberFormat fmt = NumberFormat.getNumberInstance();
		fmt.setMaximumFractionDigits(2);
		fmt.setRoundingMode(RoundingMode.CEILING);
		sb.append("\n Gross amount: " + fmt.format(sum));
		grossAmount = fmt.format(sum).toString();

		// Convert from NumberFormat to Double

		try {
			Double myNumber = 0.00;
			myNumber = fmt.parse(grossAmount).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String invoice = sb.toString();
		HttpSession session = request.getSession();
		session.setAttribute("amount", invoice);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-invoice.jsp");
		dispatcher.forward(request, response);

	}

	private void precreateInvoice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// request.getSession().setAttribute("theLocale",
		// request.getParameter("theLocale"));
		System.out.println(request.getParameter("theLocale"));

		String theCustomer = request.getParameter("invcustomer");

		request.setAttribute("invcustomer", theCustomer);

		// get products from db util
		List<Document> documents = warehouseDbUtil.getCustomerDocuments(theCustomer);

		// add product to the request
		request.setAttribute("CUSTOM_DOCUMENTS_LIST", documents);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-invoice.jsp");
		dispatcher.forward(request, response);

	}

	private void listInvoices(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// PRODUCTS ZONE //////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get products from db util
		List<Product> products = productsDbUtil.getProducts();

		// add product to the request
		request.setAttribute("PRODUCTS_LIST", products);

		HttpSession session = request.getSession();
		session.setAttribute("Products", products);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-product.jsp");
		dispatcher.forward(request, response);

	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "add product",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send back to main page (the product list)
		listProducts(request, response);

	}

	private void loadProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read product info from form data
		int id = Integer.parseInt(request.getParameter("productId"));
		String name = request.getParameter("productName");
		String warehouse = request.getParameter("warehouse");

		// create a new product object
		Product theProduct = new Product(id, name, warehouse, 0);

		// perform update on database
		productsDbUtil.updateProduct(theProduct);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "update product",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list customers" page
		listProducts(request, response);

	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read product info from form data
		int id = Integer.parseInt(request.getParameter("productId"));

		// perform delete on database
		productsDbUtil.deleteProduct(id);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "del product",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list product" page
		listProducts(request, response);

	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// PROVIDERS ZONE //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	private void listProviders(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get provider from db util
		List<Provider> providers = providersDbUtil.getProviders();

		// add provider to the request
		request.setAttribute("PROVIDERS_LIST", providers);

		HttpSession session = request.getSession();
		session.setAttribute("Providers", providers);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-provider.jsp");
		dispatcher.forward(request, response);

	}

	private void addProvider(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "add provider",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send back to main page (the provider list)
		listProviders(request, response);

	}

	private void loadProvider(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

	private void updateProvider(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "update provider",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list providers" page
		listProviders(request, response);

	}

	private void deleteProvider(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read provider info from form data
		int id = Integer.parseInt(request.getParameter("providerId"));

		// perform delete on database
		providersDbUtil.deleteProvider(id);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "del provider",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list providers" page
		listProviders(request, response);

	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// CUSTOMERS ZONE //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	private void listCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get customer from db util
		List<Customer> customers = customersDbUtil.getCustomers();

		// add customer to the request
		request.setAttribute("CUSTOMERS_LIST", customers);

		HttpSession session = request.getSession();
		session.setAttribute("Customers", customers);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-customer.jsp");
		dispatcher.forward(request, response);

	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "add customer",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send back to main page (the customer list)
		listCustomers(request, response);

	}

	private void loadCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

	private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read customer info from form data
		int id = Integer.parseInt(request.getParameter("customerId"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");

		// create a new customer object
		Customer theCustomer = new Customer(id, name, address, telephone);

		// perform update on database
		customersDbUtil.updateCustomer(theCustomer);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "update customer",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list customers" page
		listCustomers(request, response);

	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read customer info from form data
		int id = Integer.parseInt(request.getParameter("customerId"));

		// perform delete on database
		customersDbUtil.deleteCustomer(id);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "delete customer",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list customers" page
		listCustomers(request, response);

	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// RECIEPIENTS ZONE ////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	private void listReciepients(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get reciepients from db util
		List<Reciepient> reciepients = reciepientsDbUtil.getReciepients();

		// add reciepients to the request
		request.setAttribute("RECIEPIENTS_LIST", reciepients);

		HttpSession session = request.getSession();
		session.setAttribute("Reciepients", reciepients);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-reciepient.jsp");
		dispatcher.forward(request, response);

	}

	private void addReciepient(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "add reciepient",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send back to main page (the reciepient list)
		listReciepients(request, response);

	}

	private void loadReciepient(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

	private void updateReciepient(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "update reciepient",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list reciepient" page
		listReciepients(request, response);

	}

	private void deleteReciepient(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read reciepient info from form data
		int id = Integer.parseInt(request.getParameter("reciepientId"));

		// perform delete on database
		reciepientsDbUtil.deleteReciepient(id);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "del reciepient",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list reciepient" page
		listReciepients(request, response);

	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// PRICE LIST ZONE /////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	private void listPrices(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get price from db util
		List<PriceList> prices = priceDbUtil.getPrices();

		// add prices to the request
		request.setAttribute("PRICE_LIST", prices);

		HttpSession session = request.getSession();
		session.setAttribute("Prices", prices);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-price.jsp");
		dispatcher.forward(request, response);

	}

	private void addPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "add price", dtf.format(now),
					id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send back to main page (the reciepient list)
		listPrices(request, response);

	}

	private void loadPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {

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

	private void updatePrice(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "update price",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list price" page
		listPrices(request, response);

	}

	private void deletePrice(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read price info from form data
		int id = Integer.parseInt(request.getParameter("priceId"));

		// perform delete on database
		priceDbUtil.deletePrice(id);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "del price", dtf.format(now),
					id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list price" page
		listPrices(request, response);

	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// EMPLOYEES ZONE //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get employee from db util
		List<Employee> employees = employeesDbUtil.getEmployees();

		// add employee to the request
		request.setAttribute("EMPLOYEE_LIST", employees);

		HttpSession session = request.getSession();
		session.setAttribute("Employees", employees);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-employees.jsp");
		dispatcher.forward(request, response);

	}

	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read employee info from form data
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		String profession = request.getParameter("profession");
		String safetyTraining = request.getParameter("safetyTraining");
		String medicalVisit = request.getParameter("medicalVisit");
		String contractDate = request.getParameter("contractDate");

		// create a new employee object
		Employee theEmployee = new Employee(name, surname, address, telephone, profession, safetyTraining, medicalVisit,
				contractDate);

		// add the employee to the database
		employeesDbUtil.addEmployee(theEmployee);

		// write activity to db
		List<Employee> employees = employeesDbUtil.getEmployees();
		int id = employees.get(employees.size() - 1).getId();

		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "add employee",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send back to main page (the employee list)
		listEmployees(request, response);

	}

	private void loadEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read employee id from form data
		String theEmployeeId = request.getParameter("employeeId");

		// get employee from database (db util)
		Employee theEmployee = employeesDbUtil.getEmployee(theEmployeeId);

		// place employee in the request attribute
		request.setAttribute("THE_EMPLOYEE", theEmployee);

		// send to jsp page: update-employee-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-employee-form.jsp");
		dispatcher.forward(request, response);

	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read employee info from form data
		int id = Integer.parseInt(request.getParameter("employeeId"));
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		String profession = request.getParameter("profession");
		String safetyTraining = request.getParameter("safetyTraining");
		String medicalVisit = request.getParameter("medicalVisit");
		String contractDate = request.getParameter("contractDate");

		// create a new employee object
		Employee employee = new Employee(id, name, surname, address, telephone, profession, safetyTraining,
				medicalVisit, contractDate);

		// perform update on database
		employeesDbUtil.updateEmployee(employee);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "update employee",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list employees" page
		listEmployees(request, response);

	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read employee info from form data
		int id = Integer.parseInt(request.getParameter("employeeId"));

		// perform delete on database
		employeesDbUtil.deleteEmployee(id);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "del employee",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list price" page
		listEmployees(request, response);

	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// WAREHOUSES ZONE /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	private void listWarehouses(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// get employee from db util
		List<Warehouse> warehouses = warehousesDbUtil.getWarehouses();

		// add employee to the request
		request.setAttribute("WAREHOUSES_LIST", warehouses);

		HttpSession session = request.getSession();
		session.setAttribute("Warehouses", warehouses);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-warehouse.jsp");
		dispatcher.forward(request, response);

	}

	private void addWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read warehouses info from form data
		String name = request.getParameter("name");

		// create a new employee object
		Warehouse theWarehouse = new Warehouse(name);

		// add the employee to the database
		warehousesDbUtil.addWarehouse(theWarehouse);

		// write activity to db
		List<Warehouse> warehouses = warehousesDbUtil.getWarehouses();
		int id = warehouses.get(warehouses.size() - 1).getId();

		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "add warehouse",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send back to main page (the employee list)
		listWarehouses(request, response);

	}

	private void loadWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read warehouse id from form data
		String theWarehouseId = request.getParameter("warehouseId");

		// get wearehouse from database (db util)
		Warehouse theWarehouse = warehousesDbUtil.getWarehouse(theWarehouseId);

		// place employee in the request attribute
		request.setAttribute("THE_WAREHOUSE", theWarehouse);

		// send to jsp page: update-employee-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-warehouse-form.jsp");
		dispatcher.forward(request, response);

	}

	private void updateWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read warehouse info from form data
		int id = Integer.parseInt(request.getParameter("warehouseId"));
		String name = request.getParameter("name");

		// create a new employee object
		Warehouse warehouse = new Warehouse(id, name);

		// perform update on database
		warehousesDbUtil.updateWarehouse(warehouse);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "update warehouse",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list employees" page
		listWarehouses(request, response);

	}

	private void deleteWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read employee info from form data
		int id = Integer.parseInt(request.getParameter("warehouseId"));

		// perform delete on database
		warehousesDbUtil.deleteWarehouse(id);

		// write activity to db
		HttpSession session = request.getSession();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		try {
			Activity activity = new Activity(session.getAttribute("userName").toString(), "del warehouse",
					dtf.format(now), id);
			warehouseDbUtil.monitorActivity(activity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send them back to the "list price" page
		listWarehouses(request, response);

	}

	////////////////////////////////////// ohters //////////////////////////

}