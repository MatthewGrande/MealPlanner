Feature: Log into account

As a user of the meal planning app, I want to log into my account to check my personal information like diet goals, meal suggestions and my food consumption. 

	Scenario: Login using a valid email and a valid password
	
	I should be able to see all information related to my account.
	
		Given I have already created an account
		When I insert the email John.doe@gmail.com 
			And I insert the password 01234
		Then I should receive a correct login confirmation
			And I should see all personal information in my account displayed
			
	Scenario: Login using a valid username and a valid password
	
	I should be able to see all information related to my account.
	
		Given I have already created an account
		When I insert the username JohnDoe
			And I insert the password 01234
		Then I should receive a correct login confirmation
			And I should see all personal information in my account displayed
	 

	Scenario: Invalid login

	I should get a prompt telling me that my login credentials are wrong and prompted to try again
    
    Given I have already created an account
		When I insert a <UserLog> 
			And I insert a <PassStat> password
		Then  I should receive a detailed incorrect login prompt
			And I should be able to re-enter my credentials 
            
            | UserLog                 | PassStat 
            | John                    | 01234
            | John.do@gmail.com       | 01234
            | JohnDoe                 | 0123
            | John.doe@gmail.com      | 0123
            | John                    | 0123
            | John.do@gmail.com       | 0123
	