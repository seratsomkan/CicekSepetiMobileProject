package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utilities.RenameAllureJsonFiles;

@RunWith(Cucumber.class)
@CucumberOptions(
        //plugin = {"pretty",
        //        "json:target/cucumber.json",},
        plugin = {"pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        features = "src/test/resources/Features",
        glue="stepDefinitions",
        tags = "@E2E",
        dryRun = false
)

public class RunnerTest {
    @BeforeClass
    public static void setup() {
        RenameAllureJsonFiles.rememberExistingFiles();
    }

    @AfterClass
    public static void tearDown() {
        RenameAllureJsonFiles.renameOnlyNewFiles("E2E");
    }
}
