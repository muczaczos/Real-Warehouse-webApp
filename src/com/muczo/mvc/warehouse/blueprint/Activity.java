package com.muczo.mvc.warehouse.blueprint;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.muczo.mvc.warehouse.db.DbUtil;

public class Activity implements Serializable {

	public static void monitorSpecificActivity(HttpSession session, HttpServletRequest request, DataSource dataSource, 
			String userName, String activityName, int activityId) throws Exception {
		
		// write activity to db
		session = request.getSession();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		String dateTime = dtf.format(now);
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into activity " + "(userName, activityName, dateTime, activityId) " + "values (?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, userName);
			myStmt.setString(2, activityName);
			myStmt.setString(3, dateTime);
			myStmt.setInt(4, activityId);

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}
	}
	

}
