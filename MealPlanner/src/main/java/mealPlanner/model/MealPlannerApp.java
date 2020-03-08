/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.1.4785.04bd3fea6 modeling language!*/

package mealPlanner.model;

import java.util.*;

// line 7 "model.ump"
// line 67 "model.ump"
public class MealPlannerApp {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// MealPlannerApp Associations
	private List<User> users;
	private List<Recipe> recipes;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public MealPlannerApp() {
		users = new ArrayList<User>();
		recipes = new ArrayList<Recipe>();
	}

	// ------------------------
	// INTERFACE
	// ------------------------
	/* Code from template association_GetMany */
	public User getUser(int index) {
		User aUser = users.get(index);
		return aUser;
	}

	public List<User> getUsers() {
		List<User> newUsers = Collections.unmodifiableList(users);
		return newUsers;
	}

	// ******************//
	/**
	 * Added Method checks if user exists
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean hasUser(User user) {

		for (User u : users) {
			if (u.getUsername().equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}
	// ******************//

	public int numberOfUsers() {
		int number = users.size();
		return number;
	}

	public boolean hasUsers() {
		boolean has = users.size() > 0;
		return has;
	}

	public int indexOfUser(User aUser) {
		int index = users.indexOf(aUser);
		return index;
	}

	/* Code from template association_GetMany */
	public Recipe getRecipe(String name) {
		int index = -1;
		for (int i = 0; i < recipes.size(); i++) {
			if (recipes.get(i).getName().contentEquals(name)) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			return null;
		}

		Recipe aRecipe = recipes.get(index);
		return aRecipe;
	}

	public List<Recipe> getRecipes() {
		List<Recipe> newRecipes = Collections.unmodifiableList(recipes);
		return newRecipes;
	}

	public int numberOfRecipes() {
		int number = recipes.size();
		return number;
	}

	public boolean hasRecipes() {
		boolean has = recipes.size() > 0;
		return has;
	}

	public int indexOfRecipe(Recipe aRecipe) {
		int index = recipes.indexOf(aRecipe);
		return index;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfUsers() {
		return 0;
	}

	/* Code from template association_AddUnidirectionalMany */
	public boolean addUser(User aUser) {
		boolean wasAdded = false;
		if (users.contains(aUser)) {
			return false;
		}
		users.add(aUser);
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeUser(User aUser) {
		boolean wasRemoved = false;
		if (users.contains(aUser)) {
			users.remove(aUser);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addUserAt(User aUser, int index) {
		boolean wasAdded = false;
		if (addUser(aUser)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfUsers()) {
				index = numberOfUsers() - 1;
			}
			users.remove(aUser);
			users.add(index, aUser);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveUserAt(User aUser, int index) {
		boolean wasAdded = false;
		if (users.contains(aUser)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfUsers()) {
				index = numberOfUsers() - 1;
			}
			users.remove(aUser);
			users.add(index, aUser);
			wasAdded = true;
		} else {
			wasAdded = addUserAt(aUser, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfRecipes() {
		return 0;
	}

	/* Code from template association_AddUnidirectionalMany */
	public boolean addRecipe(Recipe aRecipe) {
		boolean wasAdded = false;
		if (recipes.contains(aRecipe)) {
			return false;
		}
		recipes.add(aRecipe);
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeRecipe(Recipe aRecipe) {
		boolean wasRemoved = false;
		if (recipes.contains(aRecipe)) {
			recipes.remove(aRecipe);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addRecipeAt(Recipe aRecipe, int index) {
		boolean wasAdded = false;
		if (addRecipe(aRecipe)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfRecipes()) {
				index = numberOfRecipes() - 1;
			}
			recipes.remove(aRecipe);
			recipes.add(index, aRecipe);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveRecipeAt(Recipe aRecipe, int index) {
		boolean wasAdded = false;
		if (recipes.contains(aRecipe)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfRecipes()) {
				index = numberOfRecipes() - 1;
			}
			recipes.remove(aRecipe);
			recipes.add(index, aRecipe);
			wasAdded = true;
		} else {
			wasAdded = addRecipeAt(aRecipe, index);
		}
		return wasAdded;
	}

	public void delete() {
		users.clear();
		recipes.clear();
	}

}