Feature: As a new user to the meal planning app, I want to be able to create an account.

	Scenario: New user wants to create a new account and specify a diet goal.
		Given the new user wants to consume 2000 calories a day
		Then they should be able to input this with an email and password and create an account

	Scenario: New user wants to create a new account and not specify a diet goal.
		Given the new user wants to not specify a diet goal
		Then they should be able to input an email and password and create an account

	Scenario: New user enters the email address of an existing user
		Given the new user enters an email adress that is already associated to an account
		Then they should receive an error message

	Scenario: New user enters an invalid pasword
		Given the new user enters a password that is shorter than 10 characters
		Then they should receive an error message