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
		Date date1= new Date(0,0,0);
		Date date2= new Date(0,0,0);
		Day day1= new Day(date1,0);
		Day day2= new Day(date2,0);
		User user1 = new User("John","123",2000,day1);
		User user2 = new User("Daniel","122",2000,day2);
		mp.addUser(user1);
		mp.addUser(user2);
		PersistenceXStream.saveToXMLwithXStream(mp);
		}
	
	
	
}
