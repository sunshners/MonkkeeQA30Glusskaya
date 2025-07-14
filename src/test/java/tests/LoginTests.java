package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListener;

@Listeners(TestListener.class)
public class LoginTests extends BaseTest {

    @Test(description = "Успешная авторизация с валидными учетными данными")
    public void successfulLoginTest() {
        loginSteps
                .successfulLogin(TEST_USER_EMAIL, TEST_USER_PASSWORD)
                .verifyEntriesPageDisplayed();
    }

    @Test(description = "Неудачная авторизация с неверным паролем")
    public void failedLoginWithInvalidPasswordTest() {
        loginSteps
                .failedLogin(TEST_USER_EMAIL, TEST_INVALID_PASSWORD, "Invalid credentials")
                .verifyLoginErrorDisplayed("Invalid credentials");
    }

    @Test(description = "Неудачная авторизация с пустым паролем")
    public void failedLoginWithEmptyPasswordTest() {
        loginSteps
                .failedLogin(TEST_USER_EMAIL, "", "Password is required")
                .verifyLoginErrorDisplayed("Password is required");
    }
}