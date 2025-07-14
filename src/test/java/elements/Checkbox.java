package elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Checkbox {
    private final SelenideElement element;

    public Checkbox(SelenideElement element) {
        this.element = element;
    }

    public void check() {
        if (!isChecked()) {
            log.info("Checking checkbox");
            element.shouldBe(Condition.visible).click();
        }
    }

    public void uncheck() {
        if (isChecked()) {
            log.info("Unchecking checkbox");
            element.shouldBe(Condition.visible).click();
        }
    }

    public boolean isChecked() {
        return element.isSelected();
    }

    public void set(boolean shouldBeChecked) {
        if (shouldBeChecked) {
            check();
        } else {
            uncheck();
        }
    }
}