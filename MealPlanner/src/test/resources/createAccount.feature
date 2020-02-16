Feature: Create User Account

As a MealPlanner Customer
I would like to create an Account
So that I can log my daily meals

Background: 
    Given the initial List<user> "userlist" in the "MealPlannerService" are:
    | <user>   | <username> | <password> |<email>
    | TudorG   | tudor_T    | aa001      |tg@gmail.com
    | MatthewG | MG002      | matt_M     |mg@gmail.com
    

	Scenario: New user wants to create a new account (Normal Flow)
		Given the <user> does not have an account in the "MealPlannerService"
		Then they should input their <name>, <username>, and <password> to create an account.
		 | <user>   | <username> | <password> | <email>
     | JuliannaT| JT003      | jules_J    | jt@gmail.com
     And a message will be returned to the user "Account Successfully created"
     
     
	Scenario: New user wants to create a new account (Alternate Flow)
		Given the <user> has an account in the "MealPlannerService"
		Then they may add their desired <dietType> and <CalorieGoal>
		| <user>   | <dietType> | <CalorieGoal> |
    | JuliannaT| Keto       | 2000          |
     And a message will be returned to the user "Diet Goal added to existing user account"
     
		
	Scenario: New user wants to create a new account but already exists in the system (Error Flow)
		Given the <user> already has account in the "MealPlannerService"
		Then they should input their <name>, <username>, and <password> to create an account.
		 | <user>   | <username> | <password> | <email>
     | JuliannaT| JT003      | jules_J    | jt@gmail.com
     And a message will be returned to the user "Account not created. User already exists"
		
		
		
		
		