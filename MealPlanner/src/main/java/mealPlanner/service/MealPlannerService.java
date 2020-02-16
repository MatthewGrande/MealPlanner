package mealPlanner.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import mealPlanner.service.InvalidInputException;
import mealPlanner.model.Day;
import mealPlanner.model.Ingredient;
import mealPlanner.model.MealPlannerApp;
import mealPlanner.model.OwnedIngredient;
import mealPlanner.model.User;
import mealPlanner.persistence.PersistenceXStream;

//Add all methods for the app here
@Service
public class MealPlannerService {
	private MealPlannerApp mp;
	public MealPlannerService(MealPlannerApp mp) {
		this.mp = mp;
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
