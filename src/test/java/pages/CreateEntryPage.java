package pages;

import com.codeborne.selenide.SelenideElement;

;import static com.codeborne.selenide.Selenide.$;

public class CreateEntryPage extends BasePage {
    private final SelenideElement titleInput = $("#entry-title");
    private final SelenideElement contentInput = $("#entry-content");
    private final SelenideElement saveButton = $("#save-entry");
    private final SelenideElement successMessage = $(".alert-success");

    public EntriesPage createNewEntry(String title, String content) {
        enterTitle(title)
            .enterContent(content)
            .clickSave();
        
        return new EntriesPage();
    }

    public CreateEntryPage enterTitle(String title) {
        clearAndType(titleInput, title);
        return this;
    }

    public CreateEntryPage enterContent(String content) {
        clearAndType(contentInput, content);
        return this;
    }

    public EntriesPage clickSave() {
        clickElement(saveButton);
        return new EntriesPage();
    }

    public CreateEntryPage verifySuccessMessage(String message) {
        verifyElementText(successMessage, message);
        return this;
    }
}