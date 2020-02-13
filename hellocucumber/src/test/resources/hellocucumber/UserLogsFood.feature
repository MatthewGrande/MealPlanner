Feature: User Logs Meals. As a system, I want to display input options for the user to input the foods they have eaten and calculate the calories and/or fat consumed. As a user of the meal planning app, I want to be able to enter all the food I have eaten, including specific ingredients used.

As a system, I want to save meals the user has eaten and calculate nutritional information using the ingredients I was given by the user. 

	Scenario: Click on add food to daily log 
		Given the input exists in the database 
		Then I type the item name to search 
		And click on add to daily log 

		
	Scenario: Search for recommended meal in database
		Given the database contains the item
		When I enter the name of an item
		Then the item should appear in the list
		When I Click add to daily log
		Then the correct meal should be added to my log

	Scenario: Search for meal not present in database
		Then a message should appear notifying me that no results were found and an option to 		add manually exists
		When I click add manually I am taken to the add meal page
		Then upon submission the meal is added to my daily log