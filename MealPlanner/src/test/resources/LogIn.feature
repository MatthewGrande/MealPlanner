Feature: Log into account
  
  As a user of the meal planning app, 
  I want to be log into my account,
  So that I can check my personal information like diet goals, meal suggestions and my food consumption.

  Scenario: Login using a valid email and a valid password  (Normal Flow)
    Given the initial List of users in the MealPlannerService is:
      | <user>   | <username> | <password> | <email>      |
      | TudorG   | tudor_T    | aa001      | tg@gmail.com |
      | MatthewG | MG002      | matt_M     | mg@gmail.com |
    When a user inputs their <username> and <password> to login:
      | <username> | <password> |
      | tudor_T    | aa001      |
    Then the user should be logged in as "tudor_T"

  Scenario: Invalid login (Error Flow)
    Given the initial List of users in the MealPlannerService is:
      | <user>   | <username> | <password> | <email>      |
      | TudorG   | tudor_T    | aa001      | tg@gmail.com |
      | MatthewG | MG002      | matt_M     | mg@gmail.com |
    When a user inputs their <username> and <password> to login:
      | <username> | <password> |
      | julianna_T | aa001      |
    Then the user should not be logged in
