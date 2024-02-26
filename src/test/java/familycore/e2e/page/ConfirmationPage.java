package familycore.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ConfirmationPage {
    private static final By verificationCodeBy = By.xpath("//*[@id=\"one-time-code\"]");
    private static final By verificationMessageBy = By.xpath("/html/body/div/div/div/div[2]");
    private static final String pageName = "confirmation";
    private final WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void putVerificationCode(String code) {
        var codeVerificationElement = driver.findElement(verificationCodeBy);
        codeVerificationElement.sendKeys(String.format(" %s", code));
    }

    public String getVerificationMessage() {

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .until(webDriver -> driver.getCurrentUrl().contains(pageName));

        return driver.findElement(verificationMessageBy).getText();
    }
}
