Feature: Logging Past Meals
  
  As a MealPlanner Customer
  I would like to log past meals
  So that I can keep track of my daily consumption

  Scenario: User Julianna wants to log a meal she has eaten (Normal Flow)
    Given The following list of meals exists in the MealPlanner database:
      | <Recipe>            | <CalorieCount>  | 
      | pestoKalePasta      | 200							|
      | tofuSoup            | 400							|
      | smokedsalamonGratin |	500							|
      
    When "Julianna" logs the following meal:
      | <Recipe>       | <servings> | <Day> |
      | pestoKalePasta |          2 | today |
      
    Then The list of user "Julianna" logged meals is updated.
      | <Meal>         | <CalorieCount> | <Day> |
      | pestoKalePasta |            400 | today |

  Scenario: User "Julianna" wants to log an item she has eaten that does not exist as a meal in the database (Error Flow)
    Given The following list of meals exists in the MealPlanner database:
      | <Recipe>            | <CalorieCount>  | 
      | pestoKalePasta      | 200							|
      | tofuSoup            | 400							|
      | smokedsalamonGratin |	500							|
      
    When "Julianna" logs the following meal:
      | <Recipe> | <Quantity> | <Day> |
      | Banana |          1 | today |
      
    Then "Julianna's" meal is not logged
