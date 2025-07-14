package pages;


import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;

@Log4j2
public class BasePage {

    protected void clearAndType(SelenideElement element, String text) {
        log.info("Ввод текста '{}' в элемент {}", text, element.getSearchCriteria());
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.setValue(text);
    }

    protected void waitForElementVisible(SelenideElement element) {
        log.info("Ожидание видимости элемента {}", element.getSearchCriteria());
        element.shouldBe(visible);
    }

    protected void clickElement(SelenideElement element) {
        log.info("Клик по элементу {}", element.getSearchCriteria());
        waitForElementVisible(element);
        element.click();
    }

    protected void verifyElementText(SelenideElement element, String expectedText) {
        log.info("Проверка текста элемента {}. Ожидается: {}", element.getSearchCriteria(), expectedText);
        element.shouldHave(text(expectedText));
    }
}