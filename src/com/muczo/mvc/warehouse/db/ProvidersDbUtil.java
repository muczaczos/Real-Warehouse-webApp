package com.muczo.mvc.warehouse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.Provider;

public class ProvidersDbUtil {
	
	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// DECLARATION ZONE //////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	private DataSource dataSource;

	public ProvidersDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// PROVIDERS ZONE //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	public List<Provider> getProviders() throws Exception {

		List<Provider> providers = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from providers";

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

				// create new provider object
				Provider tempProvider = new Provider(id, name, address, telephone);

				// add it to the list of students
				providers.add(tempProvider);
			}

			return providers;
		} finally {
			// close JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}

	}

	public void addProvider(Provider theProvider) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into providers " + "(name, address, telephone) " + "values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theProvider.getName());
			myStmt.setString(2, theProvider.getAddress());
			myStmt.setString(3, theProvider.getTelephone());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}

	}

	public Provider getProvider(String theProviderId) throws Exception {

		Provider theProvider = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int providerId;

		try {

			// convert student id to int
			providerId = (int) Integer.parseInt(theProviderId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected student
			String sql = "select * from providers where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, providerId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				String address = myRs.getString("address");
				String telephone = myRs.getString("telephone");

				// use the studentId during construction
				theProvider = new Provider(providerId, name, address, telephone);
			} else {
				throw new Exception("Could not find provider id: " + providerId);
			}

			return theProvider;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void updateProvider(Provider theProvider) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update providers " + "set name=?, address=?, telephone=? " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theProvider.getName());
			myStmt.setString(2, theProvider.getAddress());
			myStmt.setString(3, theProvider.getTelephone());
			myStmt.setInt(4, theProvider.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

	public void deleteProvider(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from providers " + "where id=?";

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
