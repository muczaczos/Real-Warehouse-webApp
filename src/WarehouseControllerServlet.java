


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class WarehouseControllerServlet
 */
@WebServlet("/WarehouseControllerServlet")
public class WarehouseControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private WarehouseDbUtil warehouseDbUtil;
	
	@Resource(name="jdbc/kp")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
	
		// create our warehouse db util ... and pass in the conn pool / datasource
		try {
			warehouseDbUtil = new WarehouseDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST-PRODUCTS";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST-PRODUCTS":
				listProducts(request, response);
				break;
				
			case "LIST-CUSTOMERS":
				listCustomers(request, response);
				break;
				
			case "LIST-RECIEPIENTS":
				listReciepients(request, response);
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
				
			case "DELETE-PRODUCTS":
				deleteProduct(request, response);
				break;
			case "PRINT-DOCUMENT":
				printDocument(request, response);
				break;
							
			default:
				listProducts(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
		
	}

	private void printDocument(HttpServletRequest request, HttpServletResponse response) {

		ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "myfile.txt");
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void listReciepients(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		// get products from db util
		List<Reciepient> reciepients = warehouseDbUtil.getReciepients();
				
		// add products to the request
		request.setAttribute("RECIEPIENTS_LIST", reciepients);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-doc.jsp");
		dispatcher.forward(request, response);
				
		
	}

	private void listCustomers(HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
		
		// get products from db util
		List<Customer> customers = warehouseDbUtil.getCustomers();
		
		// add products to the request
		request.setAttribute("CUSTOMERS_LIST", customers);
		
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-doc.jsp");
		dispatcher.forward(request, response);
		
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void loadProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		request.setAttribute("TODAY_DATE", sdf.format(date).toString());
		
		// get products from db util
		List<Customer> customers = warehouseDbUtil.getCustomers();
				
		// add products to the request
		request.setAttribute("CUSTOMERS_LIST", customers);
				
		// get products from db util
		List<Reciepient> reciepients = warehouseDbUtil.getReciepients();
						
		// add products to the request
		request.setAttribute("RECIEPIENTS_LIST", reciepients);
		
		String warehouse1 = request.getParameter("warehouse1");
		String warehouse2 = request.getParameter("warehouse2");
		String warehouse3 = request.getParameter("warehouse3");
		String warehouse4 = request.getParameter("warehouse4");
		String warehouse5 = request.getParameter("warehouse5");
		String warehouse6 = request.getParameter("warehouse6");
		String warehouse7 = request.getParameter("warehouse7");
	
		// get products from db util
		List<Product> products1 = warehouseDbUtil.getProducts(warehouse1);
		List<Product> products2 = warehouseDbUtil.getProducts(warehouse2);
		List<Product> products3 = warehouseDbUtil.getProducts(warehouse3);
		List<Product> products4 = warehouseDbUtil.getProducts(warehouse4);
		List<Product> products5 = warehouseDbUtil.getProducts(warehouse5);
		List<Product> products6 = warehouseDbUtil.getProducts(warehouse6);
		List<Product> products7 = warehouseDbUtil.getProducts(warehouse7);
				
		// add products to the request
		request.setAttribute("PRODUCT_LIST1", products1);
		request.setAttribute("PRODUCT_LIST2", products2);
		request.setAttribute("PRODUCT_LIST3", products3);
		request.setAttribute("PRODUCT_LIST4", products4);
		request.setAttribute("PRODUCT_LIST5", products5);
		request.setAttribute("PRODUCT_LIST6", products6);
		request.setAttribute("PRODUCT_LIST7", products7);

		//listReciepients(request, response);
		//listCustomers(request, response);
						
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/create-doc.jsp");
		dispatcher.forward(request, response);
	}



}
