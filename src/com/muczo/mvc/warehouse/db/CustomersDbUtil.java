package com.muczo.mvc.warehouse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.Customer;

public class CustomersDbUtil {

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// DECLARATION ZONE //////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	private DataSource dataSource;

	public CustomersDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// CUSTOMERS ZONE //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	public List<Customer> getCustomers() throws Exception {

		List<Customer> customers = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from customers ORDER BY name";

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
				Customer tempCustomer = new Customer(id, name, address, telephone);

				// add it to the list of students
				customers.add(tempCustomer);
			}

			return customers;
		} finally {
			// close JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}

	}

	public void addCustomer(Customer theCustomer) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into customers " + "(name, address, telephone) " + "values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theCustomer.getName());
			myStmt.setString(2, theCustomer.getAddress());
			myStmt.setString(3, theCustomer.getTelephone());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}

	}

	public Customer getCustomer(String theCustomerId) throws Exception {

		Customer theCustomer = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int customerId;

		try {

			// convert student id to int
			customerId = (int) Integer.parseInt(theCustomerId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected student
			String sql = "select * from customers where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, customerId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				String address = myRs.getString("address");
				String telephone = myRs.getString("telephone");

				// use the studentId during construction
				theCustomer = new Customer(customerId, name, address, telephone);
			} else {
				throw new Exception("Could not find customer id: " + customerId);
			}

			return theCustomer;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void updateCustomer(Customer theCustomer) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update customers " + "set name=?, address=?, telephone=? " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theCustomer.getName());
			myStmt.setString(2, theCustomer.getAddress());
			myStmt.setString(3, theCustomer.getTelephone());
			myStmt.setInt(4, theCustomer.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

	public void deleteCustomer(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from customers " + "where id=?";

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
