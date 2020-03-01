Feature: See meal suggestions based on owned ingredients

	As a user of the meal planning app, 
	I want to see suggested meals 
	So that I can stay on track with my diet goal

	Scenario: Input one ingredient to see meal suggestions with that ingredient. (Normal Scenatio)
		Given the user "tudor_T" owns the following <Ingredients>
		  | <username> | <ingredientName> | <quantity> |
      | tudor_T    | banana           | 5          |
      | tudor_T    | oatmeal          | 500        |
      | tudor_T    | apple            | 3          |
		When "tudor_T" is on the meal suggestion menu and add an <ingredientName>:
			| <username> | <ingredientName> |
      | tudor_T    | apple            |
		Then I should receive meal suggestions including the <IngredientName> "apple"
			| <recipe>     | <Ingredients> |
			| apple sauce  | apple, water  |		
		  | apple chips  | apple, oil    |			
			
	Scenario: User inputs multiple ingredients to see meal suggestions with that list of ingredients. (Alternate Scenario)
		Given the user "tudor_T" owns the following <Ingredients>
		  | <username> | <ingredientName> | <quantity> |
      | tudor_T    | banana           | 5          |
      | tudor_T    | oatmeal          | 500        |
      | tudor_T    | apple            | 3          |
		When "tudor_T" is on the meal suggestion menu and add an <ingredientName>:
			| <username> | <ingredientName> |
      | tudor_T    | apple            |
      | tudor_T    | banana           |
		Then I should receive meal suggestions including both the <Ingredients>
			| <recipe>     | <Ingredients>                   |
			| fruit salad  | apple, banana, grapes, honeydew |		
		  | oatmeal      | apple, banana, oatmeal, milk    |	
		
