package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage {
    private final SelenideElement welcomeMessage = $(".welcome-message");
    private final SelenideElement entriesLink = $("#entries-link");
    
    public HomePage verifyLoginSuccess() {
        waitForElementVisible(welcomeMessage);
        return this;
    }
    
    public EntriesPage navigateToEntries() {
        clickElement(entriesLink);
        return new EntriesPage();
    }

    public void verifySuccessfulRegistration() {
    }

    public void verifyEntriesPageDisplayed() {
    }

    public void verifyErrorMessage(String expectedError) {
    }
}