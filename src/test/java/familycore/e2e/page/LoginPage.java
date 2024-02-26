package familycore.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage {
    public static final By emailBy = By.xpath("//*[@id=\"email\"]");
    public static final By passwordBy = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/form/div[1]/div[2]/div/div/div/input");
    public static final By signInBy = By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/form/button");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        var emailElement = driver.findElement(emailBy);
        var passwordElement = driver.findElement(passwordBy);
        var buttonElement = driver.findElement(signInBy);
        emailElement.sendKeys(username);
        passwordElement.sendKeys(password);
        buttonElement.click();
    }
}
