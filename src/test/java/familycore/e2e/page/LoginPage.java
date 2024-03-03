package familycore.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Objects;

public class LoginPage {
    public static final By emailBy = By.xpath("//*[@id=\"email\"]");
    public static final By passwordBy = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/form/div[1]/div[2]/div/div/div/input");
    public static final By signInBy = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/form/button");
    public static final By errorMessageBy = By.xpath("//*[@id=\"email-helper-text\"]");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        var emailElement = driver.findElement(emailBy);
        var passwordElement = driver.findElement(passwordBy);
        emailElement.sendKeys(username);
        passwordElement.sendKeys(password);
        tryLogin(5);
    }

    private void tryLogin(int attempts) {
        var buttonElement = driver.findElement(signInBy);
        buttonElement.click();
        var errorElements = driver.findElements(errorMessageBy);
        if(!errorElements.isEmpty() && Objects.equals(errorElements.getFirst().getText(), "Something went wrong, please try again!") && attempts > 1) {
            new Actions(driver).pause(500).build().perform();
            tryLogin(attempts - 1);
        }
    }
}
