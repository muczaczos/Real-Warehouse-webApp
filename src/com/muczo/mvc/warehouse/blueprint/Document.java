package com.muczo.mvc.warehouse.blueprint;
import java.io.Serializable;

public class Document implements Serializable {

	/////////////////////////////////////////////////////////////////////
	/////////////////////////////// Main variables///////////////////////
	/////////////////////////////////////////////////////////////////////
	//private static int count = 1;

	private Boolean check;
	private int id;
	private String customer;
	private String reciepient;
	private int noOfDoc;
	private String date;
	private String product1;
	private int qty1, qty2, qty3, qty4, qty5, qty6, qty7;
	private String product2;
	private String product3;
	private String product4;
	private String product5;
	private String product6;
	private String product7;
	private String info1;
	private String info2;
	private String info3;
	private String info4;
	private String info5;
	private String info6;
	private String info7;


	/////////////////////////////////////////////////////////////////////
	//////////////////////////// Constructors ///////////////////////////
	////////////////////////////////////////////////////////////////////
	public Document(Boolean check, int id, String customer, String reciepient, int noOfDoc, String date, String product1, int qty1,
			String product2, int qty2, String product3, int qty3, String product4, int qty4, String product5, int qty5,
			String product6, int qty6, String product7, int qty7, String info1, String info2, String info3, String info4,
			String info5, String info6, String info7) {

		this.check = check;
		this.id = id;
		this.customer = customer;
		this.reciepient = reciepient;
		this.noOfDoc = noOfDoc;
		this.date = date;
		this.product1 = product1;
		this.qty1 = qty1;
		this.product2 = product2;
		this.qty2 = qty2;
		this.product3 = product3;
		this.qty3 = qty3;
		this.product4 = product4;
		this.qty4 = qty4;
		this.product5 = product5;
		this.qty5 = qty5;
		this.product6 = product6;
		this.qty6 = qty6;
		this.product7 = product7;
		this.qty7 = qty7;
		this.info1 = info1;
		this.info2 = info2;
		this.info3 = info3;
		this.info4 = info4;
		this.info5 = info5;
		this.info6 = info6;
		this.info7 = info7;

	}

	public Document(Boolean check, String customer, String reciepient, int noOfDoc, String date, String product1, int qty1,
			String product2, int qty2, String product3, int qty3, String product4, int qty4, String product5, int qty5,
			String product6, int qty6, String product7, int qty7, String info1, String info2, String info3, String info4,
			String info5, String info6, String info7) {

		this.check = check;
		this.customer = customer;
		this.reciepient = reciepient;
		this.noOfDoc = noOfDoc;
		this.date = date;
		this.product1 = product1;
		this.qty1 = qty1;
		this.product2 = product2;
		this.qty2 = qty2;
		this.product3 = product3;
		this.qty3 = qty3;
		this.product4 = product4;
		this.qty4 = qty4;
		this.product5 = product5;
		this.qty5 = qty5;
		this.product6 = product6;
		this.qty6 = qty6;
		this.product7 = product7;
		this.qty7 = qty7;
		this.info1 = info1;
		this.info2 = info2;
		this.info3 = info3;
		this.info4 = info4;
		this.info5 = info5;
		this.info6 = info6;
		this.info7 = info7;

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

	public String getReciepient() {
		return reciepient;
	}

	public void setReciepient(String reciepient) {
		this.reciepient = reciepient;
	}

	public int getNoOfDoc() {
		return noOfDoc;
	}

	public void setNoOfDoc(int noOfDoc) {
		this.noOfDoc = noOfDoc;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProduct1() {
		return product1;
	}

	public void setProduct1(String product1) {
		this.product1 = product1;
	}

	public int getQty1() {
		return qty1;
	}

	public void setQty1(int qty1) {
		this.qty1 = qty1;
	}

	public String getProduct2() {
		return product2;
	}

	public void setProduct2(String product2) {
		this.product2 = product2;
	}

	public int getQty2() {
		return qty2;
	}

	public void setQty2(int qty2) {
		this.qty2 = qty2;
	}

	public String getProduct3() {
		return product3;
	}

	public void setProduct3(String product3) {
		this.product3 = product3;
	}

	public int getQty3() {
		return qty3;
	}

	public void setQty3(int qty3) {
		this.qty3 = qty3;
	}

	public String getProduct4() {
		return product4;
	}

	public void setProduct4(String product4) {
		this.product4 = product4;
	}

	public int getQty4() {
		return qty4;
	}

	public void setQty4(int qty4) {
		this.qty4 = qty4;
	}

	public String getProduct5() {
		return product5;
	}

	public void setProduct5(String product5) {
		this.product5 = product5;
	}

	public int getQty5() {
		return qty5;
	}

	public void setQty5(int qty5) {
		this.qty5 = qty5;
	}

	public String getProduct6() {
		return product6;
	}

	public void setProduct6(String product6) {
		this.product6 = product6;
	}

	public int getQty6() {
		return qty6;
	}

	public void setQty6(int qty6) {
		this.qty6 = qty6;
	}

	public String getProduct7() {
		return product7;
	}

	public void setProduct7(String product7) {
		this.product7 = product7;
	}

	public int getQty7() {
		return qty7;
	}

	public void setQty7(int qty7) {
		this.qty7 = qty7;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public String getInfo3() {
		return info3;
	}

	public void setInfo3(String info3) {
		this.info3 = info3;
	}

	public String getInfo4() {
		return info4;
	}

	public void setInfo4(String info4) {
		this.info4 = info4;
	}

	public String getInfo5() {
		return info5;
	}

	public void setInfo5(String info5) {
		this.info5 = info5;
	}

	public String getInfo6() {
		return info6;
	}

	public void setInfo6(String info6) {
		this.info6 = info6;
	}

	public String getInfo7() {
		return info7;
	}

	public void setInfo7(String info7) {
		this.info7 = info7;
	}



	

}
