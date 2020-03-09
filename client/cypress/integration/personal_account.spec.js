describe('Personal Account', function() {
	beforeEach(function setToken() {
		cy.visit('/viewaccount');
	});

	it("contains the personal account information", function() {
		cy.contains("Full Name");
		cy.contains("Email");
		cy.contains("Goal");
		cy.contains("Progress");
	});
});