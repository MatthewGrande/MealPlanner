Feature: View Personal Account
  
  As a MealPlanner User
  I would like see my personal information
  So that I can edit it and view my goals

	Scenario: User requests to view Personal Account (Normal Scenario)
    Given the initial List of users in the MealPlannerService is:
      | <user>   | <username> | <password> | <email>      | 
      | TudorG   | tudor_T    | aa001      | tg@gmail.com | 
      | MatthewG | MG002      | matt_M     | mg@gmail.com | 
    When the User "tudor_T" requests to see their personal account
    Then he User "tudor_T" is diaplayed the following information
      | <user>   | <username> | <password> | <calorieGoal>| 
      | TudorG   | tudor_T    | aa001      |    2000      | 

  Scenario: System Admin requests to view Personal Account (Alternate Scenario)
    Given the initial List of users in the MealPlannerService is:
      | <user>   | <username> | <password> | <email>      | 
      | TudorG   | tudor_T    | aa001      | tg@gmail.com | 
      | MatthewG | MG002      | matt_M     | mg@gmail.com | 
    When  System Admin requests to see personal account
    Then a list of all users is diaplayed alongside following information
      | <user>   | <username> | <password> | <email>      | 
      | TudorG   | tudor_T    | *****      | tg@gmail.com |
      | MatthewG | MG002      | ******     | mg@gmail.com |  
      