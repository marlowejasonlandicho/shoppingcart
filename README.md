## Synopsis

Shopping Cart Exercise.


## Code Example

		PricingRuleBuilder pricingRuleBuilder = new PricingRuleBuilder();
		pricingRuleBuilder.addBulkDiscount("ult_large", 3, 39.90);
		pricingRuleBuilder.addLimitedDiscount("ult_small", 1, 3, 0.0);
		pricingRuleBuilder.addFreebie("ult_medium", "1gb");
    
	    	Cart cart = ShoppingCart.newCart(pricingRuleBuilder.getPricingRules());

		cart.add(ProductFactory.createStaticProduct("ult_small"));
		cart.add(ProductFactory.createStaticProduct("ult_small"));
		cart.add(ProductFactory.createStaticProduct("ult_small"));

		cart.add(ProductFactory.createStaticProduct("ult_large"));
	   	cart.add(ProductFactory.createStaticProduct("1gb"), new PromoCode("I<3AMAYSIM", 10, 0));
	    	
		System.out.println(cart.total()); //Total amount
	    	System.out.println(cart.items()); //List contents

## Installation

Make sure that Java 8 is installed on your machine and can be invoked on the command line. 
Maven is also required to build and test the project. But by default, the application binaries are under ./target folder.

You can get Java 8 here: 
	http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

And Maven here: 
	https://maven.apache.org/

## Runninng application

Go to ./target folder and run the application via java command:

  java -jar shoppingcart-0.0.1-SNAPSHOT.jar

Instructions are also provided upon running the application

## Tests

Tests can be run via Maven. 
Test results are stored under ./target/surefire-reports
