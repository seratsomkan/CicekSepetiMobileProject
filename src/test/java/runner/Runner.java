package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //plugin = {"pretty",
        //        "json:target/cucumber.json",},
        plugin = {"pretty",
                "html:target/cucumber-reports/regression.html"},
        features = "src/test/resources/Features",
        glue="stepDefinitions",
        tags = "@LoginTest",
        dryRun = false

)

public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);

}
