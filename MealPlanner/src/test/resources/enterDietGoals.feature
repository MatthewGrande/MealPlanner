Feature: As a user of the meal planning app, I want to be able to enter all of my diet goals, including my desired calories count, protein intake, calcium intake, and whether or not I am following any particular diet regimes such as intermittent fasting or keto fasting.

	Scenario: Existing user inputs as many of their diet goals as they want. There is no minimum or maximum amount of goals they need to enter.
		Given the user wants to consume 2000 calories a day and intake 60g of protein a day while following the keto fasting regime
		And intake 60g of protein a day
		When folowing the keto fasting regime
		Then they should be able to input all of those constraints

	Scenario: Existing user only wants to input how many calories they wish to consume a day
		Given the user wants to consume 2000 calories a day
		Then they should be able to input only this constraint

	Scenario: Existing user enters a string value for a diet goal that requires an integer
		Given the user wants to input a string for the number of calories they want to consume a day
		Then they should receive an error message