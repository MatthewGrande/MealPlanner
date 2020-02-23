Feature:  Delete User Account
As a user of the meal planning application, 
I want to be able to delete my account and all its stored related data,
So that my information is no lonegr stored on the MealPlanner application

  Scenario: User Julianna wants to create a new account (Normal Flow)
    Given the initial List of users in the MealPlannerService is:
      | <user>   | <username> | <password> | 
      | TudorG   | tudor_T    | aa001      | 
      | MatthewG | MG002      | matt_M     | 
      | Julianna | JT003      | jules      | 
  
     When Julianna inputs her <username> "JT003" and <password> "jules" to delete an account
  
     Then the final List of users in the MealPLannerService is:
      | <user>   | <username> | <password> | <email>      | 
      | TudorG   | tudor_T    | aa001      | tg@gmail.com | 
      | MatthewG | MG002      | matt_M     | mg@gmail.com | 
  
  Scenario: User Julianna wants to delete her account but enters the wrong password when asked to confirm(Error Flow)
  
    Given the initial List of users in the MealPlannerService is:
      | <user>   | <username> | <password> | <email>      | 
      | TudorG   | tudor_T    | aa001      | tg@gmail.com | 
      | MatthewG | MG002      | matt_M     | mg@gmail.com | 
      | Julianna | JT003      | jules      | j@gmail.com  | 
  
     When Julianna inputs her <username> "JT003" and <password> "jule" to delete an account
  
     Then the final List of users in the MealPLannerService is:
      | <user>    | <username> | <password> | <email>      | 
      | TudorG    | tudor_T    | aa001      | tg@gmail.com | 
      | MatthewG  | MG002      | matt_M     | mg@gmail.com | 
      | JuliannaT | JT003      | jules      | jt@gmail.com | 