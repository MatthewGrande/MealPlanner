package hellocucumber;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", 
features = "src/test/resources",
glue = "src/test/java/hellocucumber")
public class RunCucumberTest {
}
