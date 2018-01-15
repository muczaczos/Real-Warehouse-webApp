package com.muczo.mvc.warehouse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.Document;
import com.muczo.mvc.warehouse.blueprint.Product;
import com.muczo.mvc.warehouse.blueprint.User;
import com.muczo.mvc.warehouse.helperclasses.OtherHelpers;

public class Documents1DbUtil {

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// DECLARATION ZONE //////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	private DataSource dataSource;

	public Documents1DbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	//////////////////////////////////////////////////////////////////////////////
	//////////////////////// DOCUMENTS ZONE //////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	public List<Document> getDocuments() throws Exception {

		List<Document> documents = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from documents ORDER BY id DESC LIMIT 50";

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
				Document tempDocument = new Document(true, id, customer, reciepient, docId, date, product1, qty1,
						product2, qty2, product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7,
						info);

				// add it to the list of students
				documents.add(tempDocument);
			}

			return documents;
		} finally {
			// close JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public List<Document> getCustomerDocuments(String theCustomer) throws Exception {

		List<Document> documents = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected product
			String sql = "select * from documents where customer=? ORDER BY id DESC LIMIT 50";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theCustomer);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			while (myRs.next()) {

				String customer = myRs.getString("customer");
				String reciepient = myRs.getString("reciepient");
				int id = myRs.getInt("id");
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

				// use the documentId during construction
				Document theDocument = new Document(true, id, customer, reciepient, docId, date, product1, qty1,
						product2, qty2, product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7,
						info);
				documents.add(theDocument);
			}

			return documents;

		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void addDocument(Document theDocument) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into documents (customer, reciepient, docId, date, product1, qty1, product2, qty2, product3, qty3, "
					+ "product4, qty4, product5, qty5, product6, qty6, product7, qty7, info) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
					+ ", ?, ?, ?, ?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the document
			myStmt.setString(1, theDocument.getCustomer());
			myStmt.setString(2, theDocument.getReciepient());
			myStmt.setInt(3, theDocument.getNoOfDoc());
			myStmt.setString(4, theDocument.getDate());
			myStmt.setString(5, theDocument.getProduct1());
			myStmt.setInt(6, theDocument.getQty1());
			myStmt.setString(7, theDocument.getProduct2());
			myStmt.setInt(8, theDocument.getQty2());
			myStmt.setString(9, theDocument.getProduct3());
			myStmt.setInt(10, theDocument.getQty3());
			myStmt.setString(11, theDocument.getProduct4());
			myStmt.setInt(12, theDocument.getQty4());
			myStmt.setString(13, theDocument.getProduct5());
			myStmt.setInt(14, theDocument.getQty5());
			myStmt.setString(15, theDocument.getProduct6());
			myStmt.setInt(16, theDocument.getQty6());
			myStmt.setString(17, theDocument.getProduct7());
			myStmt.setInt(18, theDocument.getQty7());
			myStmt.setString(19, theDocument.getInfo());

			// execute sql insert
			myStmt.execute();

			if (theDocument.getQty1() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(theDocument.getProduct1(), dataSource), theDocument.getQty1());
			}
			if (theDocument.getQty2() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(theDocument.getProduct2(), dataSource), theDocument.getQty2());
			}
			if (theDocument.getQty3() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(theDocument.getProduct3(), dataSource), theDocument.getQty3());
			}
			if (theDocument.getQty4() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(theDocument.getProduct4(), dataSource), theDocument.getQty4());
			}
			if (theDocument.getQty5() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(theDocument.getProduct5(), dataSource), theDocument.getQty5());
			}
			if (theDocument.getQty6() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(theDocument.getProduct6(), dataSource), theDocument.getQty6());
			}
			if (theDocument.getQty7() > 0) {

				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(theDocument.getProduct7(), dataSource), theDocument.getQty7());
			}

		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}

	}

	public Document getDocument(String theDocumentId) throws Exception {

		Document theDocument = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int documentId;

		try {

			// convert document id to int
			documentId = (int) Integer.parseInt(theDocumentId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected product
			String sql = "select * from documents where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, documentId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {

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

				// use the documentId during construction
				theDocument = new Document(true, documentId, customer, reciepient, docId, date, product1, qty1,
						product2, qty2, product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7,
						info);
			} else {
				throw new Exception("Could not find document id: " + documentId);
			}

			return theDocument;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void updateDocument(Document theDocument) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		Document theDocumentBeforeUpdate = getDocument(Integer.toString(theDocument.getId()));

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update documents set customer=?, reciepient=?, docId=?, date=?, product1=?, qty1=?, product2=?, qty2=?, "
					+ "product3=?, qty3=?, product4=?, qty4=?, product5=?, qty5=?, product6=?, qty6=?, product7=?, qty7=?, "
					+ "info=? where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theDocument.getCustomer());
			myStmt.setString(2, theDocument.getReciepient());
			myStmt.setInt(3, theDocument.getNoOfDoc());
			myStmt.setString(4, theDocument.getDate());

			myStmt.setString(5, theDocument.getProduct1());
			myStmt.setInt(6, theDocument.getQty1());
			myStmt.setString(7, theDocument.getProduct2());
			myStmt.setInt(8, theDocument.getQty2());
			myStmt.setString(9, theDocument.getProduct3());
			myStmt.setInt(10, theDocument.getQty3());
			myStmt.setString(11, theDocument.getProduct4());
			myStmt.setInt(12, theDocument.getQty4());
			myStmt.setString(13, theDocument.getProduct5());
			myStmt.setInt(14, theDocument.getQty5());
			myStmt.setString(15, theDocument.getProduct6());
			myStmt.setInt(16, theDocument.getQty6());
			myStmt.setString(17, theDocument.getProduct7());
			myStmt.setInt(18, theDocument.getQty7());
			myStmt.setString(19, theDocument.getInfo());
			myStmt.setInt(20, theDocument.getId());

			// execute SQL statement
			myStmt.execute();

			OtherHelpers.correctQtyWhenUpdateDoc(dataSource,
					OtherHelpers.getProductByName(theDocument.getProduct1(), dataSource),
					theDocumentBeforeUpdate.getQty1(), theDocument.getQty1());
			OtherHelpers.correctQtyWhenUpdateDoc(dataSource,
					OtherHelpers.getProductByName(theDocument.getProduct1(), dataSource),
					theDocumentBeforeUpdate.getQty2(), theDocument.getQty2());
			OtherHelpers.correctQtyWhenUpdateDoc(dataSource,
					OtherHelpers.getProductByName(theDocument.getProduct1(), dataSource),
					theDocumentBeforeUpdate.getQty3(), theDocument.getQty3());
			OtherHelpers.correctQtyWhenUpdateDoc(dataSource,
					OtherHelpers.getProductByName(theDocument.getProduct1(), dataSource),
					theDocumentBeforeUpdate.getQty4(), theDocument.getQty4());
			OtherHelpers.correctQtyWhenUpdateDoc(dataSource,
					OtherHelpers.getProductByName(theDocument.getProduct1(), dataSource),
					theDocumentBeforeUpdate.getQty5(), theDocument.getQty5());
			OtherHelpers.correctQtyWhenUpdateDoc(dataSource,
					OtherHelpers.getProductByName(theDocument.getProduct1(), dataSource),
					theDocumentBeforeUpdate.getQty6(), theDocument.getQty6());
			OtherHelpers.correctQtyWhenUpdateDoc(dataSource,
					OtherHelpers.getProductByName(theDocument.getProduct1(), dataSource),
					theDocumentBeforeUpdate.getQty7(), theDocument.getQty7());

		} finally {
			// clean up JDBC objects and update products qty

			DbUtil.close(myConn, myStmt, null);

		}

	}

	public void deleteDocument(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		Document document = getDocument(Integer.toString(id));

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL delete document
			String sql = "delete from documents " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id);

			// execute SQL statement
			myStmt.execute();

			if (document.getQty1() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(document.getProduct1(), dataSource), document.getQty1());
			}
			if (document.getQty2() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(document.getProduct2(), dataSource), document.getQty2());
			}
			if (document.getQty3() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(document.getProduct3(), dataSource), document.getQty3());
			}
			if (document.getQty4() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(document.getProduct4(), dataSource), document.getQty4());
			}
			if (document.getQty5() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(document.getProduct5(), dataSource), document.getQty5());
			}
			if (document.getQty6() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(document.getProduct6(), dataSource), document.getQty6());
			}
			if (document.getQty7() > 0) {
				OtherHelpers.correctQtyWhenAddOrDelDoc(dataSource,
						OtherHelpers.getProductByName(document.getProduct7(), dataSource), document.getQty7());
			}

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// OTHER ZONE ////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	///////////////////// next doc number /////////////////////////
	// Each customer has own numeration of documents.
	// When new year starts, numerations starts from one again.
	public int nextCustomerDoc(String name) throws Exception {

		List<Document> docs = new ArrayList<>();
		docs = getDocuments();
		Connection myConn = null;
		myConn = dataSource.getConnection();

		int idDoc = 0;
		int year1 = 0;
		int year2 = 0;
		int docId = 0;
		// docs.clear();

		String checkSql = "select count(*) as count from documents where customer=?";
		PreparedStatement checkSmt = myConn.prepareStatement(checkSql);

		String sql = "select id, customer, reciepient, docId, date, product1, qty1, product2, qty2, "
				+ "product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7 from "
				+ "documents where customer=? order by id";

		PreparedStatement selectStatement = myConn.prepareStatement(sql);
		selectStatement.setString(1, name);

		checkSmt.setString(1, name);
		ResultSet checkResult = checkSmt.executeQuery();
		checkResult.next();

		String stringDate = null;
		ResultSet results = selectStatement.executeQuery();
		int count = checkResult.getInt(1);
		if (count > 0) {

			while (results.next()) {
				docId = results.getInt("docId");
				stringDate = results.getString("date");
			}

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date2 = null;

			/*
			 * try { date2 = sdf.parse("31-08-2017"); } catch (ParseException e1) { // TODO
			 * Auto-generated catch block e1.printStackTrace(); }
			 */
			// DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
			date2 = new Date();
			System.out.println(sdf.format(date2));
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date2);

			// System.out.println(docs.get(docs.size()-1).getDate());
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date date = null;
			try {
				date = format.parse(stringDate);
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(date);
				String newDateString = format.format(date);
				System.out.println(newDateString);
				int dni = (int) ((date.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
				year1 = cal1.get(Calendar.YEAR);
				year2 = cal2.get(Calendar.YEAR);

				System.out.println(year1);

				System.out.println(year2);

				if ((year1 - year2) == 1) {
					idDoc = 1;
					System.out.println("kutafonnnnos");
				} else if ((year1 - year2 == 0)) {
					idDoc = ++docId;
				}

				System.out.println(year1 - year2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			results.close();
			selectStatement.close();
		} else {
			System.out.println("dupa dupa dupa");
			idDoc = 1;
		}

		return idDoc;
	}

}
