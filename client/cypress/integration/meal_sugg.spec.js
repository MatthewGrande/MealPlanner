describe('Meal Suggestions', function() {
	beforeEach(function setToken() {
		cy.visit('/suggestmeal');
	});

	it("contains the meal suggestions", function() {
		cy.contains("Here are your meal suggestions for today.");
	});
});