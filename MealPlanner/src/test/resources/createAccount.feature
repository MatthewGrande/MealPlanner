Feature: Create User Account

As a MealPlanner Customer
I would like to create an Account
So that I can log my daily meals

  Scenario: User Julianna wants to create a new account (Normal Flow)
    Given the initial List of users in the MealPLannerService is:
      | <user>   | <username> | <password> | <email>      | 
      | TudorG   | tudor_T    | aa001      | tg@gmail.com | 
      | MatthewG | MG002      | matt_M     | mg@gmail.com | 
  
     When Julianna inputs her <username>, <password>, and <email> to create an account:
      | <user>    | <username> | <password> | <email>      | 
      | JuliannaT | JT003      | jules_J    | jt@gmail.com | 
  
     Then the final List of users in the MealPLannerService is:
      | <user>    | <username> | <password> | <email>      | 
      | TudorG    | tudor_T    | aa001      | tg@gmail.com | 
      | MatthewG  | MG002      | matt_M     | mg@gmail.com | 
      | JuliannaT | JT003      | jules_J    | jt@gmail.com | 
  
  Scenario: User Julianna wants to create a new account but username already exists in the system (Error Flow)
  
    Given the initial List of users in the MealPLannerService is:
      | <user>   | <username> | <password> | <email>      | 
      | TudorG   | tudor_T    | aa001      | tg@gmail.com | 
      | MatthewG | MG002      | matt_M     | mg@gmail.com | 
      | Julianna | JT003      | jules      | j@gmail.com  | 
  
     When Julianna inputs her <username>, <password>, and <email> to create an account:
      | <user>      | <username> | <password> | <email>      | 
      | JuliannaTch | JT003      | jules_J    | jt@gmail.com | 
  
     Then the final List of users in the MealPLannerService is:
      | <user>    | <username> | <password> | <email>      | 
      | TudorG    | tudor_T    | aa001      | tg@gmail.com | 
      | MatthewG  | MG002      | matt_M     | mg@gmail.com | 
      | JuliannaT | JT003      | jules      | jt@gmail.com | 
  