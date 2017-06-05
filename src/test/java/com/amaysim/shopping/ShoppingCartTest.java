package com.amaysim.shopping;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.amaysim.shopping.factory.ProductFactory;
import com.amaysim.shopping.rule.PricingRuleBuilder;
import com.amaysim.shopping.rule.PromoCode;

public class ShoppingCartTest {
	PricingRuleBuilder pricingRuleBuilder;

	public ShoppingCartTest() {
		pricingRuleBuilder = new PricingRuleBuilder();
		pricingRuleBuilder.addBulkDiscount("ult_large", 3, 39.90);
		pricingRuleBuilder.addLimitedDiscount("ult_small", 1, 3, 0.0);
		pricingRuleBuilder.addFreebie("ult_medium", "1gb");
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testScenario1() {

		Cart cart = ShoppingCart.newCart(pricingRuleBuilder.getPricingRules());

		cart.add(ProductFactory.createStaticProduct("ult_small"));
		cart.add(ProductFactory.createStaticProduct("ult_small"));
		cart.add(ProductFactory.createStaticProduct("ult_small"));

		cart.add(ProductFactory.createStaticProduct("ult_large"));

		assertTrue(cart.total() == 94.7);
		assertTrue(cart.items().containsKey("Unlimited 1GB"));
		assertTrue(cart.items().containsKey("Unlimited 5GB"));

		assertTrue(cart.items().get("Unlimited 1GB") == 3);
		assertTrue(cart.items().get("Unlimited 5GB") == 1);

	}

	@Test
	public void testScenario2() {

		Cart cart = ShoppingCart.newCart(pricingRuleBuilder.getPricingRules());

		cart.add(ProductFactory.createStaticProduct("ult_small"));
		cart.add(ProductFactory.createStaticProduct("ult_small"));

		cart.add(ProductFactory.createStaticProduct("ult_large"));
		cart.add(ProductFactory.createStaticProduct("ult_large"));
		cart.add(ProductFactory.createStaticProduct("ult_large"));
		cart.add(ProductFactory.createStaticProduct("ult_large"));

		assertTrue(cart.total() == 209.4);
		assertTrue(cart.items().containsKey("Unlimited 1GB"));
		assertTrue(cart.items().containsKey("Unlimited 5GB"));
		assertTrue(cart.items().get("Unlimited 1GB") == 2);
		assertTrue(cart.items().get("Unlimited 5GB") == 4);

	}

	@Test
	public void testScenario3() {

		Cart cart = ShoppingCart.newCart(pricingRuleBuilder.getPricingRules());

		cart.add(ProductFactory.createStaticProduct("ult_small"));

		cart.add(ProductFactory.createStaticProduct("ult_medium"));
		cart.add(ProductFactory.createStaticProduct("ult_medium"));

		assertTrue(cart.total() == 84.7);
		assertTrue(cart.items().containsKey("Unlimited 1GB"));
		assertTrue(cart.items().containsKey("Unlimited 2GB"));
		assertTrue(cart.items().containsKey("1 GB Data-pack"));

		assertTrue(cart.items().get("Unlimited 1GB") == 1);
		assertTrue(cart.items().get("Unlimited 2GB") == 2);
		assertTrue(cart.items().get("1 GB Data-pack") == 2);

	}

	@Test
	public void testScenario4() {

		Cart cart = ShoppingCart.newCart(pricingRuleBuilder.getPricingRules());

		cart.add(ProductFactory.createStaticProduct("ult_small"));

		cart.add(ProductFactory.createStaticProduct("1gb"), new PromoCode("I<3AMAYSIM", 10, 0));

		assertTrue(cart.total() == 31.32);
		assertTrue(cart.items().containsKey("Unlimited 1GB"));
		assertTrue(cart.items().containsKey("1 GB Data-pack"));

		assertTrue(cart.items().get("Unlimited 1GB") == 1);
		assertTrue(cart.items().get("1 GB Data-pack") == 1);
	}

	@Test
	public void testScenario5() {

		Cart cart = ShoppingCart.newCart(pricingRuleBuilder.getPricingRules());

		cart.add(ProductFactory.createStaticProduct("ult_small"));
		cart.add(ProductFactory.createStaticProduct("ult_small"));
		cart.add(ProductFactory.createStaticProduct("ult_small"));
		cart.add(ProductFactory.createStaticProduct("ult_small"));
		cart.add(ProductFactory.createStaticProduct("ult_small"));
		cart.add(ProductFactory.createStaticProduct("ult_small"));
		cart.add(ProductFactory.createStaticProduct("ult_small"));

		cart.add(ProductFactory.createStaticProduct("ult_large"));
		cart.add(ProductFactory.createStaticProduct("ult_large"));
		cart.add(ProductFactory.createStaticProduct("ult_large"));
		cart.add(ProductFactory.createStaticProduct("ult_large"));

		cart.add(ProductFactory.createStaticProduct("ult_medium"));
		cart.add(ProductFactory.createStaticProduct("ult_medium"), new PromoCode("I<3AMAYSIM", 10, 0));

		assertTrue(cart.total() == 309.51);
		assertTrue(cart.items().containsKey("Unlimited 1GB"));
		assertTrue(cart.items().containsKey("1 GB Data-pack"));
		assertTrue(cart.items().containsKey("Unlimited 2GB"));
		assertTrue(cart.items().containsKey("Unlimited 5GB"));

		assertTrue(cart.items().get("Unlimited 1GB") == 7);
		assertTrue(cart.items().get("1 GB Data-pack") == 2);
		assertTrue(cart.items().get("Unlimited 2GB") == 2);
		assertTrue(cart.items().get("Unlimited 5GB") == 4);

	}

	@AfterClass
	public static void tearDown() throws Exception {
	}

}
