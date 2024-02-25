package familycore.e2e.test;

import familycore.e2e.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver driver;
    private String baseUrl = "http://qa.thefamilycore.com/";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage(driver, baseUrl);
        loginPage.login();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
