// Holds functions that call backend endpoints found in
// MealPlanner/src/main/java/mealPlanner/controller/MealPlannerRestController.java

export const register = (username, password, calorieGoal) => {
	return fetch(`/newUser/${username}/${password}/${calorieGoal}`, {
		method: 'POST',
		headers: {
			Accept: 'application/json',
			'Content-Type': 'application/json',
		},
	});
};
