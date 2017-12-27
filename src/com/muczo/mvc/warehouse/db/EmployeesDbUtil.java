package com.muczo.mvc.warehouse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.muczo.mvc.warehouse.blueprint.Employee;

public class EmployeesDbUtil {
	
	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// DECLARATION ZONE //////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	private DataSource dataSource;

	public EmployeesDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// EMPLOYEES ZONE //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	public List<Employee> getEmployees() throws Exception {

		List<Employee> employees = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from employees";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String surname = myRs.getString("surname");
				String address = myRs.getString("address");
				String telephone = myRs.getString("telephone");
				String profession = myRs.getString("profession");
				String safetyTraining = myRs.getString("safetyTraining");
				String medicalVisit = myRs.getString("medicalVisit");
				String contractDate = myRs.getString("contractDate");

				// create new student object
				Employee tempEmployee = new Employee(id, name, surname, address, telephone, profession, safetyTraining,
						medicalVisit, contractDate);

				// add it to the list of students
				employees.add(tempEmployee);
			}

			return employees;
		} finally {
			// close JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void addEmployee(Employee theEmployee) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into employees " + "(name, surname, address, telephone, profession, safetyTraining,"
					+ " medicalVisit, contractDate) " + "values (?, ?, ?, ?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theEmployee.getName());
			myStmt.setString(2, theEmployee.getSurname());
			myStmt.setString(3, theEmployee.getAddress());
			myStmt.setString(4, theEmployee.getTelephone());
			myStmt.setString(5, theEmployee.getProfession());
			myStmt.setString(6, theEmployee.getSafetyTraining());
			myStmt.setString(7, theEmployee.getMedicalVisit());
			myStmt.setString(8, theEmployee.getContractDate());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}

	}

	public Employee getEmployee(String theEmployeeId) throws Exception {

		Employee theEmployee = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int employeeId;

		try {

			// convert employee id to int
			employeeId = (int) Integer.parseInt(theEmployeeId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected employee
			String sql = "select * from employees where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, employeeId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				String surname = myRs.getString("surname");
				String address = myRs.getString("address");
				String telephone = myRs.getString("telephone");
				String profession = myRs.getString("profession");
				String safetyTraining = myRs.getString("safetyTraining");
				String medicalVisit = myRs.getString("medicalVisit");
				String contractDate = myRs.getString("contractDate");

				// use the employeeId during construction
				theEmployee = new Employee(employeeId, name, surname, address, telephone, profession, safetyTraining,
						medicalVisit, contractDate);
			} else {
				throw new Exception("Could not find employee id: " + employeeId);
			}

			return theEmployee;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void updateEmployee(Employee employee) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update employees "
					+ "set name=?, surname=?, address=?, telephone=?, profession=?, safetyTraining=?, "
					+ "medicalVisit=?, contractDate=? where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, employee.getName());
			myStmt.setString(2, employee.getSurname());
			myStmt.setString(3, employee.getAddress());
			myStmt.setString(4, employee.getTelephone());
			myStmt.setString(5, employee.getProfession());
			myStmt.setString(6, employee.getSafetyTraining());
			myStmt.setString(7, employee.getMedicalVisit());
			myStmt.setString(8, employee.getContractDate());
			myStmt.setInt(9, employee.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

	public void deleteEmployee(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from employees " + "where id=?";

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
