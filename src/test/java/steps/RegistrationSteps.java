package steps;

import io.qameta.allure.Step;
import pages.EntriesPage;
import pages.HomePage;
import pages.RegistrationPage;

public class RegistrationSteps extends BaseSteps {

    private final RegistrationPage registrationPage;

    public RegistrationSteps() {
        registrationPage = new RegistrationPage();
    }

    @Step("Регистрация нового пользователя с email '{email}'")
    public EntriesPage registerNewUser(String url, String email, String password,
                                            String passwordConfirmation, String passwordHint) {
        logStep("Регистрация нового пользователя");
        return registrationPage
                .open(url)
                .registerNewUser(email, password, passwordConfirmation, passwordHint)
                .verifySuccessMessage();
    }

    @Step("Проверка валидации пароля")
    public RegistrationSteps verifyPasswordValidation(String url, String password, String expectedMessage) {
        logStep("Проверка валидации пароля");
        registrationPage
                .open(url)
                .enterPassword(password)
                .verifyPasswordStrength(expectedMessage);
        return this;
    }

    @Step("Проверка подтверждения пароля")
    public RegistrationSteps verifyPasswordConfirmation(String url, String password, 
                                                      String passwordConfirmation) {
        logStep("Проверка подтверждения пароля");
        registrationPage
                .open(url)
                .enterPassword(password)
                .confirmPassword(passwordConfirmation)
                .verifyPasswordMismatchError();
        return this;
    }

    @Step("Проверка успешной регистрации")
    public RegistrationSteps verifySuccessfulRegistration() {
        logStep("Проверка сообщения об успешной регистрации");
        registrationPage.verifySuccessMessage();
        return this;
    }
}