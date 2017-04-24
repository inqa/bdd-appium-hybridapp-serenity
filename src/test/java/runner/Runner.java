package runner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = {"pretty:output", "html:target/cucumber"},
        features = { "src/test/resources/features"},
        tags = {"@my"},
        glue = {"StepDefs"})
public class Runner {}
