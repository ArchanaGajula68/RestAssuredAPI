package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/Getcall.feature",
        glue = {"steps.Definitions"},
        monochrome = true,
        strict = true,
        plugin = { "pretty", "html:target/cucumber-reports" }
)
public class TestRunner {

}
