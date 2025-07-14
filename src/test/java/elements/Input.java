package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class Input {
    private final String label;
    private final SelenideElement element;

    private static final String LOGIN_LOCATOR = "//*[@name='%s']";
    private static final String REGISTRATION_LOCATOR = "//*[@id='%s']";
    private static final String ENTRY_DESCRIPTION_LOCATOR = "//*[@id='%s']";

    public Input(String label, String locatorType) {
        this.label = label;
        this.element = resolveElement(locatorType);
    }

    private SelenideElement resolveElement(String locatorType) {
        String locator;
        switch (locatorType.toLowerCase()) {
            case "login":
                locator = String.format(LOGIN_LOCATOR, label);
                break;
            case "registration":
                locator = String.format(REGISTRATION_LOCATOR, label);
                break;
            case "entry":
                locator = String.format(ENTRY_DESCRIPTION_LOCATOR, label);
                break;
            default:
                throw new IllegalArgumentException("Unknown locator type: " + locatorType);
        }
        return $x(locator);
    }

    public Input type(String text) {
        log.info("Typing text '{}' into input field '{}'", text, label);
        element.shouldBe(Condition.visible).setValue(text);
        return this;
    }

    public Input clearAndType(String text) {
        log.info("Clearing and typing text '{}' into input field '{}'", text, label);
        element.shouldBe(Condition.visible);
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.setValue(text);
        return this;
    }

    public Input verifyValue(String expectedValue) {
        log.info("Verifying input field '{}' value. Expected: '{}'", label, expectedValue);
        element.shouldHave(Condition.value(expectedValue));
        return this;
    }

    public Input verifyVisible() {
        log.info("Verifying input field '{}' visibility", label);
        element.shouldBe(Condition.visible);
        return this;
    }
}