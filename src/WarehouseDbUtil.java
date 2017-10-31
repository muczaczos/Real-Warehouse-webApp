import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

public class WarehouseDbUtil {
	
	private DataSource dataSource;

	public WarehouseDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
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
			String sql = "select * from products where warehouse=?";

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
				
				// create new student object
				Product product = new Product(id, name, warehouseSql);
				
				// add it to the list of students
				products.add(product);				
			}
			
			return products;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}
	
	
	public List<Reciepient> getReciepients() 
		throws Exception{
		
		List<Reciepient> reciepients = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from reciepients";
			
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
				
				// create new student object
				Reciepient tempReciepient = new Reciepient(id, name, address, telephone);
				
				// add it to the list of students
				reciepients.add(tempReciepient);				
			}
			
			return reciepients;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}	
	}
	
	public List<Customer> getCustomers() 
		throws Exception{

		List<Customer> customers = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from customers";
			
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
				
				// create new student object
				Customer tempCustomer = new Customer(id, name, address, telephone);
				
				// add it to the list of students
				customers.add(tempCustomer);				
			}
			
			return customers;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}	
		
	}

	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}




	
}
