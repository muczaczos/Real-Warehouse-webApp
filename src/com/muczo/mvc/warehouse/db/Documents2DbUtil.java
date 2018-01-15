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
				String product = myRs.getString("productName");
				int qty = myRs.getInt("qty");

				// create new student object
				Document2 tempDocument2 = new Document2(id, provider, product, qty);

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
			String sql = "insert into documents2 " + "(provider, productName, qty) " + "values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theDocument2.getProvider());
			myStmt.setString(2, theDocument2.getProduct());
			myStmt.setDouble(3, theDocument2.getQty());
			
			OtherHelpers.correctQtyWhenAddDoc2(dataSource, OtherHelpers.getProductByName(theDocument2.getProduct(), dataSource), theDocument2.getQty());

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
				String product = myRs.getString("productName");
				int qty = myRs.getInt("qty");

				// use the document2id during construction
				document2 = new Document2(document2id, provider, product, qty);
			} else {
				throw new Exception("Could not find price id: " + theDocument2id);
			}

			return document2;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void updateDocument2(Document2 theDocument) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		Document2 document2 = getDocument2(Integer.toString(theDocument.getId()));

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update documents2 " + "set provider=?, productName=?, qty=? " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theDocument.getProvider());
			myStmt.setString(2, theDocument.getProduct());
			myStmt.setDouble(3, theDocument.getQty());
			myStmt.setInt(4, theDocument.getId());

			// execute SQL statement
			myStmt.execute();
			
			OtherHelpers.correctQtyWhenUpdateDoc(dataSource, OtherHelpers.getProductByName(document2.getProduct(), dataSource), 
					document2.getQty(), theDocument.getQty());

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
			
			OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource, OtherHelpers.getProductByName(document2.getProduct(), dataSource),
					document2.getQty());

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

}
