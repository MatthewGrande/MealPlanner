Feature: Saving Recipes
  
  As a MealPlanner User
  I would like to save recipes 
  So that I can make meals using these recipes

  Scenario: A user "tudor_T" adds a recipe to their saved recipes (Normal Scenario)
    Given The following list of meals exists in the MealPlanner database:
      | <Recipe>            | <CalorieCount> |
      | pestoKalePasta      |            200 |
      | tofuSoup            |            400 |
      | smokedsalamonGratin |            500 |
      
    When user "tudor_T" is on the recipes page and saves the following <recipe>:
      | <recipe>       |
      | pestoKalePasta |
      
    Then the User "tudor_T" now has following <savedRecipes>:
      | <savedRecipes> |
      | pestoKalePasta |

  Scenario: A user "tudor_T" adds multiple recipe to their saved recipes (Normal Scenario)
    Given The following list of meals exists in the MealPlanner database:
      | <Recipe>            | <CalorieCount> |
      | pestoKalePasta      |            200 |
      | tofuSoup            |            400 |
      | smokedsalamonGratin |            500 |
      
    When user "tudor_T" is on the recipes page and saves the following <recipe>:
      | <recipe>       |
      | pestoKalePasta |
      | tofuSoup			 |
      
    Then the User "tudor_T" now has following <savedRecipes>:
      | <savedRecipes> |
      | pestoKalePasta |
      | tofuSoup			 |


  Scenario: A user "tudor_T" adds a recipe that does not exist to saved recipes (Error Scenario)
    Given The following list of meals exists in the MealPlanner database:
      | <Recipe>            | <CalorieCount> |
      | pestoKalePasta      |            200 |
      | tofuSoup            |            400 |
      | smokedsalamonGratin |            500 |
      
    When user "tudor_T" is on the recipes page and saves the following <recipe>:
      | <recipe> |
      | marsBar  |
      
    Then the User "tudor_T" has no saved recipes
    
