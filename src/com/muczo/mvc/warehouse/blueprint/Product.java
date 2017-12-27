package com.muczo.mvc.warehouse.blueprint;
import java.io.Serializable;

public class Product implements Serializable {

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////// Main variables///////////////////////
	/////////////////////////////////////////////////////////////////////
	private static int count = 0;

	private int id;
	private String productName;
	private String warehouse;
	private int qty;

	/////////////////////////////////////////////////////////////////////
	//////////////////////////// Constructors ///////////////////////////
	////////////////////////////////////////////////////////////////////
	public Product(int id, String productName, String warehouse, int qty) {

		this.id = id;
		this.productName = productName;
		this.warehouse = warehouse;
		this.qty = qty;

	}
	
	public Product( String productName, String warehouse, int qty) {

		this.productName = productName;
		this.warehouse = warehouse;
		this.qty = qty;

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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	

	
	
}
