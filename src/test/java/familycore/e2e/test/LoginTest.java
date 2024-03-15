package familycore.e2e.test;

import familycore.e2e.page.ConfirmationPage;
import familycore.e2e.page.DashboardPage;
import familycore.e2e.page.LoginPage;
import familycore.e2e.page.YopmailPage;
import familycore.e2e.utils.VerificationCodeResolverTab;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class LoginTest {

    private final String gridUrl = "http://192.168.1.13:4444";
    private final String baseUrl = "http://qa.thefamilycore.com/";
    private static final String verificationEmailReceiverUrl = "https://yopmail.com/";
    private static final String userName = "jd_fc@yopmail.com";
    private static final String password = "jdPassword1";

    @Test
    public void loginShouldBePassedAndShowVerificationRequestMessage() throws MalformedURLException, URISyntaxException {

        var options = new ChromeOptions();
        var driver = new RemoteWebDriver(new URI(gridUrl).toURL(), options);
        try {
            var expectedVerificationRequestMessage = String.format("We’ve sent a confirmation link to %s. The link expires shortly, so please use it soon.", userName);
            driver.get(String.format("%s/#/auth/sign-in", baseUrl));
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(userName, password);
            var verificationCodeResolver = new ConfirmationPage(driver).getVerificationMessage();
            Assert.assertEquals(verificationCodeResolver, expectedVerificationRequestMessage);
        } finally {
            driver.quit();
        }
    }

    @Test
    public void loginWithEmailVerificationShouldBePassedAndShowHomePage() throws IOException, URISyntaxException {
        var options = new ChromeOptions();
        var driver = new RemoteWebDriver(new URI(gridUrl).toURL(), options);
        try {
            var expectedWelcomeMessage = "Hi John, Welcome Back!";
            driver.get(String.format("%s/#/auth/sign-in", baseUrl));
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(userName, password);

            var code = VerificationCodeResolverTab
                    .Builder
                    .start()
                    .setDriver(driver)
                    .setUrl(verificationEmailReceiverUrl)
                    .setVerificationCodeReceiverPage(new YopmailPage(driver))
                    .createVerificationCodeResolverTab()
                    .getVerificationCode(userName);

            new ConfirmationPage(driver).putVerificationCode(code);

            var welcomeMessage = new DashboardPage(driver).getWelcomeMessage();

            Assert.assertEquals(welcomeMessage, expectedWelcomeMessage);
        } finally {
            driver.quit();
        }
    }
}
