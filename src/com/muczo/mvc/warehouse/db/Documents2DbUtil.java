package com.muczo.mvc.warehouse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.Document2;
import com.muczo.mvc.warehouse.blueprint.Product;
import com.muczo.mvc.warehouse.blueprint.User;
import com.muczo.mvc.warehouse.helperclasses.OtherHelpers;

public class Documents2DbUtil {
	

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// DECLARATION ZONE //////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	private DataSource dataSource;

	public Documents2DbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}


	///////////////////////////////////////////////////////////////////////////////
	////////////////////////// DOCUMENTS2 ZONE ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	public List<Document2> getDocuments2() throws Exception {

		List<Document2> documents2 = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from documents2";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String provider = myRs.getString("provider");
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

				// create new student object
				Document2 tempDocument2 = new Document2(id, provider, product1, qty1, product2, qty2, product3, qty3,
						 product4, qty4, product5, qty5, product6, qty6, product7, qty7);

				// add it to the list of students
				documents2.add(tempDocument2);
			}

			return documents2;
		} finally {
			// close JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void addDocument2(Document2 theDocument2) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
	
		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into documents2 " + "(provider, product1, qty1, product2, qty2, product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theDocument2.getProvider());
			myStmt.setString(2, theDocument2.getProduct1());
			myStmt.setDouble(3, theDocument2.getQty1());
			myStmt.setString(4, theDocument2.getProduct2());
			myStmt.setDouble(5, theDocument2.getQty2());
			myStmt.setString(6, theDocument2.getProduct3());
			myStmt.setDouble(7, theDocument2.getQty3());
			myStmt.setString(8, theDocument2.getProduct4());
			myStmt.setDouble(9, theDocument2.getQty4());
			myStmt.setString(10, theDocument2.getProduct5());
			myStmt.setDouble(11, theDocument2.getQty5());
			myStmt.setString(12, theDocument2.getProduct6());
			myStmt.setDouble(13, theDocument2.getQty6());
			myStmt.setString(14, theDocument2.getProduct7());
			myStmt.setDouble(15, theDocument2.getQty7());
			
		
			
			OtherHelpers.correctQtyWhenAddDoc2(dataSource, OtherHelpers.getProductByName(theDocument2.getProduct1(), dataSource), theDocument2.getQty1());
			OtherHelpers.correctQtyWhenAddDoc2(dataSource, OtherHelpers.getProductByName(theDocument2.getProduct2(), dataSource), theDocument2.getQty2());
			OtherHelpers.correctQtyWhenAddDoc2(dataSource, OtherHelpers.getProductByName(theDocument2.getProduct3(), dataSource), theDocument2.getQty3());
			OtherHelpers.correctQtyWhenAddDoc2(dataSource, OtherHelpers.getProductByName(theDocument2.getProduct4(), dataSource), theDocument2.getQty4());
			OtherHelpers.correctQtyWhenAddDoc2(dataSource, OtherHelpers.getProductByName(theDocument2.getProduct5(), dataSource), theDocument2.getQty5());
			OtherHelpers.correctQtyWhenAddDoc2(dataSource, OtherHelpers.getProductByName(theDocument2.getProduct6(), dataSource), theDocument2.getQty6());
			OtherHelpers.correctQtyWhenAddDoc2(dataSource, OtherHelpers.getProductByName(theDocument2.getProduct7(), dataSource), theDocument2.getQty7());
			
			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}

	}

	public Document2 getDocument2(String theDocument2id) throws Exception {

		Document2 document2 = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int document2id;

		try {

			// convert student id to int
			document2id = (int) Integer.parseInt(theDocument2id);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected student
			String sql = "select * from documents2 where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, document2id);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String provider = myRs.getString("provider");
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

				// use the document2id during construction
				document2 = new Document2(document2id, provider, product1, qty1, product2, qty2, product3, qty3,
						 product4, qty4, product5, qty5, product6, qty6, product7, qty7);
			} else {
				throw new Exception("Could not find price id: " + theDocument2id);
			}

			return document2;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void updateDocument2(Document2 theDocument2) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		Document2 document2 = getDocument2(Integer.toString(theDocument2.getId()));

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update documents2 " + "set provider=?, product1=?, qty1=?, product2=?, qty2=? , product3=?, qty3=? "
					+ ", product4=?, qty4=? , product5=?, qty5=? , product6=?, qty6=? , product7=?, qty7=?  " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theDocument2.getProvider());
			myStmt.setString(2, theDocument2.getProduct1());
			myStmt.setDouble(3, theDocument2.getQty1());
			myStmt.setString(4, theDocument2.getProduct2());
			myStmt.setDouble(5, theDocument2.getQty2());
			myStmt.setString(6, theDocument2.getProduct3());
			myStmt.setDouble(7, theDocument2.getQty3());
			myStmt.setString(8, theDocument2.getProduct4());
			myStmt.setDouble(9, theDocument2.getQty4());
			myStmt.setString(10, theDocument2.getProduct5());
			myStmt.setDouble(11, theDocument2.getQty5());
			myStmt.setString(12, theDocument2.getProduct6());
			myStmt.setDouble(13, theDocument2.getQty6());
			myStmt.setString(14, theDocument2.getProduct7());
			myStmt.setDouble(15, theDocument2.getQty7());
			myStmt.setInt(16, theDocument2.getId());

			// execute SQL statement
			myStmt.execute();
			
			OtherHelpers.correctQtyWhenUpdateDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct1(), dataSource), 
					document2.getQty1(), theDocument2.getQty1());
			OtherHelpers.correctQtyWhenUpdateDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct2(), dataSource), 
					document2.getQty1(), theDocument2.getQty1());
			OtherHelpers.correctQtyWhenUpdateDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct3(), dataSource), 
					document2.getQty1(), theDocument2.getQty1());
			OtherHelpers.correctQtyWhenUpdateDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct4(), dataSource), 
					document2.getQty1(), theDocument2.getQty1());
			OtherHelpers.correctQtyWhenUpdateDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct5(), dataSource), 
					document2.getQty1(), theDocument2.getQty1());
			OtherHelpers.correctQtyWhenUpdateDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct6(), dataSource), 
					document2.getQty1(), theDocument2.getQty1());
			OtherHelpers.correctQtyWhenUpdateDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct7(), dataSource), 
					document2.getQty1(), theDocument2.getQty1());

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

	public void deleteDocument2(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		Document2 document2 = getDocument2(Integer.toString(id));

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from documents2 " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id);

			// execute SQL statement
			myStmt.execute();
			
			OtherHelpers.correctQtyWhenDelDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct1(), dataSource),
					document2.getQty1());
			OtherHelpers.correctQtyWhenDelDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct2(), dataSource),
					document2.getQty2());
			OtherHelpers.correctQtyWhenDelDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct3(), dataSource),
					document2.getQty3());
			OtherHelpers.correctQtyWhenDelDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct4(), dataSource),
					document2.getQty4());
			OtherHelpers.correctQtyWhenDelDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct5(), dataSource),
					document2.getQty5());
			OtherHelpers.correctQtyWhenDelDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct6(), dataSource),
					document2.getQty6());
			OtherHelpers.correctQtyWhenDelDoc2(dataSource, OtherHelpers.getProductByName(document2.getProduct7(), dataSource),
					document2.getQty7());

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

}
