package familycore.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class DashboardPage extends PageObject {
    private static final By welcomeMessageBy = By.xpath("/html/body/div[1]/div/div/div[2]/div[1]");
    private static final String pageName = "dashboard";

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeMessage() {

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .until(webDriver -> driver.getCurrentUrl().contains(pageName) && !driver.findElement(welcomeMessageBy).getText().isEmpty());

        return driver.findElement(welcomeMessageBy).getText();
    }
}
