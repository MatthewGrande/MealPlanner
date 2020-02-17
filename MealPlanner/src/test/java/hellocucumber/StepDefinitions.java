package hellocucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import mealPlanner.model.Day;
import mealPlanner.model.Ingredient;
import mealPlanner.model.MealPlannerApp;
import mealPlanner.model.OwnedIngredient;
import mealPlanner.model.User;
import mealPlanner.persistence.PersistenceXStream;
import mealPlanner.service.InvalidInputException;
import mealPlanner.service.MealPlannerService;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import cucumber.api.PendingException;

public class StepDefinitions {
    private String today;
    private String actualAnswer;

	private MealPlannerApp mp;
	private MealPlannerService service;
	
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
	public void julianna_inputs_her_username_password_and_email_to_create_an_account(DataTable dataTable) {
		for (Map<String, String> x: dataTable.asMaps()) {
			try {
				service.createUser(x.get("<username>"), x.get("<password>"), 0);
			}
			catch (InvalidInputException e) {
			}
		}
	}
	
	@Then("the final List of users in the MealPLannerService is:")
	public void the_final_List_of_users_in_the_MealPLannerService_is(DataTable dataTable) {
		for (Map<String, String> x: dataTable.asMaps()) {
			User y = service.getUser(x.get("<username>"));
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
			}
			catch (InvalidInputException e) {
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
	public void the_User_owns_the_following_Ingredients(String string, DataTable dataTable) {
		setup();
		
		User u = null;
		try {
			u = this.service.createUser(string, "pass", 0);
		}
		catch (InvalidInputException e) {
		}
		
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
	public void the_User_now_owns_the_following_Ingredients(String string, DataTable dataTable) {
		for (Map<String, String> x: dataTable.asMaps()) {
			User y = service.getUser(x.get("<username>"));
			assertEquals(y.getOwnedIngredient(x.get("<ingredientName>")).getAmount(), Integer.parseInt(x.get("<quantity>")));
		}
		clean();
	}
   
}

class IsItFriday {
    static String isItFriday(String today) {
        return "Friday".equals(today) ? "TGIF" : "Nope";
    }
}
