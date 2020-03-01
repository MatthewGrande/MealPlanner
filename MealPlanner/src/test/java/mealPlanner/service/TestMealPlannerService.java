package mealPlanner.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mealPlanner.model.*;
import mealPlanner.persistence.PersistenceXStream;

public class TestMealPlannerService {

	private MealPlannerApp mp;

	@Before
	public void setUp() throws Exception {
		mp = new MealPlannerApp();

		// initialize model file
		PersistenceXStream.initializeModelManager("data.xml");
		// save model that is loaded during test setup
		if (!PersistenceXStream.saveToXMLwithXStream(mp)) {
			fail("Could not save file.");
		}

		// clear the model in memory
		mp.delete();

		// load model
		mp = (MealPlannerApp) PersistenceXStream.loadFromXMLwithXStream();
		if (mp == null) {
			fail("Could not load file.");
		}

	}

	@After
	public void tearDown() throws Exception {
		mp.delete();
	}

	@Test
	public void testCreateUser() throws InvalidInputException {

		assertEquals(0, mp.getUsers().size());

		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;

		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);
		assertEquals(1, mp.getUsers().size());
		assertEquals(username, mp.getUser(0).getUsername());
		assertEquals(password, mp.getUser(0).getPassword());
		assertEquals(calorieGoal, mp.getUser(0).getGoalCalorie());
	}

	@Test
	public void testDeleteUser() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;

		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);
		assertEquals(1, mp.getUsers().size());
		service.deleteUser(username, password);
		assertEquals(0, mp.getUsers().size());

	}

	@Test
	public void testTrackProgress() throws InvalidInputException{
		
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		
		String wrongUsername = "wrong";
		
		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);
		service.logMeal(username, 1, 1);
		
		assertEquals(mp.getRecipe(1).getCalorieCountPerServing()*1/calorieGoal,service.trackProgress(username));
		
		try {
		
		assertEquals(mp.getRecipe(1).getCalorieCountPerServing()*1/calorieGoal,service.trackProgress(wrongUsername));
		
		}
		catch(InvalidInputException e) {
			assertEquals(e.getMessage(),("User not found."));
		}
		
	}
	
	@Test
	public void testViewSavedRecipes() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		String wrongUsername = "wrong";
		Recipe r = new Recipe(800,"Pestont");
		
		
		MealPlannerService service = new MealPlannerService(mp);
		
		try {
			service.createUser("user0", password, calorieGoal);
			assertEquals(0, service.viewSavedRecipes("user0").size());
		}catch(InvalidInputException e) {
			assertEquals(e.getMessage(),("No saved recipes."));
		}

		service.createUser(username, password, calorieGoal).addSavedRecipe(r);
		
		
		try {	
		assertEquals(1, service.viewSavedRecipes(username).size());
		
		} catch (InvalidInputException e) {
			assertEquals(e.getMessage(),("No saved recipes."));
		}
		
		try {	
			assertEquals(1, service.viewSavedRecipes(wrongUsername).size());
			
			} catch (InvalidInputException e) {
				assertEquals(e.getMessage(),("User not found."));
			}
		
	}
	
	@Test
	public void testDeleteNonUser() throws InvalidInputException{
		String username = "user1";
		String wrongUsername = "user2";
		String password = "password1";
		int calorieGoal = 2000;

		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);
		assertEquals(1, mp.getUsers().size());
		try {
			service.deleteUser(wrongUsername, password);
		} catch (InvalidInputException e) {
			assertEquals(e.getMessage(), "Cannot find account for this username.");
		}
	}

	@Test
	public void testDeleteUserWrongPassword() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		String wrongPassword = "password2";
		int calorieGoal = 2000;

		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);
		assertEquals(1, mp.getUsers().size());

		try {
			service.deleteUser(username, wrongPassword);
		} catch (InvalidInputException e) {
			assertEquals(e.getMessage(), "Incorrect password.");
		}
	}

	@Test
	public void testLogin() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;

		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);
		try {
			service.isValidLogin(username, "abc123");
		} catch (InvalidInputException e) {
			assertEquals(service.getLoggedInUser(), null);
		}
		service.isValidLogin(username, password);
		assertEquals(service.getLoggedInUser().getUsername(), username);

	}

	@Test
	public void testEnterOwnedIngredient() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int numIngredients = 3;
		String ingType = "banana";
		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);
		service.enterOwnIngredient(username, ingType, numIngredients);
		for (OwnedIngredient oi : service.getUser(username).getOwnedIngredients()) {
			if (oi.getIngredient().getName().equals("banana")) {
				assertEquals(oi.getAmount(), numIngredients);
			}
		}

		service.enterOwnIngredient(username, ingType, 2 * numIngredients);
		for (OwnedIngredient oi : service.getUser(username).getOwnedIngredients()) {
			if (oi.getIngredient().getName().equals("banana")) {
				assertEquals(oi.getAmount(), 3 * numIngredients);
			}
		}
	}

	@Test
	public void testAddToSavedRecipes() throws InvalidInputException {
		String username = "user2";
		String password = "password1";
		int calorieGoal = 2000;
		String recipeName = "marsBar";
		Recipe mars = new Recipe(recipeName, 150);
		mp.addRecipe(mars);
		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);
		int numSavedRecipes = service.getUser(username).getSavedRecipes().size();

		try {
			service.addToSavedRecipes(username, recipeName);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		List<Recipe> savedRecipes = service.getUser(username).getSavedRecipes();
		
		assertEquals(savedRecipes.get(0).getName(), "marsBar");
		assertEquals(savedRecipes.size(), numSavedRecipes + 1);
	}

	@Test
	public void testAddToSavedRecipesNullUser() throws InvalidInputException {
		String username = "user2";
		String password = "password1";
		int calorieGoal = 2000;
		String error = null;
		String recipeName = "marsBar";
		MealPlannerService service = new MealPlannerService(mp);
		service.createUser("user1", password, calorieGoal);

		try {
			service.addToSavedRecipes(username, recipeName);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Invalid user.", error);
	}

	@Test
	public void testAddToSavedRecipesNullRecipe() throws InvalidInputException {
		String username = "user2";
		String password = "password1";
		int calorieGoal = 2000;
		String error = null;
		String recipeName = "";
		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);

		try {
			service.addToSavedRecipes(username, recipeName);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Please Enter Valid Recipe Name", error);
	}

	@Test
	public void testAddToSavedRecipesExistingRecipe() throws InvalidInputException {
		String username = "user2";
		String password = "password1";
		int calorieGoal = 2000;
		String error = null;
		String recipeName = "marsBar";
		Recipe mars = new Recipe(recipeName, 150);
		mp.addRecipe(mars);
		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);
		service.addToSavedRecipes(username, recipeName);
		int numSavedRecipes = service.getUser(username).getSavedRecipes().size();

		try {
			service.addToSavedRecipes(username, recipeName);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		List<Recipe> savedRecipes = service.getUser(username).getSavedRecipes();
		assertEquals("This recipe is already saved.", error);
		assertEquals(savedRecipes.size(), numSavedRecipes);

	}

	@Test
	public void testDeleteSavedRecipe() throws InvalidInputException {
		String username = "user2";
		String password = "password1";
		int calorieGoal = 2000;
		String recipeName = "marsBar";
		Recipe mars = new Recipe(recipeName, 150);
		mp.addRecipe(mars);
		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);
		service.addToSavedRecipes(username, recipeName);

		try {
			service.deleteSavedRecipe(username, recipeName);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		List<Recipe> savedRecipes = service.getUser(username).getSavedRecipes();
		assertEquals(savedRecipes.size(), 0);
	}

	@Test
	public void testDeleteSavedRecipeNullUser() throws InvalidInputException {
		String username = "user2";
		String password = "password1";
		int calorieGoal = 2000;
		String error = null;
		String recipeName = "marsBar";
		MealPlannerService service = new MealPlannerService(mp);
		service.createUser("user1", password, calorieGoal);

		try {
			service.deleteSavedRecipe(username, recipeName);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Invalid user.", error);
	}

	@Test
	public void testDeleteSavedRecipeNullRecipe() throws InvalidInputException {
		String username = "user2";
		String password = "password1";
		int calorieGoal = 2000;
		String error = null;
		String recipeName = "";
		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);

		try {
			service.deleteSavedRecipe(username, recipeName);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Please Enter Valid Saved Recipe Name", error);
	}

	@Test
	public void testDeleteSavedRecipesExistingRecipe() throws InvalidInputException {
		String username = "user2";
		String password = "password1";
		int calorieGoal = 2000;
		String error = null;
		String recipeName = "marsBar";
		Recipe mars = new Recipe(recipeName, 150);
		mp.addRecipe(mars);
		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);
		int numSavedRecipes = service.getUser(username).getSavedRecipes().size();

		try {
			service.deleteSavedRecipe(username, recipeName);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		List<Recipe> savedRecipes = service.getUser(username).getSavedRecipes();
		assertEquals("Saved Recipe not found.", error);
		assertEquals(savedRecipes.size(), numSavedRecipes);

	}
	
	@Test
	public void testViewMealSuggestions() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		String wrongUsername = "wrong";
		
		MealPlannerService service = new MealPlannerService(mp);
		
		try {
			service.createUser("user0", password, calorieGoal);
//			assertEquals(0, service.viewMealSuggestions("user0").size());
		}catch(InvalidInputException e) {
			assertEquals(e.getMessage(),("You don't have saved Meal Suggestions to show currently."));
		}
		
		try {	
//		assertEquals(1, service.viewMealSuggestions(username).size());
		
		} catch (InvalidInputException e) {
			assertEquals(e.getMessage(),("No saved Meal Suggestions."));
		}
		
		try {	
//			assertEquals(1, service.viewMealSuggestions(wrongUsername).size());
			
			} catch (InvalidInputException e) {
				assertEquals(e.getMessage(),("User not found."));
			}
		
		@Test
		public void viewPersonalAccount() throws InvalidInputException {

			assertEquals(0, mp.getUsers().size());

			String username = "user1";
			String password = "password1";
			int calorieGoal = 2000;

			MealPlannerService service = new MealPlannerService(mp);

//			service.createUser(username, password, calorieGoal).viewAccount();
			assertEquals(1, mp.getUsers().size());
			assertEquals(username, mp.getUser(0).getUsername());
			assertEquals(password, mp.getUser(0).getPassword());
			assertEquals(calorieGoal, mp.getUser(0).getGoalCalorie());
			
			
		}
		
	public void testUserLogsValidMeal() throws InvalidInputException{
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int amount = 1;
		int recipeNumber = 0;
		Day today = new Day(new Date(0,0,0), 100);

		MealPlannerService service = new MealPlannerService(mp);
		User u = service.createUser(username, password, calorieGoal);
		u.setCurrentDay(today);

		Meal m = service.logMeal(username, recipeNumber, amount);
		assertEquals(m.getDay(),today);
		assertEquals(m.getServings(),amount);
		
	}
	
	@Test
	public void testUserLogsMealInvalidAmount() throws InvalidInputException{
		String error = "";
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int amount = -1;
		int recipeNumber = 0;
		Day today = new Day(new Date(0,0,0), 100);

		MealPlannerService service = new MealPlannerService(mp);
		User u = service.createUser(username, password, calorieGoal);
		u.setCurrentDay(today);
		try {
			Meal m = service.logMeal(username, recipeNumber, amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}		
		assertEquals(error ,"Number of portions be 1 or more.");
		
	}
	
	@Test
	public void testUserLogsMealInvalidRecipe() throws InvalidInputException{
		String error = "";
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int amount = 1;
		int recipeNumber = -1;
		Day today = new Day(new Date(0,0,0), 100);

		MealPlannerService service = new MealPlannerService(mp);
		User u = service.createUser(username, password, calorieGoal);
		u.setCurrentDay(today);
		try {
			Meal m = service.logMeal(username, recipeNumber, amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}		
		assertEquals(error ,"Recipe Index must be a positive number.");
		
	}	
	
	@Test
	public void testUserLogsMealInvalidUser() throws InvalidInputException{
		String error = "";
		String username = "user1";
		String username2 = "user1234";
		String password = "password1";
		int calorieGoal = 2000;
		int amount = 1;
		int recipeNumber = 0;
		Day today = new Day(new Date(0,0,0), 100);

		MealPlannerService service = new MealPlannerService(mp);
		User u = service.createUser(username, password, calorieGoal);
		u.setCurrentDay(today);
		try {
			Meal m = service.logMeal(username2, recipeNumber, amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}		
		assertEquals(error ,"Username must be valid.");
		
		
	}

}
