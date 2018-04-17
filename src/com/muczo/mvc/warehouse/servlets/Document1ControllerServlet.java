package com.muczo.mvc.warehouse.servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import com.muczo.mvc.warehouse.helperclasses.PrintDocument;

/**
 * Servlet implementation class WarehouseControllerServlet
 */
@WebServlet("/Document1ControllerServlet")
public class Document1ControllerServlet extends HttpServlet {
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

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// COMMANDS ZONE //////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		//remember!
		//PrintWriter out = response.getWriter();
		
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

					case "PRINT-DOCUMENT":
						printDocument(request, response);
						break;
						
					case "SEARCH-DOCUMENT-BY-CUSTOMER":
						searchDocumentByCustomer(request, response);
						break;
						
					case "SEARCH-DOCUMENT-BY-RECIEPIENT":
						searchDocumentByReciepient(request, response);
						break;
				
					default:
						firstList(request, response);
					}

				} catch (Exception exc) {
					// throw new ServletException(exc);
					System.out.println(exc.toString());
				}

			} else {
				
				//remember!
				//out.print("Proszê siê najpierw zalogowaæ!");
			}
		} catch (Exception e) {
		
		}
		
		//remember!
	//	out.close();
	}


	

	private void firstList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
			request.setAttribute("TODAY_DATE", sdf.format(date).toString());
			session.setAttribute("Date", sdf.format(date).toString());

			// get documents from db util
			List<Document> documents = documents1DbUtil.getDocuments();
			// add documents to the request
			request.setAttribute("DOCUMENTS_LIST", documents);

			// get documents from db util
			List<Document2> documents2 = documents2DbUtil.getDocuments2();
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

			// get invoices from db util
			List<Invoice> invoices = invoiceDbUtil.getInvoices();
			// add invoices to the request
			request.setAttribute("INVOICES_LIST", invoices);

			session.setAttribute("Invoices", customers);

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
			int noOfDoc = documents1DbUtil.nextCustomerDoc(preCustomer);
			System.out.println(noOfDoc);
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
	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// DOCUMENTS ZONE /////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	private void listDocuments(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {
			// get products from db util
			List<Document> documents = documents1DbUtil.getDocuments();

			// add product to the request
			request.setAttribute("DOCUMENTS_LIST", documents);

			session.setAttribute("Documents", documents);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/WarehouseControllerServlet?command=FirstList");
			dispatcher.forward(request, response);
		}

	}

	private void addDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {
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
			String info1 = request.getParameter("info1");
			String info2 = request.getParameter("info2");
			String info3 = request.getParameter("info3");
			String info4 = request.getParameter("info4");
			String info5 = request.getParameter("info5");
			String info6 = request.getParameter("info6");
			String info7 = request.getParameter("info7");

			// create a new document object
			Document theDocument = new Document(true, customer, reciepient, docId, formatDate, product1, qty1, product2,
					qty2, product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7, info1, 
					info2, info3, info4, info5, info6, info7);

			// add the document to the database
			documents1DbUtil.addDocument(theDocument);

			// write activity to db
			List<Document> documents = documents1DbUtil.getDocuments();
			int id = documents.get(0).getId();

			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"add doc1", id);

			// send back to main page (the documents list)
			firstList(request, response);
		}

	}

	private void loadDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {
			// read document id from form data
			String theDocumentId = request.getParameter("documentId");

			// get document from database (db util)
			Document theDocument = documents1DbUtil.getDocument(theDocumentId);

			// place document in the request attribute
			request.setAttribute("THE_DOCUMENT", theDocument);

			// send to jsp page: update-document-form.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update-document-form.jsp");
			dispatcher.forward(request, response);
		}

	}

	private void updateDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {
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
			String info1 = request.getParameter("info1");
			String info2 = request.getParameter("info2");
			String info3 = request.getParameter("info3");
			String info4 = request.getParameter("info4");
			String info5 = request.getParameter("info5");
			String info6 = request.getParameter("info6");
			String info7 = request.getParameter("info7");

			// create a new document object
			Document theDocument = new Document(true, id, customer, reciepient, docId, date, product1, qty1, product2,
					qty2, product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7, info1, 
					info2, info3, info4, info5, info6, info7);

			// perform update on database
			documents1DbUtil.updateDocument(theDocument);

			// write activity to db
			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
				"update doc1", id);

			firstList(request, response);

		}
	}

	private void deleteDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {
			// read document info from form data
			int id = Integer.parseInt(request.getParameter("documentId"));

			// perform delete on database
			documents1DbUtil.deleteDocument(id);

			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"del doc1", id);

			// send back to main page (the documents list)
			firstList(request, response);
		}

	}

	private void printDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			PrintDocument.printDocument(dataSource, request.getParameter("documentId"), request);

			// write activity to db
			int id = Integer.parseInt(request.getParameter("documentId"));

			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"print doc1", id);

			// Process p = Runtime.getRuntime().exec("wscript test.vbs");

			// send back to main page (the documents list)
		//	firstList(request, response);
			
			//remember! 
			RequestDispatcher rd = request.getRequestDispatcher("DownloadControllerServlet");
			rd.forward(request,response);
		}

	}
	

	private void searchDocumentByCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception{

		HttpSession session = request.getSession();
		
		if (session.getAttribute("userName") != null) {
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
			request.setAttribute("TODAY_DATE", sdf.format(date).toString());
			session.setAttribute("Date", sdf.format(date).toString());
			
			// get customers from db util
			List<Customer> customers = customersDbUtil.getCustomers();
			// add customers to the request
			request.setAttribute("CUSTOMERS_LIST", customers);
			
			// get reciepients from db util
			List<Reciepient> reciepients = reciepientsDbUtil.getReciepients();
			// add reciepients to the request
			request.setAttribute("RECIEPIENTS_LIST", reciepients);

			// get products from db util
			List<Product> products = productsDbUtil.getProducts();
			// add producst to the request
			request.setAttribute("PRODUCTS_LIST", products);

			// get documents from db util
			String customer = request.getParameter("customer");
			List<Document> documents = documents1DbUtil.getCustomerDocuments(customer);
			// add documents to the request
			request.setAttribute("DOCUMENTS_LIST", documents);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-doc.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	private void searchDocumentByReciepient(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
			if (session.getAttribute("userName") != null) {
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
			request.setAttribute("TODAY_DATE", sdf.format(date).toString());
			session.setAttribute("Date", sdf.format(date).toString());
			
			// get customers from db util
			List<Customer> customers = customersDbUtil.getCustomers();
			// add customers to the request
			request.setAttribute("CUSTOMERS_LIST", customers);
			
			// get reciepients from db util
			List<Reciepient> reciepients = reciepientsDbUtil.getReciepients();
			// add reciepients to the request
			request.setAttribute("RECIEPIENTS_LIST", reciepients);

			// get products from db util
			List<Product> products = productsDbUtil.getProducts();
			// add producst to the request
			request.setAttribute("PRODUCTS_LIST", products);

			// get documents from db util
			String reciepient = request.getParameter("reciepient");
			List<Document> documents = documents1DbUtil.getReciepientsDocuments(reciepient);
			// add documents to the request
			request.setAttribute("DOCUMENTS_LIST", documents);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-doc.jsp");
			dispatcher.forward(request, response);
		}
		
	}


}
