describe('The Progress Page', function() {
	beforeEach(function setToken() {
		cy.visit('/progress');
	});

	it("contains the user's progress", function() {
		// TODO: Update once progress is no longer hard coded
		cy.contains("You've consumed 42% of your daily calorie goal for the day.");
	});
});
