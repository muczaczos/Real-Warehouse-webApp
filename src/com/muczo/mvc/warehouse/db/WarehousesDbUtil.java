package com.muczo.mvc.warehouse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.Warehouse;

public class WarehousesDbUtil {

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// DECLARATION ZONE //////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	private DataSource dataSource;

	public WarehousesDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// WAREHOUSES ZONE /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	public List<Warehouse> getWarehouses() throws Exception {

		List<Warehouse> warehouses = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from warehouses";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");

				// create new warehouse object
				Warehouse tempWarehouse = new Warehouse(id, name);

				// add it to the list of students
				warehouses.add(tempWarehouse);
			}

			return warehouses;
		} finally {
			// close JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void addWarehouse(Warehouse theWarehouse) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert

			String sql = "insert into warehouses (name) values (?)";
			System.out.println("warehouse upa upa upa");
			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theWarehouse.getName());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}

	}

	public Warehouse getWarehouse(String theWarehouseId) throws Exception {

		Warehouse theWarehouse = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int warehouseId;

		try {

			// convert employee id to int
			warehouseId = (int) Integer.parseInt(theWarehouseId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected warehouse
			String sql = "select * from warehouses where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, warehouseId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");

				// use the warehouseId during construction
				theWarehouse = new Warehouse(warehouseId, name);
			} else {
				throw new Exception("Could not find employee id: " + warehouseId);
			}

			return theWarehouse;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}

	}

	public void updateWarehouse(Warehouse warehouse) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update warehouses " + "set name=? where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, warehouse.getName());
			myStmt.setInt(2, warehouse.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

	public void deleteWarehouse(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from warehouses " + "where id=?";

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
