package com.muczo.mvc.warehouse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.Invoice;
import com.muczo.mvc.warehouse.blueprint.PriceList;

public class InvoiceDbUtil {

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// DECLARATION ZONE //////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	private DataSource dataSource;

	public InvoiceDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// INVOICE ZONE ////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	public List<Invoice> getInvoices() throws Exception {

		List<Invoice> invoices = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from invoices";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String customer = myRs.getString("customer");
				String date = myRs.getString("date");
				int invNumber = myRs.getInt("invNumber");
				int startDocRange = myRs.getInt("startDocRange");
				int endDocRange = myRs.getInt("startDocRange");
				Double grossAmount = myRs.getDouble("grossAmount");

				// create new invoice object
				Invoice invoice = new Invoice(id, customer, date, invNumber, startDocRange, endDocRange, grossAmount);

				// add it to the list of students
				invoices.add(invoice);
			}

			return invoices;
		} finally {
			// close JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void addInvoice(Invoice invoice) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into invoices " + "(customer, date, invNumber, startDocRange, endDocRange, grossAmount) " + 
			"values (?, ?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the invoice
			myStmt.setString(1, invoice.getCustomer());
			myStmt.setString(2, invoice.getDate());
			myStmt.setInt(3, invoice.getInvNumber());
			myStmt.setInt(4, invoice.getStartDocRange());
			myStmt.setInt(5, invoice.getEndDocRange());
			myStmt.setDouble(6, invoice.getGrossAmount());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}
	}

	public Invoice getInvoice(String theInvoiceId) throws Exception {

		Invoice invoice = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int invoiceId;

		try {

			// convert student id to int
			invoiceId = (int) Integer.parseInt(theInvoiceId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected student
			String sql = "select * from invoices where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, invoiceId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String customer = myRs.getString("customer");
				String date = myRs.getString("data");
				int invNumber = myRs.getInt("invNumber");
				int startDocNumber = myRs.getInt("startDocRange");
				int endDocNumber = myRs.getInt("endDocRange");
				Double grossAmount = myRs.getDouble("grossAmount");

				// use the studentId during construction
				invoice = new Invoice(invoiceId, customer, date, invNumber, startDocNumber, endDocNumber, grossAmount);
			} else {
				throw new Exception("Could not find invoice id: " + invoiceId);
			}

			return invoice;
			
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void updateInvoice(Invoice invoice) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update invoices " + "set customer=?, date=?, invNumber=?, startDocRange=?, endDocRange=?, grossAmount=?" + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, invoice.getCustomer());
			myStmt.setString(2, invoice.getDate());
			myStmt.setInt(3, invoice.getInvNumber());
			myStmt.setInt(4, invoice.getStartDocRange());
			myStmt.setInt(5, invoice.getEndDocRange());
			myStmt.setDouble(6, invoice.getGrossAmount());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

	public void deleteInvoice(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from invoices " + "where id=?";

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
