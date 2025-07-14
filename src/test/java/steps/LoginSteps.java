package steps;

import io.qameta.allure.Step;
import pages.EntriesPage;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps extends BaseSteps {

    private final LoginPage loginPage;
    private final EntriesPage entriesPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
        this.entriesPage = new EntriesPage();
    }

    @Step("Выполнить вход с логином '{username}' и паролем '{password}'")
    public HomePage successfulLogin(String username, String password) {
        logStep("Выполнение входа в систему");
        return loginPage
                .open()
                .verifyPageOpened()
                .loginAs(username, password)
                .verifyLoginSuccess();
    }

    @Step("Попытка входа с неверными данными")
    public LoginSteps failedLogin(String username, String password, String expectedError) {
        logStep("Попытка входа с неверными данными");
        loginPage
                .open()
                .verifyPageOpened()
                .loginAs(username, password)
                .verifyErrorMessage(expectedError);
        return this;
    }

    @Step("Проверить отображение страницы записей")
    public LoginSteps verifyEntriesPageDisplayed() {
        logStep("Проверка отображения страницы записей");
        entriesPage
                .verifyCreateButtonVisible()
                .verifyEntriesExist();
        return this;
    }

    @Step("Проверить сообщение об ошибке входа")
    public LoginSteps verifyLoginErrorDisplayed(String expectedError) {
        logStep("Проверка сообщения об ошибке входа");
        entriesPage.verifyErrorMessage(expectedError);
        return this;
    }
}