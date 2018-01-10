package com.muczo.mvc.warehouse.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

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
import com.muczo.mvc.warehouse.blueprint.User;
import com.muczo.mvc.warehouse.db.Documents1DbUtil;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

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
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("logout");
		
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		
		
		try {
			
			
			//write activity to db
			Activity.monitorSpecificActivity(session, request, dataSource, session.getAttribute("userName").toString(),
					"Logout", 0);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
			dispatcher.forward(request, response);
			
			session.setAttribute("userName", null);
			System.out.println(session.getAttribute("userName"));
			session.invalidate();  
	
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
			
		
	
	
	}
	

}
