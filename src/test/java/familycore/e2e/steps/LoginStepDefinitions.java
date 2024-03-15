package familycore.e2e.steps;

import familycore.e2e.page.*;
import familycore.e2e.utils.VerificationCodeResolverTab;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class LoginStepDefinitions {
    private static final String verificationEmailReceiverUrl = "https://yopmail.com/";
    private String userName;
    private String password;
    private LoginPage loginPage;
    private WebDriver driver;
    private String code;
    private VerificationCodeResolverTab emailVerifier;

    @Before
    public void before() throws URISyntaxException, MalformedURLException {
        var options = new EdgeOptions();
        String gridUrl = "http://192.168.1.13:4444";
        driver = new RemoteWebDriver(new URI(gridUrl).toURL(), options);
        emailVerifier = VerificationCodeResolverTab
                .Builder
                .start()
                .setDriver(driver)
                .setUrl(verificationEmailReceiverUrl)
                .setVerificationCodeReceiverPage(new YopmailPage(driver))
                .createVerificationCodeResolverTab();
    }

    @Given("user with username {string} and password {string}")
    public void givenUserNameAndPassword(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    @When("user on log in page")
    public void userOnLogin() {
        String baseUrl = "http://qa.thefamilycore.com/";
        driver.get(String.format("%s/#/auth/sign-in", baseUrl));
        this.loginPage = new LoginPage(driver);
    }

    @And("user proceeds to log in")
    public void userProceedsToLogin() {
        this.loginPage.login(userName, password);
    }

    @And("user gets verification code")
    public void userGetsVerificationCode() {
        code = emailVerifier.getVerificationCode(userName);
    }

    @Then("user should be on verification page")
    public void userShouldBeOnDashboardPage() {
        var expectedVerificationRequestMessage = String.format("We’ve sent a confirmation link to %s. The link expires shortly, so please use it soon.", userName);
        var verificationCodeResolver = new ConfirmationPage(driver).getVerificationMessage();
        Assert.assertEquals(verificationCodeResolver, expectedVerificationRequestMessage);
    }

    @And("user enters verification code")
    public void userEntersVerificationCode() {
        new ConfirmationPage(driver).putVerificationCode(code);
    }

    @And("user select their family")
    public void userSubmitsVerificationCode() {
        new SelectFamilyPage(driver).selectMyFamily();
    }

    @Then("user should see verification")
    public void userShouldSeeVerification() {
        var expectedWelcomeMessage = "Hi John, Welcome Back!";
        var welcomeMessage = new DashboardPage(driver).getWelcomeMessage();
        Assert.assertEquals(welcomeMessage, expectedWelcomeMessage);
    }

    @After
    public void after() {
        driver.quit();
    }

    /*private void loginApi() {
        var email = "ao003587@mocvn.com";

        MessageDigest md = MessageDigest.getInstance("MD5");
        var bytes = ().getBytes();
        md.update(bytes);
        byte[] digest = md.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(String.format("https://privatix-temp-mail-v1.p.rapidapi.com/request/mail/id/%s/", hashtext))
                .get()
                .addHeader("X-RapidAPI-Key", "1JAxsXVZfumsh5ieTjHQNQcxu3PKp1MU4LUjsnuj2s0vOTyLeI")
                .addHeader("X-RapidAPI-Host", "privatix-temp-mail-v1.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        var s = response.body().string();
    }*/
}
