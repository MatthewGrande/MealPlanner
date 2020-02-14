package mealPlanner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import mealPlanner.service.TestMealPlannerService;
import mealPlanner.persistence.TestPersistence;


@RunWith(Suite.class)
@SuiteClasses({ TestMealPlannerService.class, TestPersistence.class })
public class AllTests {

}
