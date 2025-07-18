package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BaseSteps {

    @Step("{0}")
    protected void logStep(String message) {
        log.info("Выполняется шаг: {}", message);
    }
}