package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class EntriesPage extends BasePage {

    private final SelenideElement createEntryButton = $("#create-entry");
    private final SelenideElement errorMessage = $(".alert-danger");
    private final SelenideElement successMessage = $(".alert-success");
    private final SelenideElement entriesList = $(".entries-list");

    public EntriesPage verifyCreateButtonVisible() {
        waitForElementVisible(createEntryButton);
        return this;
    }

    public EntriesPage verifyErrorMessage(String expectedMessage) {
        verifyElementText(errorMessage, expectedMessage);
        return this;
    }

    public EntriesPage verifySuccessMessage(String expectedMessage) {
        verifyElementText(successMessage, expectedMessage);
        return this;
    }

    public CreateEntryPage clickCreateEntryButton() {
        clickElement(createEntryButton);
        return new CreateEntryPage();
    }

    public EntriesPage verifyEntriesExist() {
        waitForElementVisible(entriesList);
        return this;
    }
}