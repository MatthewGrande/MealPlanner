package hellocucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import mealPlanner.model.User;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;

public class StepDefinitions {
    private String today;
    private String actualAnswer;

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
    
    //For "Create Account"
    
    private List<User> userList;
	@Given("the initial List of users in the MealPLannerService is:")
	public void the_initial_List_of_users_in_the_MealPLannerService_is(DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
		System.out.println("AHHHHH");
	    // For other transformations you can register a DataTableType.
		for (Map<String, String> x: dataTable.asMaps()) {
			System.out.println(x.keySet());
		}
	}
	
	@When("Julianna inputs her <name>, <username>, and <password> to create an account:")
	public void julianna_inputs_her_name_username_and_password_to_create_an_account(DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	//    throw new PendingException();
	}
	
	
	@When("Julianna inputs her <username>, <password>, and <email> to create an account:")
	public void julianna_inputs_her_username_password_and_email_to_create_an_account(DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	//    throw new PendingException();
	    assertEquals(true, true);
	}


   
}

class IsItFriday {
    static String isItFriday(String today) {
        return "Friday".equals(today) ? "TGIF" : "Nope";
    }
}