package pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage extends BasePage {

    public RegistrationPage open(String url) {
        Selenide.open(url);
        return this;
    }

    public RegistrationPage fillRegistrationForm(String email, String password,
                                                 String passwordConfirmation, String hint) {
        $("#email").setValue(email);
        $("#password").setValue(password);
        $("#confirmPassword").setValue(passwordConfirmation);
        $("#hint").setValue(hint);
        return this;
    }

    public RegistrationPage submitRegistration() {
        $("#submit-btn").click();
        return this;
    }

    public void verifySuccessMessage() {
        $(".success-message").shouldHave(text("User registered"));
    }

    public RegistrationPage enterPassword(String password) {
        $("#password").setValue(password);
        return this;
    }

    public RegistrationPage confirmPassword(String confirmation) {
        $("#confirmPassword").setValue(confirmation);
        return this;
    }

    public void verifyPasswordStrength(String expectedMessage) {
        $(".password-strength").shouldHave(text(expectedMessage));
    }

    public void verifyPasswordMismatchError() {
        $(".error-message").shouldHave(text("Password confirmation doesn't match"));
    }
}