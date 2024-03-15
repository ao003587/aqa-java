package familycore.e2e.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/familycore/e2e/login.feature"}, glue = {"familycore.e2e.steps"},
        plugin = {"pretty"})
public class LoginVerificationTest extends AbstractTestNGCucumberTests {

}