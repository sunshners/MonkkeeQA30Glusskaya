package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class EntriesPage extends BasePage {

    private static final SelenideElement CREATE_ENTRY_BUTTON = $("#create-entry");
    private static final SelenideElement CREATE_FAIL_BUTTOM = $(".alert-danger");

    public EntriesPage isCreateButtonVisible() {
        CREATE_ENTRY_BUTTON.shouldBe(Condition.visible);
        return this;
    }

    public EntriesPage isFailLoginVisible() {
        CREATE_FAIL_BUTTOM.shouldBe(Condition.visible);
        return this;
    }
}