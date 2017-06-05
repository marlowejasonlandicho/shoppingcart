package com.amaysim.shopping.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.amaysim.shopping.Cart;
import com.amaysim.shopping.ShoppingCart;
import com.amaysim.shopping.data.Product;
import com.amaysim.shopping.factory.ProductFactory;
import com.amaysim.shopping.rule.PricingRuleBuilder;
import com.amaysim.shopping.rule.PromoCode;

public class ShoppingCartApp {

	public static void main(String[] args) {

		PricingRuleBuilder pricingRuleBuilder = new PricingRuleBuilder();
		pricingRuleBuilder.addBulkDiscount("ult_large", 3, 39.90);
		pricingRuleBuilder.addLimitedDiscount("ult_small", 1, 3, 0.0);
		pricingRuleBuilder.addFreebie("ult_medium", "1gb");

		Cart cart = ShoppingCart.newCart(pricingRuleBuilder.getPricingRules());

		List<Product> products = ProductFactory.getStaticProducts();

		System.out.println("PRODUCT CATALOGUE:\n" + "Product Code\t" + "Product Name\t" + "Price\t");
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			System.out.println(
					product.getProductCode() + "\t" + product.getProductName() + "\t" + product.getPrice() + "\t");
		}
		System.out.println(
				"\nPlease set number of products you wish to avail as shown above" + "Press ENTER key to continue...");

		for (int i = 0; i < products.size(); i++) {
			int numOfProducts = 0;

			Product product = products.get(i);

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("How many " + product.getProductName() + " would you like to avail? ");

			try {
				numOfProducts = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				// Do nothing
			}

			for (int j = 0; j < numOfProducts; j++) {
				cart.add(ProductFactory.createStaticProduct(product.getProductCode()));
			}

		}

		List<PromoCode> promos = ProductFactory.getStaticPromos();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Would you like to avail a promo? Enter promo code here: ");
		try {
			String promoCodeInput = br.readLine();
			if (promoCodeInput != null) {
				for (int i = 0; i < promos.size(); i++) {
					PromoCode promoCode = (PromoCode) promos.get(i);
					if (promoCode.getCode().equals(promoCodeInput)) {
						System.out.println(promoCodeInput + " Promo Applied!");
						cart.addPromoCode(promoCode);
					}
				}
			}
		} catch (IOException e) {
			// Do nothing
		}

		System.out.println("\nTotal amount:\t" + cart.total());
		System.out.println("\nYou have availed the following:");
		Map<String, Long> items = cart.items();
		items.keySet().forEach(s -> System.out.println(items.get(s) + " X " + s));
	}

}
