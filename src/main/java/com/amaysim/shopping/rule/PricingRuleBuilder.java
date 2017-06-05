package com.amaysim.shopping.rule;

import java.util.ArrayList;
import java.util.List;

import com.amaysim.shopping.data.Product;
import com.amaysim.shopping.factory.ProductFactory;

public class PricingRuleBuilder {

	private List<Pricing> pricingRules = new ArrayList<Pricing>();

	public PricingRuleBuilder() {
	}
	
	public void addLimitedDiscount(String productCode, int quantityDiscounted, int quantityElligible,
			double discountAmount) {
		Product product = ProductFactory.createStaticProduct(productCode);
		LimitedDiscount limitedDiscount = new LimitedDiscount(product, quantityDiscounted, quantityElligible,
				discountAmount);
		pricingRules.add(limitedDiscount);
	}

	public void addBulkDiscount(String productCode, int quantityElligible, double discountAmount) {
		Product product = ProductFactory.createStaticProduct(productCode);
		BulkDiscount bulkDiscount = new BulkDiscount(product, quantityElligible, discountAmount);
		pricingRules.add(bulkDiscount);
	}

	public void addFreebie(String productCode, String bundledProductCode) {
		Product product = ProductFactory.createStaticProduct(productCode);
		Product bundledProduct = ProductFactory.createStaticProduct(bundledProductCode);
		Freebie freebie = new Freebie(product, bundledProduct);
		pricingRules.add(freebie);
	}

	public List<Pricing> getPricingRules() {
		return pricingRules;
	}
}
