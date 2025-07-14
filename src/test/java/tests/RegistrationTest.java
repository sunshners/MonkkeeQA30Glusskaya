package tests;

import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test(description = "Успешная регистрация нового пользователя")
    public void successRegistrationTest() {
        registrationSteps
                .completeRegistration(
                    REGISTRATION_URL, 
                    REGISTRATION_EMAIL, 
                    REGISTRATION_PASSWORD,
                    REGISTRATION_PASSWORD_CONFIRMATION, 
                    REGISTRATION_PASSWORD_HINT);
    }

    @Test(description = "Проверка валидации короткого пароля (<8 символов)")
    public void registrationShortPasswordValidationTest() {
        registrationSteps
                .verifyPasswordValidation(
                    REGISTRATION_URL, 
                    SHORT_PASSWORD, 
                    "Password is too short. Min. 8 characters");
    }

    @Test(description = "Проверка валидации слабого пароля")
    public void registrationWeakPasswordValidationTest() {
        registrationSteps
                .verifyPasswordValidation(
                    REGISTRATION_URL, 
                    WEAK_PASSWORD, 
                    "Password strength: Weak");
    }

    @Test(description = "Проверка валидации сложного пароля")
    public void registrationStrongPasswordValidationTest() {
        registrationSteps
                .verifyPasswordValidation(
                    REGISTRATION_URL, 
                    STRONG_PASSWORD, 
                    "Password strength: Strong");
    }

    @Test(description = "Проверка несовпадения пароля и подтверждения")
    public void registrationPasswordConfirmationValidationTest() {
        registrationSteps
                .verifyPasswordConfirmation(
                    REGISTRATION_URL, 
                    REGISTRATION_PASSWORD, 
                    MISMATCHED_PASSWORD);
    }
}