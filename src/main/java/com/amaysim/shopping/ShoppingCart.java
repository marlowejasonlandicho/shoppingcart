package com.amaysim.shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.amaysim.shopping.data.Product;
import com.amaysim.shopping.engine.PricingEngine;
import com.amaysim.shopping.rule.Pricing;
import com.amaysim.shopping.rule.PromoCode;

public class ShoppingCart implements Cart {

	private PricingEngine pricingEngine;
	private List<Product> cartItems = new ArrayList<>();
	private double cartTotal;

	public static Cart newCart(List<Pricing> pricingRules) {
		return new ShoppingCart(pricingRules);
	}

	private ShoppingCart(List<Pricing> pricingRules) {
		pricingEngine = new PricingEngine(this.cartItems, pricingRules);
	}

	@Override
	public void add(Product product) {
		cartItems.add(product);
	}

	@Override
	public void add(Product product, PromoCode promoCode) {
		cartItems.add(product);
		pricingEngine.addPromoCode(promoCode);
	}

	@Override
	public double total() {
		this.cartTotal = pricingEngine.computeTotal();
		return this.cartTotal;
	}

	@Override
	public Map<String, Long> items() {
		return pricingEngine.items();
	}

	@Override
	public List<Product> itemList() {
		return this.cartItems;
	}

	@Override
	public void addPromoCode(PromoCode promoCode) {
		pricingEngine.addPromoCode(promoCode);
	}

}
