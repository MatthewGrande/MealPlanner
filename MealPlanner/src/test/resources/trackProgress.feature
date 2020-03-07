Feature: Track Progress
  
  As a MealPlanner User
  I would like to track my daily progress
  So that I can plan my food consumption for the rest of the day
 
  Scenario: Valid user tracks progress in Meal Planner (Normal Scenario)
    Given the initial List of users in the MealPlannerService is:
      | <user>   | <username> | <password> | <email>      | 
      | TudorG   | tudor_T    | aa001      | tg@gmail.com | 
      | MatthewG | MG002      | matt_M     | mg@gmail.com | 
    When the user "tudor_T" navigates to the track progress page
    Then the User "tudor_T" will see the following infomation
      | <user>   | <calorieGoal> | <currentCalorieCount> | <progress>   | 
      | TudorG   | 2000          | 1000                  | 0.50         | 

  Scenario: Invalid user tracks progress in Meal Planner (Error Scenario)
    Given the initial List of users in the MealPlannerService is:
      | <user>   | <username> | <password> | <email>      | 
      | TudorG   | tudor_T    | aa001      | tg@gmail.com | 
      | MatthewG | MG002      | matt_M     | mg@gmail.com | 
    When someone who is not logged in navigates to the track progress page
    Then the following message will be displayed "User not found."
 