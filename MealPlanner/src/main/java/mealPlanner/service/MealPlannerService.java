package mealPlanner.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mealPlanner.service.InvalidInputException;
import mealPlanner.model.Day;
import mealPlanner.model.DietType;
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

		Recipe pestoKalePasta = new Recipe("Pesto Kale Pasta", 500);
		DietType vegeterian = new DietType("Vegeterian");
		pestoKalePasta.addDietType(vegeterian);
		Ingredient rapeseedoil = new Ingredient("rapeseed oil");
		Ingredient redOnion = new Ingredient("red Onion");
		Ingredient kale = new Ingredient("kale");
		Ingredient pasta = new Ingredient("pasta");
		Ingredient creamCheese = new Ingredient("cream cheese");
		Ingredient pesto = new Ingredient("pesto");
		ArrayList<Ingredient> pkpIng = new ArrayList<Ingredient>();
		pkpIng.add(pesto);
		pkpIng.add(creamCheese);
		pkpIng.add(rapeseedoil);
		pkpIng.add(redOnion);
		pkpIng.add(kale);
		pkpIng.add(pasta);
		pestoKalePasta.setIngredients(pkpIng);
		pestoKalePasta.setSteps("Heat the oil in a large pan over a medium heat. "
				+ "Fry the onions for 10 mins until softened and beginning to caramelise. "
				+ "Add the kale and 100ml water, then cover and cook for 5 mins more,"
				+ " or until the kale has wilted.----" + "Cook the pasta following pack instructions. "
				+ "Drain, reserving a little of the cooking water. "
				+ "Toss the pasta with the onion mixture, cream cheese and pesto, "
				+ "adding a splash of the reserved cooking water to loosen, if needed. Season.");

		Recipe smokedsalamonGratin = new Recipe("Smoked salmon & spinach gratin", 600);
		Ingredient spinach = new Ingredient("spinach");
		Ingredient butter = new Ingredient("butter");
		Ingredient salmon = new Ingredient("salmon fillets");
		Ingredient cream = new Ingredient("double cream");
		ArrayList<Ingredient> ssgIng = new ArrayList<Ingredient>();
		ssgIng.add(spinach);
		ssgIng.add(butter);
		ssgIng.add(salmon);
		ssgIng.add(cream);
		smokedsalamonGratin.setIngredients(ssgIng);
		smokedsalamonGratin.setSteps(
				"Put the spinach in a really big saucepan (or two saucepans) and add a few tablespoons of water. Cover, "
						+ "set over a medium heat and cook for about 5-8 mins, turning the spinach over every so often, "
						+ "until wilted. " + "Tip it into a colander to drain and allow it to cool "
						+ "(spread it out on a plate to cool it quicker). "
						+ "Take big handfuls of it in your fists and squeeze out the excess water. "
						+ "It�s really important that you do this, otherwise the water will leach out and make the cream watery and green.-----"
						+ "Chop the spinach. Melt the butter in a saucepan and gently toss the spinach in it. "
						+ "Season with pepper and a tiny bit of salt (there�s so much salt in the salmon). "
						+ "Heat the oven to 160C/140C fan/gas 3. Lay the spinach in the bottom of a gratin dish"
						+ " (about 30cm x 20cm), then arrange the salmon fillets on top."
						+ "Heat the double cream in a small pan, then pour it all over the salmon and spinach. "
						+ "Bake for 35 mins, or until the top is golden and the cream is bubbling.");

		Recipe tofuSoup = new Recipe("Miso mushroom & tofu noodle soup", 300);
		Ingredient mushrooms = new Ingredient("mushrooms");
		Ingredient tofu = new Ingredient("tofu");
		Ingredient brownrice = new Ingredient("brown rice");
		Ingredient noodles = new Ingredient("noodles");
		ArrayList<Ingredient> mmsIng = new ArrayList<Ingredient>();
		mmsIng.add(mushrooms);
		mmsIng.add(brownrice);
		mmsIng.add(noodles);
		mmsIng.add(redOnion);
		mmsIng.add(rapeseedoil);
		tofuSoup.setIngredients(mmsIng);
		tofuSoup.setSteps("Heat half the oil in a frying pan over a medium heat. "
				+ "Add the mushrooms and fry for 5-6 mins, or until golden. "
				+ "Transfer to a bowl using a slotted spoon and set aside. "
				+ "Add the remaining oil to the pan and fry the tofu for 3-4 mins, or until evenly golden.------"
				+ "Mix the miso paste with 325ml boiling water in a jug."
				+ " Cook the noodles following pack instructions," + " then drain and transfer to a bowl."
				+ " Top with the mushrooms and tofu, then pour over the miso broth."
				+ " Scatter over the spring onions just before serving.");
		this.mp.addRecipe(smokedsalamonGratin);
		this.mp.addRecipe(pestoKalePasta);
		this.mp.addRecipe(tofuSoup);

		PersistenceXStream.saveToXMLwithXStream(this.mp);

	}

	public User createUser(String username, String password, int calorieGoal) throws InvalidInputException {
		return createUser(username, password, calorieGoal, null, null);
	}

	public User createUser(String username, String password, int calorieGoal, List<Ingredient> ownedIngredients)
			throws InvalidInputException {
		return createUser(username, password, calorieGoal, ownedIngredients, null);
	}

	public User createUser(String username, String password, int calorieGoal, List<Ingredient> ownedIngredients,
			List<DietType> dietTypes) throws InvalidInputException {

		if (username == null || username == "" || password == "" || password == null) {
			throw new InvalidInputException("Invalid user parameters");
		}

		if (calorieGoal < 1200) {
			throw new InvalidInputException("A minimum of 1200 calories is required for a healthy diet");

		}

		// Check if username is already in database
		if (getUser(username) != null) {
			throw new InvalidInputException("This username is taken.");
		}

		Day today = new Day(new Date(0, 0, 0), 0);
		User u = new User(username, password, calorieGoal, today);

		if (ownedIngredients != null) {
			for (Ingredient i : ownedIngredients) {
				u.addOwnedIngredient(1, i);
			}
		}
		if (dietTypes != null) {
			for (DietType d : dietTypes) {
				u.addDietType(d);
			}
		}
		mp.addUser(u);
		PersistenceXStream.saveToXMLwithXStream(mp);
		return u;
	}

	/**
	 * 
	 * @param username
	 * @return all saved recipes
	 * @throws InvalidInputException
	 */
	public List<Recipe> viewSavedRecipes(String username) throws InvalidInputException {
		User u = getUser(username);

		if (u == null) {
			throw new InvalidInputException("User not found.");
		}

		List<Recipe> savedRecipes = u.getSavedRecipes();

		if (savedRecipes == null || savedRecipes.size() == 0) {

			throw new InvalidInputException("No saved recipes.");

		}

		return savedRecipes;

	}

	/**
	 * Method that tracks user's progress
	 * 
	 * @param username
	 * @return (calorie consumed / calorie goal) of the day
	 * @throws InvalidInputException
	 */
	public int trackProgress(String username) throws InvalidInputException {

		User user = null;

		if (username == null) {
			throw new InvalidInputException("Invalid username");
		}

		// Check if username is already in database
		List<User> userList = mp.getUsers();
		for (User u : userList) {
			if (u.getUsername().contentEquals(username)) {
				user = u;
			}
		}

		// Throw Exception if user doesnt exist
		if (user == null) {
			throw new InvalidInputException("User not found.");
		}

		int calorieGoal = user.getGoalCalorie();

		// Get current day's calorie count
		int currentCalorieCount = user.getCurrentDay().getCalorieCount();

		int progress = currentCalorieCount / calorieGoal;

		return progress;
	}

	public User deleteUser(String username, String password) throws InvalidInputException {
		User u = getUser(username);

		if (isValidLogin(username, password)) {
			mp.removeUser(u);
		}

		PersistenceXStream.saveToXMLwithXStream(mp);
		return u;
	}

	public User getUser(String username) throws InvalidInputException {
		List<User> users = mp.getUsers();

		if (username == null || username == "") {
			throw new InvalidInputException("Invalid username");
		}

		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

	public Meal logMeal(String username, String recipe_name, int amount) throws InvalidInputException {
		if (recipe_name == "" || recipe_name == null) {
			throw new InvalidInputException("Invalid recipe name.");
		}
		if (amount < 1) {
			throw new InvalidInputException("Number of portions be 1 or more.");
		}

		User u = getUser(username);
		if (u == null) {
			throw new InvalidInputException("User not found.");

		}
		Day day = u.getCurrentDay();
		Recipe recipe = mp.getRecipe(recipe_name);

		if (recipe == null) {
			throw new InvalidInputException("Recipe not found.");
		}
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

		if (password == null) {
			throw new InvalidInputException("Invalid password");
		}

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

		// check ingredientName
		if (ingredientName.equals(null) || ingredientName.equals("")) {
			throw new InvalidInputException("Please Enter Valid Ingredient Name");
		}

		if (amount <= 0) {
			throw new InvalidInputException("Amount must be bigger than 0");

		}
		User user = getUser(username);

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
		List<Recipe> recipeList = mp.getRecipes();

		if (user == null) {
			throw new InvalidInputException("Invalid user.");
		}

		if (newRecipeName.equals(null) || newRecipeName.equals("")) {
			throw new InvalidInputException("Please Enter Valid Recipe Name");
		}

		Recipe newRecipe = mp.getRecipe(newRecipeName);

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

		if (user == null) {
			throw new InvalidInputException("Invalid user.");
		}

		List<Recipe> savedRecipeList = user.getSavedRecipes();

		if (savedRecipeName.equals(null) || savedRecipeName.equals("")) {
			throw new InvalidInputException("Please Enter Valid Saved Recipe Name");
		}

		Recipe savedRecipe = mp.getRecipe(savedRecipeName);

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

	/**
	 * Recommend a recipe to a user based on their owned ingredients
	 * 
	 * @param username
	 * @return list of recommended recipes
	 */
	public Recipe recommendRecipe(String username) throws InvalidInputException {
		User user = null;

		user = getUser(username);

		if (user == null) {
			throw new InvalidInputException("User not found");
		}

		List<DietType> userDietTypes = user.getDietType();

		for (Recipe r : mp.getRecipes()) {
			boolean hasIngredients = true;
			int index = 0;
			
			while (index < r.getIngredients().size() && hasIngredients) {
				if (user.getOwnedIngredient(r.getIngredients().get(index).getName()) == null) {
					hasIngredients = false;
				}
				index++;
			}
			
			boolean matchesDietType = true;
			List<DietType> recipeDietTypes = r.getDietType();
			
			for (DietType d : userDietTypes) {
				matchesDietType = false;
				for (DietType d2 : recipeDietTypes) {
					if (d.getType().equals(d2.getType())) {
						matchesDietType = true;
					}
				}
			}
			if (hasIngredients && matchesDietType) {
				return r;
			}
		}
		return null;

	}
	
	/**
	 * Recommend recipe to be used for gherkin test.
	 * @param ingNames
	 * @return
	 */
	public ArrayList<String> recommendRecipeFromProvidedIngredients(List<String> ingNames){
		ArrayList<String> ar = new ArrayList<String>();

		for (Recipe r : mp.getRecipes()) {
			for (String ingName: ingNames) {
				if (!r.getName().contains(ingName)) {
					continue;
				}
				ar.add(r.getName());
			}
		}
		return ar;
	}
	
	public Recipe createRecipe(String name, int calories, List<Ingredient> ingredients) {
		return createRecipe(name, calories, ingredients, null);
	}

	public Recipe createRecipe(String name, int calories, List<Ingredient> ingredients, List<DietType> dietTypes) {
		Recipe r = new Recipe(name, calories);
		r.setIngredients(ingredients);

		if (dietTypes != null) {
			for (DietType d : dietTypes) {
				r.addDietType(d);
			}
		}
		mp.addRecipe(r);
		PersistenceXStream.saveToXMLwithXStream(mp);
		return r;
	}
	
	public int enterDietGoal(String username, int calorieGoal) throws InvalidInputException{
		User u = getUser(username);
		if(u == null) {
			throw new InvalidInputException("Username must be valid.");
		}
		if(calorieGoal < 0) {
			throw new InvalidInputException("Goal must be a positive number.");
		}
		u.setGoalCalorie(calorieGoal);
		
		return calorieGoal;
	}

}
