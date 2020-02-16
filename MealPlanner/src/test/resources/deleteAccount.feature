Feature:  As a user of the meal planning application, I want to be able to delete my account and all it’s stored related data. 

	Scenario: Existing user logs in and choses the deactivate account option from the settings menu. The user re-enters his password before confirming he would like to delete his account. 
		Given User is logged in
		When User Choses delete account
		And  Enters correct password 
		And  Enters 'yes' for confirmation
		Then the account attached to the user will be deleted

	Scenario: User choses no when I asked to confirm that he would like to cancel his account. The user is taken back to the main menu. 
		Given User is logged in
		When the user choses delete account
		And   Enters correct password for confirmation
		And   Enters 'No' for confirmation
		Then User should be taken back to main menu

	Scenario: User enters the wrong password when asked to re-enter it for confirmation 
		Given User is logged in
		When the user choses delete account
		And   Enters wrong password for confirmation
		Then they should receive an error message