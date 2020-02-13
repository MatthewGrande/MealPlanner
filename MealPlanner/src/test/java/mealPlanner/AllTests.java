package mealPlanner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import mealPlanner.service.MealPlannerService;
import mealPlanner.persistence.TestPersistence;


@RunWith(Suite.class)
@SuiteClasses({ MealPlannerService.class, TestPersistence.class })
public class AllTests {

}
