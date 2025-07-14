package elements;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Button {
    private final SelenideElement element;

    public Button(SelenideElement element) {
        this.element = element;
    }

    public void click() {
        log.info("Clicking on button with text: '{}'", element.getText());
        element.shouldBe(Condition.visible, Condition.enabled).click();
    }

    public void verifyText(String expectedText) {
        log.info("Verifying button text. Expected: '{}'", expectedText);
        element.shouldHave(Condition.text(expectedText));
    }

    public void verifyVisible() {
        log.info("Verifying button visibility");
        element.shouldBe(Condition.visible);
    }
}