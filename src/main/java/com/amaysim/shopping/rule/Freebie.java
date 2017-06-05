package com.amaysim.shopping.rule;

import com.amaysim.shopping.data.Product;

public class Freebie extends Pricing {

	private Product bundledProduct;

	public Freebie(Product product, Product bundledProduct) {
		super();
		this.product = product;
		this.bundledProduct = bundledProduct;
	}

	public Product getBundledProduct() {
		return bundledProduct;
	}

	public void setBundledProduct(Product bundledProduct) {
		this.bundledProduct = bundledProduct;
	}

}
