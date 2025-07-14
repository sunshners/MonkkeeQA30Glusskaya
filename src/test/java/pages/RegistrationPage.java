package pages;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class RegistrationPage extends BasePage {

    private final SelenideElement emailInput = $("registration[email]");
    private final SelenideElement passwordInput = $("#registration_password_clear");
    private final SelenideElement confirmPasswordInput = $("#registration_password_clear_confirmation");
    private final SelenideElement passwordHintInput = $("#registration_password_hint");
    private final SelenideElement termsCheckbox = $("#registration_terms_of_use");
    private final SelenideElement warningCheckbox = $("#registration_lost_password_warning_registered");
    private final SelenideElement submitButton = $x("//button[@type='submit']");
    private final SelenideElement successMessage = $x("//*[contains(text(), 'User registered')]");
    private final SelenideElement passwordStrengthIndicator = $("#registration_password-strength-indicator");
    private final SelenideElement passwordMismatchError = $x("//*[contains(text(), 'Password confirmation doesn’t match')]");


    public RegistrationPage open(String url) {
        Selenide.open(url);
        return this;
    }


    public HomePage registerNewUser(String email, String password, String confirmation, String hint) {
        enterEmail(email)
            .enterPassword(password)
            .confirmPassword(confirmation)
            .enterPasswordHint(hint)
            .acceptTerms()
            .acceptWarning()
            .submitRegistration();
        
        return new HomePage();
    }
    
    public RegistrationPage enterEmail(String email) {
        clearAndType(emailInput, email);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        clearAndType(passwordInput, password);
        return this;
    }

    public RegistrationPage confirmPassword(String password) {
        clearAndType(confirmPasswordInput, password);
        return this;
    }

    public RegistrationPage enterPasswordHint(String hint) {
        clearAndType(passwordHintInput, hint);
        return this;
    }

    public RegistrationPage acceptTerms() {
        clickElement(termsCheckbox);
        return this;
    }

    public RegistrationPage acceptWarning() {
        clickElement(warningCheckbox);
        return this;
    }

    public RegistrationPage submitRegistration() {
        clickElement(submitButton);
        return this;
    }
    
    public RegistrationPage verifySuccessMessage() {
        verifyElementText(successMessage, "User registered");
        return this;
    }

    public RegistrationPage verifyPasswordStrength(String expectedStrength) {
        verifyElementText(passwordStrengthIndicator, expectedStrength);
        return this;
    }

    public RegistrationPage verifyPasswordMismatchError() {
        waitForElementVisible(passwordMismatchError);
        return this;
    }
}