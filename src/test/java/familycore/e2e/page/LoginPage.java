package familycore.e2e.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.time.Duration;

public class LoginPage {

    private final String url;
    private final WebDriver driver;

    public LoginPage(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.url = String.format("%s/#/auth/sign-in", baseUrl);
    }

    public void login() {
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        var emailElement = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        var passwordElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/form/div[1]/div[2]/div/div/div/input"));
        var buttonElement = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/form/button"));

        emailElement.sendKeys("jd_fc@yopmail.com");
        passwordElement.sendKeys("jdPassword1");
        buttonElement.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));
        var code = GetVerificationCode();

        var codeVerificationElement = driver.findElement(By.xpath("//*[@id=\"one-time-code\"]"));
        codeVerificationElement.sendKeys(" ");
        codeVerificationElement.sendKeys(code);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        var textElement = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]"));
    }

    private String GetVerificationCode() {
        String originalWindow = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://yopmail.com/");
        waitFor(1000);

        var consentButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/div[2]/button[1]"));
        consentButton.click();

        var emailInput = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        emailInput.sendKeys("jd_fc");
        waitFor(1000);
        var checkEmailButton = driver.findElement(By.xpath("//*[@id=\"refreshbut\"]/button"));
        checkEmailButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));

        WebElement iframe = driver.findElement(By.cssSelector("#wmmailmain>iframe"));

        driver.switchTo().frame(iframe);

        var codeElement = driver.findElement(By.xpath("//*[@id=\"mail\"]/div/p[2]/b"));
        var code = codeElement.getText();

        driver.switchTo().window(originalWindow);
        return code;
    }

    private void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
