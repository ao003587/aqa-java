package familycore.e2e.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class SelectFamilyPage extends PageObject {

    public static final By familyListBy = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/div[1]/div/div");
    public static final By familyNameBy = By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[2]");
    public static final By submitBy = By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div[1]/div/button");

    public SelectFamilyPage(WebDriver driver) {
        super(driver);
    }

    public void selectMyFamily() {

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .until(webDriver -> !webDriver.findElements(familyListBy).isEmpty());

        driver.findElement(familyListBy).click();
        driver.findElement(familyNameBy).click();
        driver.findElement(submitBy).click();
    }
}
