package com.amaysim.shopping.rule;

import com.amaysim.shopping.data.Product;

public abstract class Pricing {

	protected Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
