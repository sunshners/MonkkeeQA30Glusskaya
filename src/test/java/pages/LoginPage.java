package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class LoginPage extends BasePage {

    public LoginPage open() {
        log.info("Opening login page");
        Selenide.open("/#/login");
        return this;
    }

    public LoginPage login(String email, String password) {
        log.info("Attempting to login with email: {}", email);
        $x("//*[contains(text(),'Login')]").click();
        $("#login").setValue(email);
        $("#password").setValue(password);
        $("[type=submit]").click();
        return this;
    }
}