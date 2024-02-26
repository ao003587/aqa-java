package familycore.e2e.Utils;

import familycore.e2e.Utils.interfaces.VerificationCodeResolver;
import familycore.e2e.page.interfaces.VerificationCodeReceiverPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class VerificationCodeResolverTab implements VerificationCodeResolver {

    private final WebDriver driver;
    private final VerificationCodeReceiverPage verificationCodeReceiverPage;
    private final String url;

    private VerificationCodeResolverTab(WebDriver driver, VerificationCodeReceiverPage verificationCodeReceiverPage, String url) {
        this.driver = driver;
        this.verificationCodeReceiverPage = verificationCodeReceiverPage;
        this.url = url;
    }

    @Override
    public String getVerificationCode(String username) {
        String originalWindow = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(url);
        var code = verificationCodeReceiverPage.getVerificationCode(username);
        driver.switchTo().window(originalWindow);
        return code;
    }

    public static class Builder {
        private WebDriver driver;
        private VerificationCodeReceiverPage verificationCodeReceiverPage;
        private String url;

        public Builder setDriver(WebDriver driver) {
            this.driver = driver;
            return this;
        }

        public Builder setVerificationCodeReceiverPage(VerificationCodeReceiverPage verificationCodeReceiverPage) {
            this.verificationCodeReceiverPage = verificationCodeReceiverPage;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public VerificationCodeResolverTab createVerificationCodeResolverTab() {
            return new VerificationCodeResolverTab(driver, verificationCodeReceiverPage, url);
        }

        public static Builder start() {
            return new Builder();
        }
    }
}