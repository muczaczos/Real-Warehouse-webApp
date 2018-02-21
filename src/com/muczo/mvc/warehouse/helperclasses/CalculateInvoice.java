package com.muczo.mvc.warehouse.helperclasses;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import com.muczo.mvc.warehouse.blueprint.Document;
import com.muczo.mvc.warehouse.blueprint.ProductList;
import com.muczo.mvc.warehouse.db.Documents1DbUtil;
import com.muczo.mvc.warehouse.db.PriceDbUtil;

public class CalculateInvoice {
	
	private Double grossAmount;
	private int startDocRange;
	private int endDocRange;
	
	public String calculate(String[] ids, String theCustomer, PriceDbUtil priceDbUtil,
			Documents1DbUtil warehouseDbUtil) throws Exception{
		
		
		ArrayList<String> productList = new ArrayList<String>();
		ArrayList<String> product = new ArrayList<String>();
		ArrayList<Integer> qty = new ArrayList<Integer>();
		ArrayList<ProductList> sumList = new ArrayList<ProductList>();
		ArrayList<Document> documents = new ArrayList<Document>();
		String grossAmount;

		
		
		for (String id : ids) {

			Document document = warehouseDbUtil.getDocument(id);
			
			documents.add(document);

			product.add(document.getProduct1());
			qty.add(document.getQty1());

			if (document.getQty2() > 0) {
				product.add(document.getProduct2());
				qty.add(document.getQty2());

				if (document.getQty3() > 0) {
					product.add(document.getProduct3());
					qty.add(document.getQty3());

					if (document.getQty4() > 0) {
						product.add(document.getProduct4());
						qty.add(document.getQty4());

						if (document.getQty5() > 0) {
							product.add(document.getProduct5());
							qty.add(document.getQty5());

							if (document.getQty6() > 0) {
								product.add(document.getProduct6());
								qty.add(document.getQty6());

								if (document.getQty7() > 0) {
									product.add(document.getProduct7());
									qty.add(document.getQty7());
								}
							}
						}
					}
				}
			}

		}

		for (int j = 0; j < product.size(); j++) {

			if (!productList.contains(product.get(j))) {

				for (int k = j; k < product.size() - 1; k++) {

					if (product.get(j).equals(product.get(k + 1))) {

						int suma = qty.get(j) + qty.get(k + 1);
						qty.set(j, suma);
					}
				}
				productList.add(product.get(j));
				sumList.add(new ProductList(product.get(j), qty.get(j)));
			}

		}
		StringBuilder sb = new StringBuilder();
		Double sum = 0.00;
		
		
		this.startDocRange = documents.get(0).getNoOfDoc();
		this.endDocRange = documents.get(documents.size()-1).getNoOfDoc();
		int id = 1;
		
		String docs = "Dotyczy wz: ";
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		for(int i=0; i<documents.size(); i++) {
			docs += documents.get(i).getNoOfDoc() +"/K/"+ year + ", ";
		}
		sb.append(docs);
		sb.append("\n");
		sb.append("\n");
		for (ProductList element : sumList) {
			sb.append(id + ". ");
			sb.append(element.getProduct() + "    szt." + element.getQty() + "  x   "
					+ priceDbUtil.loadPriceForCustomer(element.getProduct(), theCustomer) + "z³ \t = \t");
			sb.append(element.getQty() * priceDbUtil.loadPriceForCustomer(element.getProduct(), theCustomer));
			sum += element.getQty() * priceDbUtil.loadPriceForCustomer(element.getProduct(), theCustomer);
			sb.append("\n");
			id++;
		}

		sum *= 1.23;

		// Rounding number to up and two digits after dot.
		NumberFormat fmt = NumberFormat.getNumberInstance();
		fmt.setMaximumFractionDigits(2);
		fmt.setRoundingMode(RoundingMode.CEILING);
		sb.append("\n Gross amount: " + fmt.format(sum) + "\n");
		grossAmount = fmt.format(sum).toString();
	    this.grossAmount = round(sum,2);
	    
		// Convert from NumberFormat to Double
		try {
			Double myNumber = 0.00;
			myNumber = fmt.parse(grossAmount).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String invoice = sb.toString();
		
		return invoice;
		
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public Double getGrossAmount() {
		return grossAmount;
	}

	public int getStartDocRange() {
		return startDocRange;
	}

	public int getEndDocRange() {
		return endDocRange;
	}
	
	

}
