package familycore.e2e.page.interfaces;

import org.openqa.selenium.WebDriver;

public interface VerificationCodeReceiverPage {
    String getVerificationCode(String email);
}
