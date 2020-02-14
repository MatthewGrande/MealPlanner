Feature: See meal suggestions based on given ingredients

As a user of the meal planning app, I want to see suggested meals using the ingredients I gave the system.

	Scenario: Input one ingredient
	
	I should see meal suggestions with that ingredient.
	
		Given I have already created an account
		When I insert one ingredient like carrot
		Then I should receive meal suggestions including carrots
			
			
	Scenario: Input a list of ingredients
	
	I should see meal suggestions with that list of ingredients.
	
		Given I have already created an account
		When I insert a list of ingredients like carrot, apple and rice
		Then I should receive meal suggestions including carrot, apple and rice
	 

	Scenario: Invalid ingredient

	I should see an error prompt saying that this is not an ingredient.
    
    	Given I have already created an account
		When I insert shoe
		Then I should receive an error prompt saying this is not a recognized ingredient
        
    
    Scenario: Invalid non string ingredient

	I should see an error prompt saying that this is not an ingredient.
    
    	Given I have already created an account
		When I insert 123
		Then I should receive an error prompt saying this is not a recognized ingredient

           