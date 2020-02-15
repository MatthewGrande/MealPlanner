package mealPlanner.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
		PersistenceXStream.initializeModelManager( "data.xml");
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
}
