package com.amaysim.shopping.rule;

import com.amaysim.shopping.data.Product;

public class LimitedDiscount extends Discount {

	private int quantityDiscounted;
	private int quantityElligible;

	public LimitedDiscount(Product product, int quantityDiscounted, int quantityElligible, double discountAmount) {
		super(product, discountAmount);
		this.product = product;
		this.quantityDiscounted = quantityDiscounted;
		this.quantityElligible = quantityElligible;
	}

	public int getDiscountedQuantity() {
		return quantityDiscounted;
	}

	public void setDiscountedQuantity(int discountedQuantity) {
		this.quantityDiscounted = discountedQuantity;
	}

	public int getQuantityElligible() {
		return quantityElligible;
	}

	public void setQuantityElligible(int quantityElligible) {
		this.quantityElligible = quantityElligible;
	}

}
