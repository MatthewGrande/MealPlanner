Feature: Enter Owned Ingregients

As a MealPlanner User
I would like to enter ingredients that I own
So that I can log the foods that I have consumed 

	Scenario: Add a new owned ingredient to the MealPlanner database (Normal Scenario)
		Given the database contains the following <Ingredients> 
		| <username> | <ingredientName> |<quantity>
    | tudor_T    | banana           | "5"
    | tudor_T    | oatmeal          | "500g"
		When I enter add own ingrediant menu and add my <ingredientName> and <Amount>
		| <username> | <ingredientName> |<quantity>
    | tudor_T    | apple            | "3"
		Then the item should appear in the owned ingredient list
		| <username> | <ingredientName> |<quantity>
    | tudor_T    | banana           | "5"
    | tudor_T    | oatmeal          | "500g"
		| tudor_T    | apple            | "3"

	Scenario: Add an item present in database (Alternate Scenario)
		Given the User owns the following ingredients
		| <username> | <ingredientName> |<quantity>
    | tudor_T    | banana           | "5"
    | tudor_T    | oatmeal          | "500g"
		When I enter the name of an item that already exists in the database
		| <username> | <ingredientName> |<quantity>
    | tudor_T    | banana           | "2"
		Then the item should appear in the list with an updated quantity
		| <username> | <ingredientName> |<quantity>
    | tudor_T    | banana           | "7"
    | tudor_T    | oatmeal          | "500g"


