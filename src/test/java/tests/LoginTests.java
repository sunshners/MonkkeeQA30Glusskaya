package tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListener;

@Listeners(TestListener.class)
public class LoginTests extends BaseTest {


    @Test(description = "Успешная авторизация")
    public void successfulLoginTest() {
        loginSteps
                .successfulLogin(TEST_USER_EMAIL, TEST_USER_PASSWORD)
                .checkEntriesPageOpened();
    }

    @Test(description = "Неудачная авторизация с неверным паролем")
    public void failedLoginTest() {
        loginSteps
                .failedLogin(TEST_USER_EMAIL, TEST_INVALID_PASSWORD)
                .checkFailedLogin();
    }
}