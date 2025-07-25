package pages;



import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class RegistrationPage extends BasePage {

    public RegistrationPage(){
    }

    public RegistrationPage openPage(String url) {
        log.info("Opening registration page at URL: {}", url);
        open(url);
        return this;
    }

    public RegistrationPage fillRegistrationForm(String email, String password,
                                                 String passwordConfirmation, String passwordHint) {
        log.info("Filling registration form for email: {}", email);
        $("[name='registration[email]']").setValue(email);
        $x("//*[@id=\"registration_password_clear\"]").setValue(password);
        $x("//*[@id=\"registration_password_clear_confirmation\"]").setValue(passwordConfirmation);
        $x("//*[@id=\"registration_password_hint\"]").setValue(passwordHint);
        $x("//*[@id=\"registration_terms_of_use\"]").click();
        $x("//*[@id=\"registration_lost_password_warning_registered\"]").click();
        $x("//*[@id=\"register\"]").click();
        $x("//*[contains(text(), 'User registered')]").shouldHave(text("User registered"));
        return this;
    }

    public void verifySuccessMessage() {
        log.info("Verifying registration success message");
        $x("//*[contains(text(), 'User registered')]").shouldHave(text("User registered"));
    }

    public RegistrationPage enterPassword(String password) {
        $x("//*[@id=\"registration_password_clear\"]").setValue(password);
        return this;
    }

    public RegistrationPage confirmPassword(String passwordConfirm) {
        $x("//*[@id=\"registration_password_clear_confirmation\"]").setValue(passwordConfirm);
        return this;
    }

    public void verifyPasswordStrength(String expectedMessage) {
        log.info("Verifying password strength message: {}", expectedMessage);
        $x("//*[@id=\"registration_password_clear-strength-indicator\"]").shouldHave(text(expectedMessage));
    }
}