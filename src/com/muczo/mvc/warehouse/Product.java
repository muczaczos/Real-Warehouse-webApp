package com.muczo.mvc.warehouse;
import java.io.Serializable;

public class Product implements Serializable {

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////// Main variables///////////////////////
	/////////////////////////////////////////////////////////////////////
	private static int count = 0;

	private int id;
	private String productName;
	private String warehouse;

	/////////////////////////////////////////////////////////////////////
	//////////////////////////// Constructors ///////////////////////////
	////////////////////////////////////////////////////////////////////
	public Product(int id, String productName, String warehouse) {

		this.id = id;
		this.productName = productName;
		this.warehouse = warehouse;

	}
	
	public Product( String productName, String warehouse) {

		this.productName = productName;
		this.warehouse = warehouse;

	}

	/////////////////////////////////////////////////////////////////////
	/////////////////////// getters & setters //////////////////////////
	////////////////////////////////////////////////////////////////////
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	
	
}
