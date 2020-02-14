package mealPlanner.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Repository;
import com.thoughtworks.xstream.XStream;

import mealPlanner.model.MealManager;
import mealPlanner.model.MealPlannerApp;
import mealPlanner.model.Recipe;
import mealPlanner.model.User;

// The first type parameter is the domain type for which we are creating the repository.
// The second is the key type that is used to look it up. This example will not use it.
@Repository
public class PersistenceXStream {

	private static XStream xstream = new XStream();
	// private static String filename = "/tomcatoutput/data.xml"; //For later use if
	// a server is required
	private static String filename = "data.xml";

	public static MealManager initializeModelManager(String fileName) {

		MealManager mm;

		setAlias("User", User.class);
		setAlias("MealPlanner", MealPlannerApp.class);
		setAlias("Recipe", Recipe.class);
		// load model if exists, create otherwise
		File file = new File(fileName);
		if (file.exists()) {
			mm = (MealManager) loadFromXMLwithXStream();
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			mm = new MealManager();
			saveToXMLwithXStream(mm);
		}
		return mm;
	}

	public static boolean saveToXMLwithXStream(Object obj) {
		xstream.setMode(XStream.ID_REFERENCES);
		String xml = xstream.toXML(obj); // save our xml file

		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(xml);
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Object loadFromXMLwithXStream() {
		xstream.setMode(XStream.ID_REFERENCES);
		try {
			FileReader fileReader = new FileReader(filename); // load our xml file
			return xstream.fromXML(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void setAlias(String xmlTagName, Class<?> className) {
		xstream.alias(xmlTagName, className);
	}

	public static void setFilename(String fn) {
		filename = fn;
	}

	public static String getFilename() {
		return filename;
	}

	public static void clearData() {
		File myFoo = new File(filename);
		FileWriter fooWriter;
		try {
			fooWriter = new FileWriter(myFoo, false);
			fooWriter.write("");
			fooWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
