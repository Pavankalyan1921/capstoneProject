package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",   // Path to your feature files
        glue = "stepDefinitions",                   // Package containing step definitions
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber-reports.json"  // Optional JSON report for CI
        },
        monochrome = true
)
public class AppTestRunner extends AbstractTestNGCucumberTests {
    // This class integrates Cucumber with TestNG.
    // No additional code is needed here.
}
