package steps;

import io.qameta.allure.Step;
import pages.RegistrationPage;

public class RegistrationSteps extends BaseSteps {

    RegistrationPage registrationPage;

    public RegistrationSteps() {
        registrationPage = new RegistrationPage();
    }

    @Step("Регистрация нового пользователя с email '{email}'")
    public void completeRegistration(String url, String email, String password,
                                     String passwordConfirmation, String passwordHint) {
        logStep("Регистрация нового пользователя");
        registrationPage
                .openPage(url)
                .fillRegistrationForm(email, password, passwordConfirmation, passwordHint)
                .verifySuccessMessage();
    }

    @Step("Проверка валидации пароля")
    public RegistrationSteps verifyPasswordValidation(String url, String password, String expectedMessage) {
        logStep("Проверка валидации пароля");
        registrationPage
                .openPage(url)
                .enterPassword(password)
                .verifyPasswordStrength(expectedMessage);
        return this;
    }

    @Step("Проверка подтверждения пароля")
    public RegistrationSteps verifyPasswordConfirmation(String url, String password,
                                                        String passwordConfirmation) {
        logStep("Проверка подтверждения пароля");
        registrationPage.openPage(url)
                .enterPassword(password)
                .confirmPassword(passwordConfirmation);
        return this;
    }
}