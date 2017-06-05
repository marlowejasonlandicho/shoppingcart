package com.amaysim.shopping;

import java.util.List;
import java.util.Map;

import com.amaysim.shopping.data.Product;
import com.amaysim.shopping.rule.PromoCode;

public interface Cart {
	public void add(Product product);

	public void add(Product product, PromoCode promoCode);
	
	public void addPromoCode(PromoCode promoCode);

	public double total();

	public Map<String, Long> items();

	public List<Product> itemList();

}
