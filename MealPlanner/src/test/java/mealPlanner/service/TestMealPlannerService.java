package mealPlanner.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
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

		try {
			service.createUser(username, password, calorieGoal);

		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(1, mp.getUsers().size());
		assertEquals(username, mp.getUser(0).getUsername());
		assertEquals(password, mp.getUser(0).getPassword());
		assertEquals(calorieGoal, mp.getUser(0).getGoalCalorie());
	}

	@Test
	public void testCreateUserNullUserName() throws InvalidInputException {

		String username = null;
		String password = "password1";
		int calorieGoal = 2000;
		String error = "";

		MealPlannerService service = new MealPlannerService(mp);

		try {
			service.createUser(username, password, calorieGoal);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("Invalid user parameters", error);
	}

	@Test
	public void testCreateUserNullPassword() throws InvalidInputException {

		String username = "user1";
		String password = null;
		int calorieGoal = 2000;
		String error = "";

		MealPlannerService service = new MealPlannerService(mp);

		try {
			service.createUser(username, password, calorieGoal);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("Invalid user parameters", error);
	}

	@Test
	public void testCreateUserInvalidCalorie() throws InvalidInputException {

		String username = "user1";
		String password = "1234";
		int calorieGoal = 100;
		String error = "";

		MealPlannerService service = new MealPlannerService(mp);

		try {
			service.createUser(username, password, calorieGoal);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("A minimum of 1200 calories is required for a healthy diet", error);
	}

	@Test
	public void testCreateUserExistingUser() throws InvalidInputException {

		String username1 = "user1";
		String password = "1234";
		int calorieGoal = 1200;
		String error = "";
		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username1, password, calorieGoal);
		String username2 = "user1";

		try {
			service.createUser(username2, password, calorieGoal);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("This username is taken.", error);
	}

	@Test
	public void testDeleteUser() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;

		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);

		try {
			service.deleteUser(username, password);
		} catch (InvalidInputException e) {
			e.getMessage();
		}

		assertEquals(0, mp.getUsers().size());

	}

	@Test
	public void testDeleteUserNullUsername() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		String error = "";

		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);

		try {
			service.deleteUser(null, password);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("Invalid username", error);

	}

	@Test
	public void testDeleteUserInvalidPassword() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		String error = "";

		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);

		try {
			service.deleteUser(username, "123");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("Incorrect password.", error);

	}

	@Test
	public void testTrackProgress() throws InvalidInputException {

		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;

		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);
		service.logMeal(username, "Pesto Kale Pasta", 100);
		int progress = 100 / calorieGoal;

		try {
			service.trackProgress(username);

		} catch (InvalidInputException e) {
			e.getMessage();
		}
		assertEquals(mp.getUser(0).getCurrentDay().getCalorieCount() / calorieGoal, progress);

	}

	@Test
	public void testTrackProgressNonExistingUser() throws InvalidInputException {

		String username = "sola";
		String password = "password1";
		int calorieGoal = 2000;
		String error = "";

		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);
		service.logMeal(username, "Pesto Kale Pasta", 100);

		try {
			service.trackProgress("mike");

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("User not found.", error);

	}

	@Test
	public void testTrackProgressNullUsername() throws InvalidInputException {

		String username = "sola";
		String password = "password1";
		int calorieGoal = 2000;
		String error = "";

		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);
		service.logMeal(username, "Pesto Kale Pasta", 100);

		try {
			service.trackProgress(null);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Invalid username", error);

	}

	@Test
	public void testViewSavedRecipes() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		Recipe r = new Recipe("Pestont", 800);
		List<Recipe> rlist = null;

		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);
		service.getUser(username).addSavedRecipe(r);

		try {
			rlist = service.viewSavedRecipes(username);

		} catch (InvalidInputException e) {
			e.getMessage();
		}

		assertEquals(1, rlist.size());
		assertEquals(service.getUser(username).getSavedRecipes(), rlist);
	}

	@Test
	public void testViewSavedRecipesNoRecipes() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		List<Recipe> rlist = null;
		String error = "";

		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);

		try {
			rlist = service.viewSavedRecipes(username);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("No saved recipes.", error);
	}

	@Test
	public void testViewSavedRecipesNullUsername() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		Recipe r = new Recipe("Pestont", 800);
		List<Recipe> rlist = null;
		String error = null;

		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);
		service.getUser(username).addSavedRecipe(r);

		try {
			rlist = service.viewSavedRecipes(null);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("Invalid username", error);

	}

	@Test
	public void testViewSavedRecipesNonExistingUser() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		Recipe r = new Recipe("Pestont", 800);
		List<Recipe> rlist = null;
		String error = null;

		MealPlannerService service = new MealPlannerService(mp);
		service.createUser(username, password, calorieGoal);
		service.getUser(username).addSavedRecipe(r);

		try {
			rlist = service.viewSavedRecipes("user0");

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("User not found.", error);

	}

	@Test
	public void testDeleteNonUser() throws InvalidInputException {
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
		boolean isValid = false;

		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);

		try {
			isValid = service.isValidLogin(username, password);

		} catch (InvalidInputException e) {
			e.getMessage();
		}

		assertEquals(true, isValid);

	}

	@Test
	public void testLoginNullUsername() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		boolean isValid = false;
		String error = "";

		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);

		try {
			isValid = service.isValidLogin(null, password);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(false, isValid);
		assertEquals("Invalid username", error);
	}

	@Test
	public void testLoginNullPassword() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		boolean isValid = false;
		String error = "";

		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);

		try {
			isValid = service.isValidLogin(username, null);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(false, isValid);
		assertEquals("Invalid password", error);
	}

	@Test
	public void testLoginNonExistingUser() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		boolean isValid = false;
		String error = "";

		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);

		try {
			isValid = service.isValidLogin("abed", password);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(false, isValid);
		assertEquals("Cannot find account for this username.", error);
	}

	@Test
	public void testLoginWrongPassword() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		boolean isValid = false;
		String error = "";

		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);

		try {
			isValid = service.isValidLogin(username, "123");

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals(false, isValid);
		assertEquals("Incorrect password.", error);
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

		try {
			service.enterOwnIngredient(username, ingType, numIngredients);

		} catch (InvalidInputException e) {
			e.getMessage();
		}

		assertEquals(1, service.getUser(username).getOwnedIngredients().size());
		assertEquals("banana", service.getUser(username).getOwnedIngredient(0).getIngredient().getName());
		assertEquals(3, service.getUser(username).getOwnedIngredient(0).getAmount());

	}

	@Test
	public void testEnterOwnedIngredientNullUsername() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int numIngredients = 3;
		String ingType = "banana";
		String error = "";
		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);

		try {
			service.enterOwnIngredient(null, ingType, numIngredients);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("Invalid username", error);

	}

	@Test
	public void testEnterOwnedIngredientInvalidIngredient() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int numIngredients = 3;
		String ingType = "";
		String error = "";
		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);

		try {
			service.enterOwnIngredient(username, ingType, numIngredients);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("Please Enter Valid Ingredient Name", error);

	}

	@Test
	public void testEnterOwnedIngredientInvalidAmount() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int numIngredients = 0;
		String ing = "banana";
		String error = "";
		MealPlannerService service = new MealPlannerService(mp);

		service.createUser(username, password, calorieGoal);

		try {
			service.enterOwnIngredient(username, ing, numIngredients);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("Amount must be bigger than 0", error);

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
	public void testAddToSavedRecipesNullRecipeName() throws InvalidInputException {
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
	public void testDeleteSavedRecipeNullRecipeName() throws InvalidInputException {
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
		assertEquals("This recipe was never saved.", error);
		assertEquals(savedRecipes.size(), numSavedRecipes);

	}

	@Test
	public void testLogMeal() throws InvalidInputException {
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int amount = 1;
		String recipeName = "Pesto Kale Pasta";
		Day today = new Day(new Date(0, 0, 0), 100);

		MealPlannerService service = new MealPlannerService(mp);
		User u = service.createUser(username, password, calorieGoal);
		u.setCurrentDay(today);

		Meal m = service.logMeal(username, recipeName, amount);
		assertEquals(m.getDay(), today);
		assertEquals(m.getServings(), amount);

	}

	@Test
	public void testLogMealInvalidAmount() throws InvalidInputException {
		String error = "";
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int amount = -1;
		String recipeName = "Pesto Kale Pasta";
		Day today = new Day(new Date(0, 0, 0), 100);

		MealPlannerService service = new MealPlannerService(mp);
		User u = service.createUser(username, password, calorieGoal);
		u.setCurrentDay(today);
		try {
			Meal m = service.logMeal(username, recipeName, amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(error, "Number of portions be 1 or more.");

	}

	@Test
	public void testLogMealInvalidRecipe() throws InvalidInputException {
		String error = "";
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int amount = 1;
		String recipeName = "";
		Day today = new Day(new Date(0, 0, 0), 100);

		MealPlannerService service = new MealPlannerService(mp);
		User u = service.createUser(username, password, calorieGoal);
		u.setCurrentDay(today);
		try {
			Meal m = service.logMeal(username, recipeName, amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(error, "Invalid recipe name.");

	}

	@Test
	public void testLogMealInvalidUser() throws InvalidInputException {
		String error = "";
		String username = "user1";
		String username2 = "user1234";
		String password = "password1";
		int calorieGoal = 2000;
		int amount = 1;
		String recipeName = "mars";
		Day today = new Day(new Date(0, 0, 0), 100);

		MealPlannerService service = new MealPlannerService(mp);
		User u = service.createUser(username, password, calorieGoal);
		u.setCurrentDay(today);
		try {
			Meal m = service.logMeal(username2, recipeName, amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(error, "User not found.");

	}
	
	@Test
	public void testLogMealNullUser() throws InvalidInputException {
		String error = "";
		String username = "user1";
		String username2 = "user1234";
		String password = "password1";
		int calorieGoal = 2000;
		int amount = 1;
		String recipeName = "mars";
		Day today = new Day(new Date(0, 0, 0), 100);

		MealPlannerService service = new MealPlannerService(mp);
		User u = service.createUser(username, password, calorieGoal);
		u.setCurrentDay(today);
		try {
			Meal m = service.logMeal(null, recipeName, amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(error, "Invalid username");

	}
	
	@Test
	public void testLogMealNonExistingRecipe() throws InvalidInputException {
		String error = "";
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int amount = 1;
		String recipeName = "mars";
		Day today = new Day(new Date(0, 0, 0), 100);

		MealPlannerService service = new MealPlannerService(mp);
		User u = service.createUser(username, password, calorieGoal);
		u.setCurrentDay(today);
		try {
			Meal m = service.logMeal(username, recipeName, amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals(error, "Recipe not found.");

	}

	@Test
	public void testRecommendedRecipes() throws InvalidInputException {

		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		Recipe r = null;

		Ingredient salt = new Ingredient("salt");
		Ingredient pepper = new Ingredient("pepper");

		MealPlannerService service = new MealPlannerService(mp);

		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(salt);
		Recipe r1 = service.createRecipe("recommendation", 20, ingredients);

		ingredients.add(pepper);
		User u = service.createUser(username, password, calorieGoal, ingredients);

		try {
		r = service.recommendRecipe(username);
		
		} catch (InvalidInputException e) {
			e.getMessage();
		}

		assertEquals(r, r1);

	}
	
	@Test
	public void testRecommendedRecipesNullUser() throws InvalidInputException {

		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		Recipe r = null;
		String error = "";

		Ingredient salt = new Ingredient("salt");
		Ingredient pepper = new Ingredient("pepper");

		MealPlannerService service = new MealPlannerService(mp);

		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(salt);
		Recipe r1 = service.createRecipe("recommendation", 20, ingredients);

		ingredients.add(pepper);
		User u = service.createUser(username, password, calorieGoal, ingredients);

		try {
		r = service.recommendRecipe(null);
		
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("Invalid username", error);

	}
	
	@Test
	public void testRecommendedRecipesNonExistingUser() throws InvalidInputException {

		String username = "user";
		String password = "password1";
		int calorieGoal = 2000;
		Recipe r = null;
		String error = "";

		Ingredient salt = new Ingredient("salt");
		Ingredient pepper = new Ingredient("pepper");

		MealPlannerService service = new MealPlannerService(mp);

		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(salt);
		Recipe r1 = service.createRecipe("recommendation", 20, ingredients);

		ingredients.add(pepper);
		User u = service.createUser(username, password, calorieGoal, ingredients);

		try {
		r = service.recommendRecipe("Lucy");
		
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		assertEquals("User not found", error);

	}

	@Test
	public void testRecommendedRecipesWithDietTypes() throws InvalidInputException {

		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;

		Ingredient salt = new Ingredient("salt");
		Ingredient pepper = new Ingredient("pepper");
		List<DietType> vegan = new ArrayList<DietType>();
		vegan.add(new DietType("vegan"));

		MealPlannerService service = new MealPlannerService(mp);

		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		ingredients.add(salt);
		service.createRecipe("recommendation", 20, ingredients);
		Recipe r1 = service.createRecipe("recommendation", 20, ingredients, vegan);

		ingredients.add(pepper);
		User u = service.createUser(username, password, calorieGoal, ingredients, vegan);

		Recipe r = service.recommendRecipe(username);

		assertEquals(r, r1);

	}
	
	@Test public void testUserEnterValidDietGoals() throws InvalidInputException{
		MealPlannerService service = new MealPlannerService(mp);
		
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int newGoal = 500;
		User u = service.createUser(username, password, calorieGoal);
		
		int newCalorieGoal = service.enterDietGoal(username, newGoal);
		assertEquals(newCalorieGoal, newGoal);

	}

	@Test public void testUserEnterDietGoalsInvalidUsername() throws InvalidInputException{
		MealPlannerService service = new MealPlannerService(mp);
		
		String error = "";
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int newGoal = 500;
		try {
			User u = service.createUser("user2", password, calorieGoal);
			int newCalorieGoal = service.enterDietGoal(username, newGoal);

		}
		catch(InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Username must be valid.", error);

	}
	
	@Test public void testUserEnterDietGoalsInvalidGoal() throws InvalidInputException{
		MealPlannerService service = new MealPlannerService(mp);
		
		String error = "";
		String username = "user1";
		String password = "password1";
		int calorieGoal = 2000;
		int newGoal = -1;
		try {
			User u = service.createUser(username, password, calorieGoal);
			int newCalorieGoal = service.enterDietGoal(username, newGoal);

		}
		catch(InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Goal must be a positive number.", error);

	}
}
