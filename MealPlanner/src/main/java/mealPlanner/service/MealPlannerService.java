package mealPlanner.service;

import java.sql.Date;
import java.util.List;

import mealPlanner.service.InvalidInputException;
import mealPlanner.model.Day;
import mealPlanner.model.Ingredient;
import mealPlanner.model.MealPlannerApp;
import mealPlanner.model.OwnedIngredient;
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

	public User createUser(String username, String password, int calorieGoal) throws InvalidInputException  {
		
		// Check if username is already in database
		List<User> userList = mp.getUsers();
		for (User u : userList) {
			if(u.getUsername().contentEquals(username)) {
				throw new InvalidInputException("This username is taken.");
			}
		}
		
		Day today = new Day(new Date(0, 0, 0), 0);
		User u = new User(username, password, calorieGoal, today);
		mp.addUser(u);
		PersistenceXStream.saveToXMLwithXStream(mp);
		return u;
	}
	
	
	/**
	 * keep track of ingredients user already has
	 * @param user
	 * @param ingredientName
	 * @param amount
	 * @return list of own ingredients
	 * @throws InvalidInputException
	 */
	public OwnedIngredient enterOwnIngredient(String username, String ingredientName, int amount) throws InvalidInputException {
		
		int index=0;
		
		// check ingredientName
		if(ingredientName.contentEquals(null) || ingredientName.contentEquals("")) {
			throw new InvalidInputException("Please Enter Valid Ingredient Name");
		}
		
		
		List<User> userList = mp.getUsers();
		for(int i=0; i<userList.size(); i++) {
			if( userList.get(i).getUsername().contentEquals(username)){
				index=i;
				break;
			}		
		}
		
		User user = userList.get(index);

		// Create ingredient if it doesnt exist and add it to the list
		Ingredient i = new Ingredient(ingredientName);
		OwnedIngredient owned_i = new OwnedIngredient(amount, i, user);
		
		// Add Ingredient
		user.addOwnedIngredient(owned_i);
		
		//PersistenceXStream.saveToXMLwithXStream(mp);
		return owned_i;
		
			
		
	}
	
	
}
