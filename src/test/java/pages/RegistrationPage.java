package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage extends BasePage {

    public RegistrationPage(){
    }

    public RegistrationPage open(String url) {
        open(url);
        return this;
    }

    public RegistrationPage fillRegistrationForm(String email, String password,
                                                 String passwordConfirmation, String passwordhint) {
        $("[name='registration[email]']").setValue(email);
        $x("//*[@id=\"registration_password_clear\"]").setValue(password);
        $x("//*[@id=\"registration_password_clear_confirmation\"]").setValue(passwordConfirmation);
        $x("//*[@id=\"registration_password_hint\"]").setValue(passwordhint);
        $x("//*[@id=\"registration_terms_of_use\"]").click();
        $x("//*[@id=\"registration_lost_password_warning_registered\"]").click();
        $x("//*[@id=\"new_registration\"]/div[8]/div").click();
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