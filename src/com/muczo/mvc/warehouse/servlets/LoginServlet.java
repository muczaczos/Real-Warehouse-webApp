package com.muczo.mvc.warehouse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.Activity;
import com.muczo.mvc.warehouse.blueprint.User;
import com.muczo.mvc.warehouse.db.WarehouseDbUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private WarehouseDbUtil warehouseDbUtil;

	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		// create our warehouse db util ... and pass in the conn pool / datasource
		try {
			warehouseDbUtil = new WarehouseDbUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		User user = null;
		String name = "d";
		String password = "k";
		String userPass = "p";

		name = request.getParameter("name");
		password = request.getParameter("password");
		try {
			user = warehouseDbUtil.getUserByName(name);
			userPass = user.getPassword();
		} catch (Exception e) {

		}

		if (password.equals(userPass)) {
			out.print("Welcome, " + name);
			HttpSession session = request.getSession();
			session.setAttribute("userName", name);
			response.sendRedirect("WarehouseControllerServlet");

			//write activity to db
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			try {
				Activity activity = new Activity(name, "Login", dtf.format(now), 0);
				warehouseDbUtil.monitorActivity(activity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			out.print("Wpisane has�o lub login jest niepoprawne!");

		}
		out.close();
	}
}