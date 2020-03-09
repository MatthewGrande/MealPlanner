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

export const deleteAccount = (username, password) => {
	return fetch(`/deleteUser/${username}`, {
		method: 'POST',
		headers: {
			Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		body: JSON.stringify({
			password,
		}),
	});
};

export const getProgress = username => {
	// Hardcoded since backend endpoint doesn't exist yet
	return new Promise(resolve => resolve(0.42));

	// TODO: update this to the proper endpoint once created
	return fetch(`/progress/${username}`, {
		method: 'GET',
		headers: {
			Accept: 'application/json',
			'Content-Type': 'application/json',
		},
	});
};
