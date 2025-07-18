package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class EntriesPage extends BasePage {

    private static final SelenideElement CREATE_ENTRY_BUTTON = $("#create-entry");
    private static final SelenideElement CREATE_FAIL_BUTTOM = $(".alert-danger");

    public EntriesPage isCreateButtonVisible() {
        log.info("Checking if create entry button is visible");
        CREATE_ENTRY_BUTTON.shouldBe(Condition.visible);
        return this;
    }

    public EntriesPage isFailLoginVisible() {
        log.info("Checking if login failure message is visible");
        CREATE_FAIL_BUTTOM.shouldBe(Condition.visible);
        return this;
    }
}