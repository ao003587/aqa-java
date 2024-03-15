package familycore.e2e.page;

import org.openqa.selenium.WebDriver;

public abstract class PageObject {

    protected final WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
    }
}
