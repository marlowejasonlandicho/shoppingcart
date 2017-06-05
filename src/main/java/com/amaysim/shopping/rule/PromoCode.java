package com.amaysim.shopping.rule;

public class PromoCode extends Pricing {
	private String code;
	private double percentageDiscount;
	private double amount;

	public PromoCode() {
	}

	public PromoCode(String code, double percentageDiscount, double amount) {
		super();
		this.code = code;
		this.percentageDiscount = percentageDiscount;
		this.amount = amount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPercentageDiscount() {
		return percentageDiscount;
	}

	public void setPercentageDiscount(double percentageDiscount) {
		this.percentageDiscount = percentageDiscount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
