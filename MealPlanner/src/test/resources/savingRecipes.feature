Feature: Saving Recipes
  
  As a MealPlanner User
  I would like to save recipes 
  So that I can make meals using these recipes

  Scenario: A user "tudor_T" adds a recipe to their saved recipes (Normal Scenario)
    Given the following <Recipes> exist in the MealPlanner database:
          | <recipe>            | <DietType> | <Ingredients> 																				 |
     		  | pestoKalePasta      | vegetarian |pesto, creamCheese, rapeseedoil, redOnion, kale, pasta |
     		  | tofuSoup            | n/a        |mushrooms, brownrice, noodles, redOnion, rapeseedoil   |
     		  | smokedsalamonGratin | n/a        |spinach, butter, salmon, cream                         |
    And the user "tudor_T" has the following <savedRecipes>
		    	| <savedRecipes>        |
		      | null                  |
    When user "tudor_T" is on the recipes page and choose to save a <recipe> 
		      | <recipe>        |
		      | pestoKalePasta  |
    Then the User "tudor_T" now has following <savedRecipes>:
		      | <savedRecipes>        |
		      | pestoKalePasta        |
      
    Scenario: A user "tudor_T" adds a recipe that is already saved (Error Scenario)
    Given the following <Recipes> exist in the MealPlanner database:
          | <Recipe>            | <DietType> | <Ingredients> 																				 |
     		  | pestoKalePasta      | vegetarian |pesto, creamCheese, rapeseedoil, redOnion, kale, pasta |
     		  | tofuSoup            | n/a        |mushrooms, brownrice, noodles, redOnion, rapeseedoil   |
     		  | smokedsalamonGratin | n/a        |spinach, butter, salmon, cream                         |
    And the user "tudor_T" has the following <savedRecipes>
		    	| <savedRecipes>        |
		      | pestoKalePasta        |
    When user "tudor_T" is on the recipes page and choose to save a <recipe> 
      | <recipe>        |
      | pestoKalePasta  |
    Then A message is returned saying "This recipe has already been saved!"

    Scenario: A user "tudor_T" adds a recipe that does not exist to saved recipes (Error Scenario)
    Given the following <Recipes> exist in the MealPlanner database:
          | <Recipe>            | <DietType> | <Ingredients> 																				 |
     		  | pestoKalePasta      | vegetarian |pesto, creamCheese, rapeseedoil, redOnion, kale, pasta |
     		  | tofuSoup            | n/a        |mushrooms, brownrice, noodles, redOnion, rapeseedoil   |
     		  | smokedsalamonGratin | n/a        |spinach, butter, salmon, cream                         |
    And the user "tudor_T" has the following <savedRecipes>
		    	| <savedRecipes>        |
		      | pestoKalePasta        |
    When user "tudor_T" is on the recipes page and choose to save a <recipe> 
      | <recipe>        |
      | marsBar         |
    Then A message is returned saying "This recipe does not exist, please add it to database."

