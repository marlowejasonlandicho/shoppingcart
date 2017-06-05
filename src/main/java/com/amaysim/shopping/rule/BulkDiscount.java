package com.amaysim.shopping.rule;

import com.amaysim.shopping.data.Product;

public class BulkDiscount extends Discount {

	private int quantityElligible;

	public BulkDiscount(Product product, int quantityElligible, double discountAmount) {
		super(product, discountAmount);
		this.quantityElligible = quantityElligible;
	}

	public int getQuantityElligible() {
		return quantityElligible;
	}

	public void setQuantityElligible(int quantityElligible) {
		this.quantityElligible = quantityElligible;
	}

}
