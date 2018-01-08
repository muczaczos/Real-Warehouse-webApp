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
import com.muczo.mvc.warehouse.blueprint.Employee;
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
 * Servlet implementation class EmployeesControllerServlet
 */
@WebServlet("/EmployeesControllerServlet")
public class EmployeesControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Documents1DbUtil documents1DbUtil;
	private EmployeesDbUtil employeesDbUtil;

	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {

		super.init();

		// create our warehouse db util ... and pass in the conn pool / datasource
		try {

			documents1DbUtil = new Documents1DbUtil(dataSource);
			employeesDbUtil = new EmployeesDbUtil(dataSource);
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
						theCommand = "LIST-EMPLOYEE";
					}

					// route to the appropriate method
					switch (theCommand) {

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

					default:
						listEmployees(request, response);
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
	////////////////////////// EMPLOYEES ZONE //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// get employee from db util
			List<Employee> employees = employeesDbUtil.getEmployees();

			// add employee to the request
			request.setAttribute("EMPLOYEE_LIST", employees);

			session = request.getSession();
			session.setAttribute("Employees", employees);

			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/create-employees.jsp");
			dispatcher.forward(request, response);

		}

	}

	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

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
			Employee theEmployee = new Employee(name, surname, address, telephone, profession, safetyTraining,
					medicalVisit, contractDate);

			// add the employee to the database
			employeesDbUtil.addEmployee(theEmployee);

			// write activity to db
			List<Employee> employees = employeesDbUtil.getEmployees();
			int id = employees.get(employees.size() - 1).getId();

			session = request.getSession();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			try {
				Activity activity = new Activity(session.getAttribute("userName").toString(), "add employee",
						dtf.format(now), id);
				documents1DbUtil.monitorActivity(activity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// send back to main page (the employee list)
			listEmployees(request, response);

		}

	}

	private void loadEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

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

	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

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
			session = request.getSession();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			try {
				Activity activity = new Activity(session.getAttribute("userName").toString(), "update employee",
						dtf.format(now), id);
				documents1DbUtil.monitorActivity(activity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// send them back to the "list employees" page
			listEmployees(request, response);

		}

	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();

		if (session.getAttribute("userName") != null) {

			// read employee info from form data
			int id = Integer.parseInt(request.getParameter("employeeId"));

			// perform delete on database
			employeesDbUtil.deleteEmployee(id);

			// write activity to db
			session = request.getSession();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			try {
				Activity activity = new Activity(session.getAttribute("userName").toString(), "del employee",
						dtf.format(now), id);
				documents1DbUtil.monitorActivity(activity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// send them back to the "list price" page
			listEmployees(request, response);

		}

	}

}
