package mealPlanner.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import mealPlanner.model.*;

public class TestPersistence {

	private MealManager mm;

	@Before
	public void setUp() throws Exception {
		mm = new MealManager();

	}

	@After
	public void tearDown() throws Exception {
		// mm.delete();
	}

	@Test
	public void test() {
		// initialize model file
		
		PersistenceXStream.initializeModelManager("output" + File.separator + "data.xml");
		// save model that is loaded during test setup
		if (!PersistenceXStream.saveToXMLwithXStream(mm)) {
			fail("Could not save file.");
		}
		
		// clear the model in memory
		// mm.delete();
		// assertEquals(0, mm.getTrees().size());

		// load model
		mm = (MealManager) PersistenceXStream.loadFromXMLwithXStream();
		if (mm == null) {
			fail("Could not load file.");
		}

	}
}
