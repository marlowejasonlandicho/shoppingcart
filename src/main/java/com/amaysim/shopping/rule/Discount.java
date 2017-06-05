package com.amaysim.shopping.rule;

import com.amaysim.shopping.data.Product;

public abstract class Discount extends Pricing {

	protected double discountAmount;

	public Discount(Product product, double discountAmount) {
		super();
		this.product = product;
		this.discountAmount = discountAmount;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

}
