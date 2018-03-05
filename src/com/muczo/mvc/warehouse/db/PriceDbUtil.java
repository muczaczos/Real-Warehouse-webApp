package com.muczo.mvc.warehouse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.PriceList;

public class PriceDbUtil {

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// DECLARATION ZONE //////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	private DataSource dataSource;

	public PriceDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// PRICE LIST ZONE /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	public List<PriceList> getPrices() throws Exception {

		List<PriceList> prices = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from prices ORDER BY customer";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String customer = myRs.getString("customer");
				String product = myRs.getString("product");
				Double price = myRs.getDouble("price");

				// create new student object
				PriceList tempPrice = new PriceList(true, id, customer, product, price);

				// add it to the list of students
				prices.add(tempPrice);
			}

			return prices;
		} finally {
			// close JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void addPrice(PriceList thePrice) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into prices " + "(customer, product, price) " + "values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, thePrice.getCustomer());
			myStmt.setString(2, thePrice.getProduct());
			myStmt.setDouble(3, thePrice.getPrice());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}
	}

	public PriceList getPrice(String thePriceId) throws Exception {

		PriceList thePrice = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int priceId;

		try {

			// convert student id to int
			priceId = (int) Integer.parseInt(thePriceId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected student
			String sql = "select * from prices where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, priceId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String customer = myRs.getString("customer");
				String product = myRs.getString("product");
				Double price = myRs.getDouble("price");

				// use the studentId during construction
				thePrice = new PriceList(true, priceId, customer, product, price);
			} else {
				throw new Exception("Could not find price id: " + priceId);
			}

			return thePrice;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void updatePrice(PriceList thePrice) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update prices " + "set customer=?, product=?, price=? " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, thePrice.getCustomer());
			myStmt.setString(2, thePrice.getProduct());
			myStmt.setDouble(3, thePrice.getPrice());
			myStmt.setInt(4, thePrice.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

	public void deletePrice(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from prices " + "where id=?";

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

	public Double loadPriceForCustomer(String product, String customer) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Double price = 0.00;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			String sql = "select price from prices where customer=? and product=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, customer);
			myStmt.setString(2, product);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				price = myRs.getDouble("price");
			}
		} finally {
			DbUtil.close(myConn, myStmt, myRs);
		}

		return price;

	}

}
