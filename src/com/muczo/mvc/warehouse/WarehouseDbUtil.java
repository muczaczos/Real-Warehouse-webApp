package com.muczo.mvc.warehouse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class WarehouseDbUtil {

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// DECLARATION ZONE ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	private DataSource dataSource;

	public WarehouseDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// PRODUCST ZONE //////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	public List<Product> getProducts(String warehouse) throws Exception {
		List<Product> products = new ArrayList<>();

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from products where warehouse=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, warehouse);

			// execute statement
			myRs = myStmt.executeQuery();

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String warehouseSql = myRs.getString("warehouse");

				// create new student object
				Product product = new Product(id, name, warehouseSql);

				// add it to the list of students
				products.add(product);
			}

			return products;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
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
			close(myConn, myStmt, myRs);
		}
	}

	/////////////////////////////////////////////////////////////////////////////////
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
			String sql = "select * from customers";

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
			close(myConn, myStmt, myRs);
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
			close(myConn, myStmt, null);
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
			close(myConn, myStmt, myRs);
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

			close(myConn, myStmt, null);

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

			close(myConn, myStmt, null);

		}

	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// RECIEPIENT ZONE //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	public void addReciepient(Reciepient theReciepient) 
		throws Exception{
		
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
			close(myConn, myStmt, null);
		}

	}

	public Reciepient getReciepient(String theRciepientId) 
			throws Exception {
		
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
			close(myConn, myStmt, myRs);
		}
	}

	
	public void updateReciepient(Reciepient theReciepient) 
		throws Exception{

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

			close(myConn, myStmt, null);

		}
		
	}
	
	public void deleteReciepient(int id) 
			throws Exception {

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

			close(myConn, myStmt, null);

		}

	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// OTHER ZONE //////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close(); // doesn't really close it ... just puts back in connection pool
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public List<Documents> getDocuments() throws Exception {

		List<Documents> documents = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from documents";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String customer = myRs.getString("customer");
				String reciepient = myRs.getString("reciepient");
				int docId = myRs.getInt("docId");
				String date = myRs.getString("date");
				String product1 = myRs.getString("product1");
				int qty1 = myRs.getInt("qty1");
				String product2 = myRs.getString("product2");
				int qty2 = myRs.getInt("qty2");
				String product3 = myRs.getString("product3");
				int qty3 = myRs.getInt("qty3");
				String product4 = myRs.getString("product4");
				int qty4 = myRs.getInt("qty4");
				String product5 = myRs.getString("product5");
				int qty5 = myRs.getInt("qty5");
				String product6 = myRs.getString("product6");
				int qty6 = myRs.getInt("qty6");
				String product7 = myRs.getString("product7");
				int qty7 = myRs.getInt("qty7");
				String info = myRs.getString("info");

				// create new student object
				Documents tempDocument = new Documents(true, id, customer, reciepient, docId, date, product1, qty1,
						product2, qty2, product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7,
						info);

				// add it to the list of students
				documents.add(tempDocument);
			}

			return documents;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	

}
