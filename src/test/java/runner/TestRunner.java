package runner;

import cucumber.api.CucumberOptions;


import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
        features = "src/test/java/features",
        glue = {"steps.Definitions"},
        tags = {"@smoke"},
        monochrome = true,
        strict = true,
        plugin = { "pretty", "html:target/cucumber-reports" }
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
