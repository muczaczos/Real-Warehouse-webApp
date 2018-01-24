package com.muczo.mvc.warehouse.helperclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.Product;
import com.muczo.mvc.warehouse.blueprint.User;
import com.muczo.mvc.warehouse.db.DbUtil;

public class OtherHelpers {

	public static Product getProductByName(String theProductName, DataSource dataSource) throws Exception {

		Product theProduct = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

	
			try {

				// get connection to database
				myConn = dataSource.getConnection();

				// create sql to get selected product
				String sql = "select * from products where name=?";

				// create prepared statement
				myStmt = myConn.prepareStatement(sql);

				// set params
				myStmt.setString(1, theProductName);

				// execute statement
				myRs = myStmt.executeQuery();

				// retrive data from result set row
				if (myRs.next()) {
					int id = myRs.getInt("id");
					String name = myRs.getString("name");
					String warehouse = myRs.getString("warehouse");
					int qty = myRs.getInt("qty");

					// use the studentId during construction
					theProduct = new Product(id, name, warehouse, qty);
				} else {
					
				}

				
			} finally {
				// clean up JDBC objects
				DbUtil.close(myConn, myStmt, myRs);
			}
		
		
		return theProduct;
	}

	public static void correctQtyWhenDelDoc2(DataSource dataSource, Product product, int qty) throws Exception {

		if (qty > 0) {

			int qty2 = product.getQty() - qty;
			product.setQty(qty2);
			updateProductQty(product, dataSource);
		}
	}

	public static void correctQtyWhenAddDoc(DataSource dataSource, Product product, int qty) throws Exception {

		if (qty > 0) {

			int qty2 = product.getQty() - qty;
			product.setQty(qty2);
			updateProductQty(product, dataSource);
		}
	}

	public static void correctQtyWhenDelDoc(DataSource dataSource, Product product, int qty) throws Exception {

		if (qty > 0) {

			int qty2 = product.getQty() + qty;
			product.setQty(qty2);
			updateProductQty(product, dataSource);
		}
	}

	public static void correctQtyWhenAddDoc2(DataSource dataSource, Product product, int qty) throws Exception {

		if (qty > 0) {
			int qty2 = product.getQty() + qty;
			product.setQty(qty2);
			updateProductQty(product, dataSource);
		}
	}

	public static void correctQtyWhenUpdateDoc2(DataSource dataSource, Product product, int qty1, int qty2)
			throws Exception {

			if (qty1 != qty2) {

				int qty3 = product.getQty() - qty1;
				qty3 = qty3 + qty2;
				product.setQty(qty3);
				updateProductQty(product, dataSource);
			
		}
	}

	public static void correctQtyWhenUpdateDoc(DataSource dataSource, Product product, int qty1, int qty2)
			throws Exception {

		if (qty1 > 0) {

			if (qty1 != qty2) {

				int qty3 = product.getQty() + qty1;
				qty3 = qty3 - qty2;
				product.setQty(qty3);
				updateProductQty(product, dataSource);
			}
		}
	}

	private static void updateProductQty(Product theProduct, DataSource dataSource) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update products " + "set qty=?" + " where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, theProduct.getQty());
			myStmt.setInt(2, theProduct.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

	public static User getUserByName(String theUserName, DataSource dataSource) throws Exception {

		User theUser = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected product
			String sql = "select * from users where name=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theUserName);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				String password = myRs.getString("password");
				int id = myRs.getInt("id");

				// use the studentId during construction
				theUser = new User(id, name, password);
			} else {
				// throw new Exception("Could not find user name: " + theUserName);
			}

			return theUser;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}
	

}
