Feature: Logging Past Meals
	
As a MealPlanner Customer
I would like to log past meals
So that I can keep track of my daily consumption
	
  Scenario: User Julianna wants to log a meal she has eaten (Normal Flow)
    Given The following list of meals exists in the MealPlanner database
          | <Recipe>            | <DietType> | <Ingredients> 																				 |
     		  | pestoKalePasta      | vegetarian |pesto, creamCheese, rapeseedoil, redOnion, kale, pasta |
     		  | tofuSoup            | n/a        |mushrooms, brownrice, noodles, redOnion, rapeseedoil   |
     		  | smokedsalamonGratin | n/a        |spinach, butter, salmon, cream                         |
    When Julianna adds <Recipe> pestoKalePasta to her logged meals
    			| <Recipe>            | <servings> | <Day>       |
    			| pestoKalePasta      |     2      |    today    |		 
    Then The list of user "Juliana's" logged meals is updated.
	  			| <Meal>            | <CalorieCount> | <Day>       |
    			| pestoKalePasta    | 400            |  today    	 |
    And the message "400 calories have been consumed today!" is returned.

  Scenario Outline: User "Julianna" wants to log an item she has eaten that does not exist as a meal in the database (Alternate Flow)
    Given The following list of meals exists in the MealPlanner database
          | <Recipe>            | <DietType> | <Ingredients> 																				 |
     		  | pestoKalePasta      | vegetarian |pesto, creamCheese, rapeseedoil, redOnion, kale, pasta |
     		  | tofuSoup            | n/a        |mushrooms, brownrice, noodles, redOnion, rapeseedoil   |
     		  | smokedsalamonGratin | n/a        |spinach, butter, salmon, cream  
    When the user "Julianna" logs a meal she has eaten by adding an individual <Ownedingredient>
    	  	| <Meal>      | <Quantity> | <Day>       |
    			| Banana      | 1          |  today    	 |
 		Then The list of user "Juliana's" logged meals is updated.
	  			| <Meal>            | <CalorieCount> | <Day>       |
    			| pestoKalePasta    | 400            |  today    	 |
    			| Banana            | 40             |  today    	 |
    And the message "440 calories have been consumed today!" is returned.

  
    Scenario Outline: User "Julianna" wants to log a meal that does not exist as a meal in the database (Alternate Flow)
    Given The following list of meals exists in the MealPlanner database
          | <Recipe>            | <DietType> | <Ingredients> 																				 |
     		  | pestoKalePasta      | vegetarian |pesto, creamCheese, rapeseedoil, redOnion, kale, pasta |
     		  | tofuSoup            | n/a        |mushrooms, brownrice, noodles, redOnion, rapeseedoil   |
     		  | smokedsalamonGratin | n/a        |spinach, butter, salmon, cream  
    When the user "Julianna" logs a meal she has eaten by adding an individual <Ownedingredient>
    	  	| <Meal>      | <Quantity> | <Day>       |
    			| Oatmeal     | 500        |  today    	 |
    			| Apple       | 1          |  today    	 |
 		Then The list of user "Juliana's" logged meals is updated.
	  			| <Meal>            | <CalorieCount> | <Day>       |
    			| pestoKalePasta    | 400            |  today    	 |
    			| Banana            | 40             |  today    	 |
    			| Oatmeal     			| 80      		   |  today    	 |
    			| Apple     			  | 50       		   |  today    	 |
    And the message "570 calories have been consumed today!" is returned.