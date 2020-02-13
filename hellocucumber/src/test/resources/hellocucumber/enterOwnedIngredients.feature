Feature: As a user of the meal planning app, I want to be able to keep track of the ingredients I have available in my home fridge and pantry. This will help me while I am grocery shopping and also enable me to receive catered meal recommendations from the app.

	Scenario: Search for item present in database
		Given the database contains the item
		When I enter the name of an item
		Then the item should appear in the list

	Scenario: Search for item with typo
		Given the input is detected as a typo
		And the input has a correct spelling
		When I incorrectly type the name of an item
		Then the correct spelling should appear as a suggestion

	Scenario: Search for item not present in database
		Given the database does not contain any items with matching names
		When I enter the name of an item
		Then a message should appear notifying me that no results were found
