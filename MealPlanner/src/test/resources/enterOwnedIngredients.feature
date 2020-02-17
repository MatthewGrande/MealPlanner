Feature: Enter Owned Ingregients
  
  As a MealPlanner User
  I would like to enter ingredients that I own
  So that I can see recipes that I can make

  Scenario: Add a new owned ingredient to the MealPlanner database (Normal Scenario)
    Given the User "tudor_T" owns the following <Ingredients>:
      | <username> | <ingredientName> | <quantity> |
      | tudor_T    | banana           | 5          |
      | tudor_T    | oatmeal          | 500        |
    When "tudor_T" is on the add own ingredient menu and add my <ingredientName> and <Amount>:
      | <username> | <ingredientName> | <quantity> |
      | tudor_T    | apple            | 3          |
    Then the User "tudor_T" now owns the following <Ingredients>:
      | <username> | <ingredientName> | <quantity> |
      | tudor_T    | banana           | 5          |
      | tudor_T    | oatmeal          | 500        |
      | tudor_T    | apple            | 3          |

  Scenario: Add an item present in database (Alternate Scenario)
    Given the User "tudor_T" owns the following <Ingredients>:
      | <username> | <ingredientName> | <quantity> |
      | tudor_T    | banana           | 5          |
      | tudor_T    | oatmeal          | 500        |
    When "tudor_T" is on the add own ingredient menu and add my <ingredientName> and <Amount>:
      | <username> | <ingredientName> | <quantity> |
      | tudor_T    | banana           | 2          |
    Then the User "tudor_T" now owns the following <Ingredients>:
      | <username> | <ingredientName> | <quantity> |
      | tudor_T    | banana           | 7          |
      | tudor_T    | oatmeal          | 500        |
