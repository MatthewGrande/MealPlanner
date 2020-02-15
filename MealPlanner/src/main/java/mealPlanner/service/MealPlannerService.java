package mealPlanner.service;

import java.sql.Date;
import java.util.List;

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
	
	public User deleteUser(String username, String password) throws InvalidInputException {
		User u = getUser(username);
		if(u == null) {
			throw new InvalidInputException("There is no user with that username");
		}
		if(!u.getPassword().equals(password)) {
			throw new InvalidInputException("Incorrect password");
		}
		mp.removeUser(u);
		PersistenceXStream.saveToXMLwithXStream(mp);
		return u;
	}
	
	public User getUser(String username) {
		List<User> users = mp.getUsers();
		
		for(User u : users) {
			if(u.getUsername().equals(username)){
				return u;
			}
		}
		return null;
	}
	
	
	
	
}
