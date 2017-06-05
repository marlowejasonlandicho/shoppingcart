package com.amaysim.shopping.factory;

import java.util.ArrayList;
import java.util.List;

import com.amaysim.shopping.data.Product;
import com.amaysim.shopping.rule.PromoCode;

public final class ProductFactory {

	public static Product createStaticProduct(String productCode) {
		Product product = new Product();
		product.setProductCode(productCode);

		if (productCode == "ult_small") {
			product.setProductName("Unlimited 1GB");
			product.setPrice(24.90);
		} else if (productCode == "ult_medium") {
			product.setProductName("Unlimited 2GB");
			product.setPrice(29.90);
		} else if (productCode == "ult_large") {
			product.setProductName("Unlimited 5GB");
			product.setPrice(44.90);
		} else if (productCode == "1gb") {
			product.setProductName("1 GB Data-pack");
			product.setPrice(9.90);
		}
		return product;
	}

	public static List<Product> getStaticProducts() {

		List<Product> products = new ArrayList<>();

		Product product = new Product();
		product.setProductCode("ult_small");
		product.setProductName("Unlimited 1GB");
		product.setPrice(24.90);
		products.add(product);

		product = new Product();
		product.setProductCode("ult_medium");
		product.setProductName("Unlimited 2GB");
		product.setPrice(29.90);
		products.add(product);

		product = new Product();
		product.setProductCode("ult_large");
		product.setProductName("Unlimited 5GB");
		product.setPrice(44.90);
		products.add(product);

		product = new Product();
		product.setProductCode("1gb");
		product.setProductName("1 GB Data-pack");
		product.setPrice(9.90);
		products.add(product);

		return products;
	}

	public static List<PromoCode> getStaticPromos() {

		List<PromoCode> promos = new ArrayList<>();

		PromoCode promo = new PromoCode();
		promo.setCode("I<3AMAYSIM");
		promo.setPercentageDiscount(10);
		promo.setAmount(0);
		promos.add(promo);

		return promos;
	}

	public static Product createProduct(String productCode) {
		Product product = new Product();
		product.setProductCode(productCode);

		return product;
	}
}
