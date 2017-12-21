package com.muczo.mvc.warehouse;

import java.io.Serializable;

public class Document2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3206798103583708156L;
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////// Main variables///////////////////////
	/////////////////////////////////////////////////////////////////////
	private int id;
	private String provider;
	private String product;
	private int qty;



	/////////////////////////////////////////////////////////////////////
	//////////////////////////// Constructors ///////////////////////////
	////////////////////////////////////////////////////////////////////
	public Document2(int id, String provider, String product, int qty) {

	
		this.id = id;
		this.provider = provider;
		this.product = product;
		this.qty = qty;
		
	}
	
	public Document2(String provider, String product, int qty) {

		this.provider = provider;
		this.product = product;
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

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}



}
