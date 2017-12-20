package com.muczo.mvc.warehouse;

import java.io.Serializable;

public class PriceList implements Serializable{

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////// Main variables///////////////////////
	/////////////////////////////////////////////////////////////////////
	

	private Boolean check;
	private int id;
	private String customer;
	private String product;
	private Double price;



	/////////////////////////////////////////////////////////////////////
	//////////////////////////// Constructors ///////////////////////////
	////////////////////////////////////////////////////////////////////
	public PriceList(Boolean check, int id, String customer, String product, Double price) {

		this.check = check;
		this.id = id;
		this.customer = customer;
		this.product = product;
		this.price = price;
		
	}
	
	public PriceList(Boolean check, String customer, String product, Double price) {

		this.check = check;
		this.customer = customer;
		this.product = product;
		this.price = price;
		
	}

	/////////////////////////////////////////////////////////////////////
	/////////////////////// getters & setters //////////////////////////
	////////////////////////////////////////////////////////////////////
	public Boolean getCheck(){
		return check;
	}
	public void setCheck(Boolean check){
		this.check = check;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}


}

