package com.amaysim.shopping.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amaysim.shopping.data.Product;
import com.amaysim.shopping.factory.ProductFactory;
import com.amaysim.shopping.rule.BulkDiscount;
import com.amaysim.shopping.rule.Freebie;
import com.amaysim.shopping.rule.LimitedDiscount;
import com.amaysim.shopping.rule.Pricing;
import com.amaysim.shopping.rule.PromoCode;

public class PricingEngine {
	private static final int PERCENT = 100;
	private List<Product> productList;
	private List<Pricing> pricingRules;
	private PromoCode promoCode;
	private double total;

	public PricingEngine(List<Product> productList, List<Pricing> pricingRules) {
		this.productList = productList;
		this.pricingRules = pricingRules;
	}

	public void addProduct(Product product) {
		this.productList.add(product);
	}

	public void addProduct(Product product, PromoCode promoCode) {
		this.productList.add(product);
		this.promoCode = promoCode;
	}

	public void addPromoCode(PromoCode promoCode) {
		this.promoCode = promoCode;
	}

	public double computeTotal() {

		// Loop pricing rules
		for (Pricing pricing : this.pricingRules) {

			String pricingProductCode = pricing.getProduct().getProductCode();
			long productCounter = this.productList.stream().filter(p -> p.getProductCode().equals(pricingProductCode))
					.count();

			// Buy 3 take 2
			if (pricing instanceof LimitedDiscount) {

				long discounted = productCounter / 3;
				this.productList.stream().filter(p -> p.getProductCode().equals(pricingProductCode)).limit(discounted)
						.forEach(p -> p.setPrice(0.0f));

			} // Bulk
			else if (pricing instanceof BulkDiscount) {
				BulkDiscount bulkDiscount = (BulkDiscount) pricing;
				productCounter = this.productList.stream().filter(p -> p.getProductCode().equals(pricingProductCode))
						.count();
				if (productCounter > 3) {
					this.productList.stream().filter(p -> p.getProductCode().equals(pricingProductCode))
							.forEach(p -> p.setPrice(bulkDiscount.getDiscountAmount()));
				}

			} // Bundle or Freebie
			else if (pricing instanceof Freebie) {
				Freebie freebie = (Freebie) pricing;
				productCounter = this.productList.stream().filter(p -> p.getProductCode().equals(pricingProductCode))
						.count();
				for (int i = 0; i < productCounter; i++) {
					Product freebieProduct = ProductFactory
							.createStaticProduct(freebie.getBundledProduct().getProductCode());
					freebieProduct.setPrice(0.0);
					this.productList.add(freebieProduct);
				}

			}
		}

		// Promo Code
		if (this.promoCode != null) {
			double adjustedTotal = this.productList.stream().mapToDouble(p -> p.getPrice()).sum()
					* ((PERCENT - promoCode.getPercentageDiscount()) / PERCENT);
			this.total = adjustedTotal;
		} else {
			this.total = this.productList.stream().mapToDouble(p -> p.getPrice()).sum();
		}

		this.total = BigDecimal.valueOf(this.total).setScale(2, RoundingMode.HALF_UP).doubleValue();

		return this.total;

	}

	public Map<String, Long> items() {
		Map<String, Long> productMap = this.productList.stream()
				.collect(Collectors.groupingBy(Product::getProductName, Collectors.counting()));
		return productMap;
	}
}
