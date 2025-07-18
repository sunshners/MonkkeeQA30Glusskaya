package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    public LoginPage open() {
        Selenide.open("/#/login");
        return this;
    }

    public LoginPage login(String email, String password) {
        $x("//*[contains(text(),'Login')]").click();
        $("#login").setValue(email);
        $("#password").setValue(password);
        $("[type=submit]").click();
        return this;
    }
}