package com.muczo.mvc.warehouse.blueprint;

import java.io.Serializable;

public class Invoice implements Serializable {

	private static final long serialVersionUID = -3206798103583708156L;
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////// Main variables///////////////////////
	/////////////////////////////////////////////////////////////////////
	private int id;
	private String customer;
	private String date;
	private int invNumber;
	private int startDocRange;
	private int endDocRange;
	private Double grossAmount;

	/////////////////////////////////////////////////////////////////////
	//////////////////////////// Constructors ///////////////////////////
	////////////////////////////////////////////////////////////////////
	public Invoice(String customer, String date, int invNumber, int startDocRange, int endDocRange,
			Double grossAmount) {
		super();
		this.customer = customer;
		this.date = date;
		this.invNumber = invNumber;
		this.startDocRange = startDocRange;
		this.endDocRange = endDocRange;
		this.grossAmount = grossAmount;
	}

	public Invoice(int id, String customer, String date, int invNumber, int startDocRange, int endDocRange,
			Double grossAmount) {
		this.id = id;
		this.customer = customer;
		this.date = date;
		this.invNumber = invNumber;
		this.startDocRange = startDocRange;
		this.endDocRange = endDocRange;
		this.grossAmount = grossAmount;
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

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getInvNumber() {
		return invNumber;
	}

	public void setInvNumber(int invNumber) {
		this.invNumber = invNumber;
	}

	public int getStartDocRange() {
		return startDocRange;
	}

	public void setStartDocRange(int startDocRange) {
		this.startDocRange = startDocRange;
	}

	public Double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public int getEndDocRange() {
		return endDocRange;
	}


	
}
