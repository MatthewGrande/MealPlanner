package mealPlanner.service;

import java.sql.Date;

import mealPlanner.model.Day;
import mealPlanner.model.MealPlannerApp;
import mealPlanner.model.User;
import mealPlanner.persistence.PersistenceXStream;

//Add all methods for the app here

public class MealPlannerService {
	private MealPlannerApp mp;
	public MealPlannerService(MealPlannerApp mp) {
		this.mp = mp;
	}

	public User createUser(String username, String password, int calorieGoal) {
		Day today = new Day(new Date(0, 0, 0), 0);
		User u = new User(username, password, calorieGoal, today);
		mp.addUser(u);
		PersistenceXStream.saveToXMLwithXStream(mp);
		return u;
	}
	
	
	
	
}
