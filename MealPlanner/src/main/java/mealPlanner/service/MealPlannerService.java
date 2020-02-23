package mealPlanner.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import mealPlanner.service.InvalidInputException;
import mealPlanner.model.Day;
import mealPlanner.model.Ingredient;
import mealPlanner.model.Meal;
import mealPlanner.model.MealPlannerApp;
import mealPlanner.model.OwnedIngredient;
import mealPlanner.model.Recipe;
import mealPlanner.model.User;
import mealPlanner.persistence.PersistenceXStream;

//Add all methods for the app here
@Service
public class MealPlannerService {
	private MealPlannerApp mp;
	private User loggedInUser;

	public MealPlannerService(MealPlannerApp mp) {
		this.mp = mp;
		this.loggedInUser = null;
	}

	public User createUser(String username, String password, int calorieGoal) throws InvalidInputException {

		// Check if username is already in database
		List<User> userList = mp.getUsers();
		for (User u : userList) {
			if (u.getUsername().contentEquals(username)) {
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

		if (isValidLogin(username, password)) {
			mp.removeUser(u);
		}

		PersistenceXStream.saveToXMLwithXStream(mp);
		return u;
	}

	public User getUser(String username) {
		List<User> users = mp.getUsers();

		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

	public Meal logMeal(String username, int recipe_index, int amount) {
		User u = getUser(username);
		Day day = u.getCurrentDay();
		Recipe recipe = mp.getRecipe(recipe_index);
		Meal m = new Meal(recipe, amount, day);
		PersistenceXStream.saveToXMLwithXStream(mp);

		return m;
	}

	/**
	 * Validates user credentials
	 * 
	 * @param userName
	 * @param password
	 * @return true if credentials are valid, otherwise false
	 * @throws InvalidInputException
	 */
	public Boolean isValidLogin(String userName, String password) throws InvalidInputException {

		User u = getUser(userName);
		if (u != null) {
			if (u.getPassword().equals(password)) {
				this.loggedInUser = u;
				return true;
			} else {
				this.loggedInUser = null;
				throw new InvalidInputException("Incorrect password.");
			}
		} else {
			this.loggedInUser = null;
			throw new InvalidInputException("Cannot find account for this username.");
		}
	}

	/**
	 * keep track of ingredients user already has
	 * 
	 * @param user
	 * @param ingredientName
	 * @param amount
	 * @return list of own ingredients
	 * @throws InvalidInputException
	 */
	public OwnedIngredient enterOwnIngredient(String username, String ingredientName, int amount)
			throws InvalidInputException {

		int index = 0;
		// check ingredientName
		if (ingredientName.equals(null) || ingredientName.equals("")) {
			throw new InvalidInputException("Please Enter Valid Ingredient Name");
		}

		List<User> userList = mp.getUsers();
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().contentEquals(username)) {
				index = i;
				break;
			}
		}

		User user = userList.get(index);

		// Create ingredient if it doesnt exist and add it to the list
		Ingredient i = new Ingredient(ingredientName);
		OwnedIngredient owned_i = user.getOwnedIngredient(ingredientName);

		if (owned_i == null) {
			owned_i = new OwnedIngredient(amount, i, user);

			// Add Ingredient
			user.addOwnedIngredient(owned_i);
		} else {
			owned_i.setAmount(owned_i.getAmount() + amount);
		}

		PersistenceXStream.saveToXMLwithXStream(mp);
		return owned_i;
	}

	public User getLoggedInUser() {
		return this.loggedInUser;
	}

	/**
	 * Adds recipe to saved recipes list for user
	 * 
	 * @param userName
	 * @param newRecipeName
	 * @return list of saved recipes
	 * @throws InvalidInputException
	 */
	public Boolean addToSavedRecipes(String userName, String newRecipeName) throws InvalidInputException {
		User user = getUser(userName);
		int index = -1;
		List<Recipe> recipeList = mp.getRecipes();

		if (user == null) {
			throw new InvalidInputException("Invalid user.");
		}

		if (newRecipeName.equals(null) || newRecipeName.equals("")) {
			throw new InvalidInputException("Please Enter Valid Recipe Name");
		}

		for (int i = 0; i < recipeList.size(); i++) {
			if (recipeList.get(i).getName().contentEquals(newRecipeName)) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			throw new InvalidInputException("Recipe not found.");
		}
		
		Recipe newRecipe = mp.getRecipe(index);

		if (newRecipe != null) {
			Boolean wasAdded = user.addSavedRecipe(newRecipe);
			if (wasAdded == false) {
				throw new InvalidInputException("This recipe is already saved.");
			}
		} else {
			throw new InvalidInputException("Please enter a valid recipe.");

		}
		PersistenceXStream.saveToXMLwithXStream(mp);
		return true;
	}

	/**
	 * Deletes recipe from saved recipes list for user
	 * 
	 * @param userName
	 * @param savedRecipeName
	 * @return list of saved recipes
	 * @throws InvalidInputException
	 */
	public Boolean deleteSavedRecipe(String userName, String savedRecipeName) throws InvalidInputException {
		User user = getUser(userName);
		int index = -1;

		if (user == null) {
			throw new InvalidInputException("Invalid user.");
		}

		List<Recipe> savedRecipeList = user.getSavedRecipes();

		if (savedRecipeName.equals(null) || savedRecipeName.equals("")) {
			throw new InvalidInputException("Please Enter Valid Saved Recipe Name");
		}

		for (int i = 0; i < savedRecipeList.size(); i++) {
			if (savedRecipeList.get(i).getName().contentEquals(savedRecipeName)) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			throw new InvalidInputException("Saved Recipe not found.");
		}
		
		Recipe savedRecipe = mp.getRecipe(index);

		if (savedRecipe != null) {
			Boolean wasRemoved = user.removeSavedRecipe(savedRecipe);
			if (wasRemoved == false) {
				throw new InvalidInputException("This recipe was never saved.");
			}
		} else {
			throw new InvalidInputException("Please choose a valid saved recipe.");
		}
		PersistenceXStream.saveToXMLwithXStream(mp);
		return true;
	}

}
