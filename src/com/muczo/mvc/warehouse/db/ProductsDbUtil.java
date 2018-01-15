package com.muczo.mvc.warehouse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.Product;

public class ProductsDbUtil {

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// DECLARATION ZONE //////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	private DataSource dataSource;

	public ProductsDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	///////////////////////////////////////////////////////////////////////////////
	////////////////////////// PRODUCST ZONE //////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	public List<Product> getProducts() throws Exception {

		List<Product> products = new ArrayList<>();

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from products ORDER BY name";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// execute statement
			myRs = myStmt.executeQuery();

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String warehouseSql = myRs.getString("warehouse");
				int qty = myRs.getInt("qty");

				// create new student object
				Product product = new Product(id, name, warehouseSql, qty);

				// add it to the list of students
				products.add(product);
			}

			return products;
		} finally {
			// close JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void addProduct(Product theProduct) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into products " + "(name, warehouse) " + "values (?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the product
			myStmt.setString(1, theProduct.getProductName());
			myStmt.setString(2, theProduct.getWarehouse());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}

	}

	public List<Product> getProducts(String warehouse) throws Exception {

		List<Product> products = new ArrayList<>();

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from products where warehouse=? ORDER BY name";

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
				int qty = myRs.getInt("qty");

				// create new student object
				Product product = new Product(id, name, warehouseSql, qty);

				// add it to the list of students
				products.add(product);
			}

			return products;
		} finally {
			// close JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public Product getProduct(String theProductId) throws Exception {

		Product theProduct = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int productId;

		try {

			// convert product id to int
			productId = (int) Integer.parseInt(theProductId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected product
			String sql = "select * from products where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, productId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				String warehouse = myRs.getString("warehouse");
				int qty = myRs.getInt("qty");

				// use the studentId during construction
				theProduct = new Product(productId, name, warehouse, qty);
			} else {
				throw new Exception("Could not find product id: " + productId);
			}

			return theProduct;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	
	public void updateProduct(Product theProduct) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update products " + "set name=?, warehouse=? " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theProduct.getProductName());
			myStmt.setString(2, theProduct.getWarehouse());
			myStmt.setInt(3, theProduct.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.	close(myConn, myStmt, null);

		}

	}

	

	public void deleteProduct(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from products " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id);

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.	close(myConn, myStmt, null);

		}

	}

}
