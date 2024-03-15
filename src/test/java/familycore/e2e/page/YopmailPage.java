package familycore.e2e.page;

import familycore.e2e.page.interfaces.VerificationCodeReceiverPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class YopmailPage extends PageObject implements VerificationCodeReceiverPage {

    public static final By consentBy = By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/div[2]/button[1]");
    public static final By emailBy = By.xpath("//*[@id=\"login\"]");
    public static final By checkEmailBy = By.xpath("//*[@id=\"refreshbut\"]/button");
    public static final By emailContentFrameBy = By.cssSelector("#wmmailmain>iframe");
    public static final By codeBy = By.xpath("//*[@id=\"mail\"]/div/p[2]/b");

    public YopmailPage(WebDriver driver) {
        super(driver);
    }

    public String getVerificationCode(String email) {

        var actions = new Actions(driver);
        var consentButton = driver.findElement(consentBy);
        consentButton.click();

        var emailInput = driver.findElement(emailBy);
        emailInput.sendKeys(email);

        actions.pause(2000).build().perform();

        var checkEmailButton = driver.findElement(checkEmailBy);
        checkEmailButton.click();

        WebElement iframe = driver.findElement(emailContentFrameBy);
        driver.switchTo().frame(iframe);

        var codeElement = driver.findElement(codeBy);
        return codeElement.getText();

    }
}
