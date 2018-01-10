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
import com.muczo.mvc.warehouse.db.Documents1DbUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private Documents1DbUtil warehouseDbUtil;

	@Resource(name = "jdbc/kp_warehouse_documents")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		// create our warehouse db util ... and pass in the conn pool / datasource
		try {
			warehouseDbUtil = new Documents1DbUtil(dataSource);
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
			response.sendRedirect("Document1ControllerServlet");

			// write activity to db

			try {
				Activity.monitorSpecificActivity(session, request, dataSource,
						session.getAttribute("userName").toString(), "Login", 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		} else {
			out.print("Wpisane has³o lub login jest niepoprawne!");

		}
		out.close();
	}
}
