package runner;

import io.cucumber.testng.CucumberOptions;
import tests.TestBase;

@CucumberOptions(features = "src/test/java/features",
        glue = {"steps"},
        tags = "@E2E or @lockedUser or @invalidCredentials or @errorUser or @problemUser"
)
public class TestRunner extends TestBase {


}
