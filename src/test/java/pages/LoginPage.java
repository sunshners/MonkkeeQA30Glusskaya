package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    private final SelenideElement emailInput = $("#login");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement submitButton = $("[type=submit]");
    private final SelenideElement loginLink = $x("//*[contains(text(),'Login')]");
    private final SelenideElement errorMessage = $(".alert-danger");

    public LoginPage open() {
        Selenide.open("/#/login");
        return this;
    }

    public HomePage loginAs(String email, String password) {
        clickElement(loginLink);
        clearAndType(emailInput, email);
        clearAndType(passwordInput, password);
        clickElement(submitButton);
        return new HomePage();
    }

    public LoginPage verifyPageOpened() {
        waitForElementVisible(emailInput);
        return this;
    }

    public LoginPage verifyErrorMessage(String expectedMessage) {
        verifyElementText(errorMessage, expectedMessage);
        return this;
    }
}