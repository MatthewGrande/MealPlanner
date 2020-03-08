package hellocucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import mealPlanner.model.Day;
import mealPlanner.model.Ingredient;
import mealPlanner.model.Meal;
import mealPlanner.model.MealPlannerApp;
import mealPlanner.model.OwnedIngredient;
import mealPlanner.model.Recipe;
import mealPlanner.model.User;
import mealPlanner.persistence.PersistenceXStream;
import mealPlanner.service.InvalidInputException;
import mealPlanner.service.MealPlannerService;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cucumber.api.PendingException;

public class StepDefinitions {
    private String today;
    private String actualAnswer;

	private MealPlannerApp mp;
	private MealPlannerService service;
	private Meal loggedMeal = null;
	
	private Day date;
	private ArrayList<String> suggestedRecipe;
    private void setup() {
		this.mp = new MealPlannerApp();
		
		// initialize model file
		PersistenceXStream.initializeModelManager( "data.xml");
		// save model that is loaded during test setup
		if (!PersistenceXStream.saveToXMLwithXStream(mp)) {
			fail("Could not save file.");
		}
		
		// clear the model in memory
		 this.mp.delete();
		 

		// load model
		this.mp = (MealPlannerApp) PersistenceXStream.loadFromXMLwithXStream();
		if (mp == null) {
			fail("Could not load file.");
		}
		this.service = new MealPlannerService(mp);
    }

    private void clean() {
		 mp.delete();
    }
    
    //Sample "today is friday"
    @Given("today is {string}")
    public void today_is(String today) {
        this.today = today;
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        actualAnswer = IsItFriday.isItFriday(today);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }
    
    //For "Create Account":
	@Given("the initial List of users in the MealPlannerService is:")
	public void the_initial_List_of_users_in_the_MealPLannerService_is(DataTable dataTable) {
		setup();
		for (Map<String, String> x: dataTable.asMaps()) {
			Day today = new Day(new Date(0, 0, 0), 0);
			User u = new User(x.get("<username>"), x.get("<password>"), 0,  today);
			mp.addUser(u);
		}
	}
	
	@When("Julianna inputs her <username>, <password>, and <email> to create an account:")
	public void julianna_inputs_her_username_password_and_email_to_create_an_account(DataTable dataTable) throws InvalidInputException {
		for (Map<String, String> x: dataTable.asMaps()) {

			try {
				service.createUser(x.get("<username>"), x.get("<password>"), 1500);
			}
			catch (InvalidInputException e) {
			}


		}
	}
	
	@Then("the final List of users in the MealPLannerService is:")
	public void the_final_List_of_users_in_the_MealPLannerService_is(DataTable dataTable) {
		for (Map<String, String> x: dataTable.asMaps()) {
			User y = null;
			try {
				y = service.getUser(x.get("<username>"));
			} catch (InvalidInputException e) {
			}
			assertEquals(y.getPassword(), x.get("<password>"));
		}
		clean();
	}
	
	// Logged in 
	@When("a user inputs their <username> and <password> to login:")
	public void a_user_inputs_their_username_and_password_to_login(DataTable dataTable) {
		for (Map<String, String> x: dataTable.asMaps()) {

				try {
					this.service.isValidLogin(x.get("<username>"), x.get("<password>"));
				} catch (InvalidInputException e) {
				}

		}
	}

	@Then("the user should be logged in as {string}")
	public void the_user_should_be_logged_in_as(String string) {
		User loggedIn = this.service.getLoggedInUser();
		if (loggedIn != null) {
			assertEquals(string, loggedIn.getUsername());
		}
		clean();
	}

	@Then("the user should not be logged in")
	public void the_user_should_not_be_logged_in() {
		User loggedIn = this.service.getLoggedInUser();
		assertEquals(loggedIn, null);
	    clean();
	}
	
	//Enter Owned Ingredients gherkins
	@Given("the User {string} owns the following <Ingredients>:")
	public void the_User_owns_the_following_Ingredients(String string, DataTable dataTable) throws InvalidInputException {
		setup();
		
		User u = null;
			u = this.service.createUser(string, "pass", 1300);
		
		for (Map<String, String> x: dataTable.asMaps()) {
			Ingredient i = new Ingredient(x.get("<ingredientName>"));
			OwnedIngredient owned_i = new OwnedIngredient(Integer.parseInt(x.get("<quantity>")), i, u);
			u.addOwnedIngredient(owned_i);
		}
	}

	@When("{string} is on the add own ingredient menu and add my <ingredientName> and <Amount>:")
	public void is_on_the_add_own_ingredient_menu_and_add_my_ingredientName_and_Amount(String string, DataTable dataTable) {
		for (Map<String, String> x: dataTable.asMaps()) {
			try {
				service.enterOwnIngredient(string, x.get("<ingredientName>"), Integer.parseInt(x.get("<quantity>")));
			} catch (NumberFormatException e) {
			} catch (InvalidInputException e) {
			}
		}
	}

	@Then("the User {string} now owns the following <Ingredients>:")
	public void the_User_now_owns_the_following_Ingredients(String string, DataTable dataTable) throws InvalidInputException {
		for (Map<String, String> x: dataTable.asMaps()) {
			User y = null;
			try {
				y = service.getUser(x.get("<username>"));
			} catch (InvalidInputException e) {
			}

			assertEquals(y.getOwnedIngredient(x.get("<ingredientName>")).getAmount(), Integer.parseInt(x.get("<quantity>")));
		}
		clean();
	}
	

	@When("Julianna inputs her <username> {string} and <password> {string} to delete an account")
	public void julianna_inputs_her_username_and_password_to_delete_an_account(String string, String string2) {

		try {
			service.deleteUser(string, string2);
		} catch (InvalidInputException e) {
		}
	}
	
	//Log Meal Gherkin:
	@Given("The following list of meals exists in the MealPlanner database:")
	public void the_following_list_of_meals_exists_in_the_MealPlanner_database(DataTable dataTable) {
		setup();
		for (Map<String, String> x: dataTable.asMaps()) {
			Recipe r = new Recipe(x.get("<Recipe>"), Integer.parseInt(x.get("<CalorieCount>")));
			mp.addRecipe(r);
		}
	}
	
	@When("{string} logs the following meal:")
	public void logs_the_following_meal(String string, DataTable dataTable) throws InvalidInputException {
		String username = string;
		String password = "temp";
		this.date = new Day(new Date(0,0,0), 100);
		int calorieGoal = 2000;
		User u = service.createUser(username, password, calorieGoal);
		u.setCurrentDay(date);
		
		for (Map<String, String> x: dataTable.asMaps()) {
			try {
				this.loggedMeal = service.logMeal(username, x.get("<Recipe>"), Integer.parseInt(x.get("<servings>")));
			} catch (NumberFormatException e) {
			} catch (InvalidInputException e) {
			}
			
		}
	}

	@Then("The list of user {string} logged meals is updated.")
	public void the_list_of_user_logged_meals_is_updated(String string, DataTable dataTable) {
		
		for (Map<String, String> x: dataTable.asMaps()) {
			assertEquals(this.loggedMeal.getRecipe().getName(), x.get("<Meal>"));
		}
		clean();
	}
	
	@Then("{string} meal is not logged")
	public void meal_is_not_logged(String string) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(this.loggedMeal, null);
		clean();
	}
	
	
	//View Meal Suggestions:
	
	@When("{string} is on the meal suggestion menu and specifies an <ingredientName>:")
	public void is_on_the_meal_suggestion_menu_and_specifies_an_ingredientName(String string, DataTable dataTable) {
		
		ArrayList<String> providedIngredients = new ArrayList<String>();
		
		for (Map<String, String> x: dataTable.asMaps()) {
			providedIngredients.add(x.get("<ingredientName>"));
		}
		this.suggestedRecipe = this.service.recommendRecipeFromProvidedIngredients(providedIngredients);
	}

	@Then("I should receive meal suggestions including the <IngredientName>")
	public void i_should_receive_meal_suggestions_including_the_IngredientName(DataTable dataTable) {
		for (Map<String, String> x: dataTable.asMaps()) {
			assertEquals(this.suggestedRecipe.contains(x.get("<recipe>")), true);
		}
	    clean();
	}
	
	//View saved Meals:
	@When("user {string} is on the recipes page and saves the following <recipe>:")
	public void user_is_on_the_recipes_page_and_saves_the_following_recipe(String string, DataTable dataTable) throws InvalidInputException {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		String username = string;
		String password = "temp";
		int calorieGoal = 2000;
		User u = service.createUser(username, password, calorieGoal);
		
		for (Map<String, String> x: dataTable.asMaps()) {
			try {
				service.addToSavedRecipes(username, x.get("<recipe>"));
			} catch (NumberFormatException e) {
			} catch (InvalidInputException e) {
			}
			
		}
	}

	@Then("the User {string} now has following <savedRecipes>:")
	public void the_User_now_has_following_savedRecipes(String string, DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		try {
			List<Recipe> r = service.viewSavedRecipes(string);
			ArrayList<String> savedRecipeNames = new ArrayList<String>();
			for (Recipe tempR : r) {
				savedRecipeNames.add(tempR.getName());
			}
			
			for (Map<String, String> x: dataTable.asMaps()) {
				assertEquals(savedRecipeNames.contains(x.get("<savedRecipes>")), true);
			}
		}
		catch (InvalidInputException e) {
			assert(false);
		}
	    clean();
	}

	@Then("the User {string} has no saved recipes")
	public void the_User_has_no_saved_recipes(String string) {
		try {
			List<Recipe> r = service.viewSavedRecipes(string);
		}
		catch (InvalidInputException e){
			assertEquals(e.getMessage(), "No saved recipes.");
		}
		clean();
	}
}

class IsItFriday {
    static String isItFriday(String today) {
        return "Friday".equals(today) ? "TGIF" : "Nope";
    }
}
