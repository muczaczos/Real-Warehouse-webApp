package com.muczo.mvc.warehouse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.Reciepient;

public class ReciepientsDbUtil {

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// DECLARATION ZONE //////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	private DataSource dataSource;

	public ReciepientsDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	//////////////////////////////////////////////////////////////////////////////
	//////////////////////// RECIEPIENT ZONE /////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	public void addReciepient(Reciepient theReciepient) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into reciepients " + "(name, address, telephone) " + "values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theReciepient.getName());
			myStmt.setString(2, theReciepient.getAddress());
			myStmt.setString(3, theReciepient.getTelephone());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}

	}

	public List<Reciepient> getReciepients() throws Exception {

		List<Reciepient> reciepients = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from reciepients";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String address = myRs.getString("address");
				String telephone = myRs.getString("telephone");

				// create new student object
				Reciepient tempReciepient = new Reciepient(id, name, address, telephone);

				// add it to the list of students
				reciepients.add(tempReciepient);
			}

			return reciepients;
		} finally {
			// close JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public Reciepient getReciepient(String theRciepientId) throws Exception {

		Reciepient theReciepient = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int reciepientId;

		try {

			// convert student id to int
			reciepientId = (int) Integer.parseInt(theRciepientId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected student
			String sql = "select * from reciepients where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, reciepientId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				String address = myRs.getString("address");
				String telephone = myRs.getString("telephone");

				// use the studentId during construction
				theReciepient = new Reciepient(reciepientId, name, address, telephone);
			} else {
				throw new Exception("Could not find customer id: " + reciepientId);
			}

			return theReciepient;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void updateReciepient(Reciepient theReciepient) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update reciepients " + "set name=?, address=?, telephone=? " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theReciepient.getName());
			myStmt.setString(2, theReciepient.getAddress());
			myStmt.setString(3, theReciepient.getTelephone());
			myStmt.setInt(4, theReciepient.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

	public void deleteReciepient(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from reciepients " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id);

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

}
