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
 * Servlet implementation class WarehouseControllerServlet
 */
@WebServlet("/WarehouseControllerServlet")
public class WarehouseControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Documents1DbUtil documents1DbUtil;
	private WarehousesDbUtil warehousesDbUtil;

	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		// create our warehouse db util ... and pass in the conn pool / datasource
		try {

			documents1DbUtil = new Documents1DbUtil(dataSource);
			warehousesDbUtil = new WarehousesDbUtil(dataSource);
	
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
						theCommand = "LIST-WAREHOUSES";
					}

					// route to the appropriate method
					switch (theCommand) {

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
						listWarehouses(request, response);
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
	////////////////////////// WAREHOUSES ZONE /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	private void listWarehouses(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// get employee from db util
			List<Warehouse> warehouses = warehousesDbUtil.getWarehouses();

			// add employee to the request
			request.setAttribute("WAREHOUSES_LIST", warehouses);

			session = request.getSession();
			session.setAttribute("Warehouses", warehouses);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-warehouse.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void addWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read warehouses info from form data
			String name = request.getParameter("name");

			// create a new employee object
			Warehouse theWarehouse = new Warehouse(name);

			// add the employee to the database
			warehousesDbUtil.addWarehouse(theWarehouse);

			// write activity to db
			List<Warehouse> warehouses = warehousesDbUtil.getWarehouses();
			int id = warehouses.get(warehouses.size() - 1).getId();
			session = request.getSession();
			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"add warehouse", id);
			
			// send back to main page (the employee list)
			listWarehouses(request, response);

		}

	}

	private void loadWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

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

	}

	private void updateWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read warehouse info from form data
			int id = Integer.parseInt(request.getParameter("warehouseId"));
			String name = request.getParameter("name");

			// create a new employee object
			Warehouse warehouse = new Warehouse(id, name);

			// perform update on database
			warehousesDbUtil.updateWarehouse(warehouse);

			// write activity to db
			session = request.getSession();
			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"update warehouse", id);

			// send them back to the "list employees" page
			listWarehouses(request, response);

		}

	}

	private void deleteWarehouse(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read employee info from form data
			int id = Integer.parseInt(request.getParameter("warehouseId"));

			// perform delete on database
			warehousesDbUtil.deleteWarehouse(id);

			// write activity to db
			session = request.getSession();
			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"del warehouse", id);

			// send them back to the "list price" page
			listWarehouses(request, response);

		}

	}

	////////////////////////////////////// ohters //////////////////////////

}
