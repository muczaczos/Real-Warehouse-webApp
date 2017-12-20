package com.muczo.mvc.warehouse;

public class ProductList {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return product + " " + qty;
	}

	private String product;
	private int qty;
	
	public ProductList(String product, int qty){
		this.product = product;
		this.qty = qty;
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
