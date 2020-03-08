Feature: See meal suggestions based on owned ingredients
  
  As a user of the meal planning app, 
  I want to see suggested meals 
  So that I can stay on track with my diet goal

  Scenario: Input one ingredient to see meal suggestions with that ingredient. (Normal Scenatio)
    Given The following list of meals exists in the MealPlanner database:
      | <Recipe>    | <CalorieCount> |
      | apple sauce |            200 |
      | apple chips |            400 |
    When "tudor_T" is on the meal suggestion menu and specifies an <ingredientName>:
      | <username> | <ingredientName> |
      | tudor_T    | apple            |
    Then I should receive meal suggestions including the <IngredientName>
      | <recipe>    | <Ingredients> |
      | apple sauce | apple, water  |
      | apple chips | apple, oil    |

  Scenario: User inputs multiple ingredients to see meal suggestions with that list of ingredients. (Alternate Scenario)
    Given The following list of meals exists in the MealPlanner database:
      | <Recipe>                    | <CalorieCount> |
      | apple sauce                 |            200 |
      | apple chips                 |            400 |
      | fruit salad (apple, banana) |            700 |
      | oatmeal (apple, banana)     |            200 |
    When "tudor_T" is on the meal suggestion menu and specifies an <ingredientName>:
      | <username> | <ingredientName> |
      | tudor_T    | apple            |
      | tudor_T    | banana           |
    Then I should receive meal suggestions including the <IngredientName>
      | <recipe>                    | <Ingredients>                   |
      | fruit salad (apple, banana) | apple, banana, grapes, honeydew |
      | oatmeal (apple, banana)     | apple, banana, oatmeal, milk    |
