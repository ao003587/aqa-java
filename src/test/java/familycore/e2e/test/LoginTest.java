package familycore.e2e.test;

import familycore.e2e.Utils.VerificationCodeResolverTab;
import familycore.e2e.page.DashboardPage;
import familycore.e2e.page.LoginPage;
import familycore.e2e.page.ConfirmationPage;
import familycore.e2e.page.YopmailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    private WebDriver driver;
    private String baseUrl = "http://qa.thefamilycore.com/";
    private static final String verificationEmailReceiverUrl = "https://yopmail.com/";
    private static final String userName = "jd_fc@yopmail.com";
    private static final String password = "jdPassword1";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void loginShouldBePassedAndShowVerificationRequestMessage() throws InterruptedException {
        var expectedVerificationRequestMessage = String.format("We’ve sent a confirmation link to %s. The link expires shortly, so please use it soon.", userName);
        driver.get(String.format("%s/#/auth/sign-in", baseUrl));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, password);
        var verificationCodeResolver = new ConfirmationPage(driver).getVerificationMessage();
        Assert.assertEquals(verificationCodeResolver, expectedVerificationRequestMessage);
    }

    @Test
    public void loginWithEmailVerificationShouldBePassedAndShowHomePage() {

        var expectedWelcomeMessage = "Hi John, Welcome Back!";
        driver.get(String.format("%s/#/auth/sign-in", baseUrl));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName,password);

        var verificationCodeResolver = VerificationCodeResolverTab
                .Builder
                .start()
                .setDriver(driver)
                .setUrl(verificationEmailReceiverUrl)
                .setVerificationCodeReceiverPage(new YopmailPage(driver))
                .createVerificationCodeResolverTab();
        var code = verificationCodeResolver.getVerificationCode(userName);

        new ConfirmationPage(driver).putVerificationCode(code);

        var welcomeMessage = new DashboardPage(driver).getWelcomeMessage();

        Assert.assertEquals(welcomeMessage, expectedWelcomeMessage);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
