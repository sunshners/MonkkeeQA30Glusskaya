package steps;

import io.qameta.allure.Step;
import pages.EntriesPage;
import pages.LoginPage;

public class LoginSteps extends BaseSteps {

    private final LoginPage loginPage;
    private final EntriesPage entriesPage;

    public LoginSteps() {
        loginPage = new LoginPage();
        entriesPage = new EntriesPage();
    }

    @Step("Выполнить вход с верными логином и паролем")
    public LoginSteps successfulLogin(String username, String password) {
        loginPage
                .open()
                .login(username, password);
        return this;
    }

    @Step("Выполнить вход с неверным паролем")
    public LoginSteps failedLogin(String username, String invalidPassword){
        loginPage
                .open()
                .login(username, invalidPassword);
        return this;
    }

    @Step("Проверить отображение сообщения об ошибке входа")
    public LoginSteps checkFailedLogin(){
        entriesPage
                .isFailLoginVisible();
        return this;
    }

    @Step("Проверить отображение страницы записей")
    public LoginSteps checkEntriesPageOpened() {
        entriesPage
                .isCreateButtonVisible();
        return this;
    }
}