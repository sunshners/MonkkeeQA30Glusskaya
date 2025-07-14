package utils;

import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.codeborne.selenide.WebDriverRunner;

import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting test: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logExecutionTime(result, "PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logExecutionTime(result, "FAILED");
        takeScreenshot();
        attachLogs(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test skipped: {}", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Test suite '{}' started", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test suite '{}' finished. Passed: {}, Failed: {}, Skipped: {}", 
                context.getName(),
                context.getPassedTests().size(),
                context.getFailedTests().size(),
                context.getSkippedTests().size());
    }

    private void logExecutionTime(ITestResult result, String status) {
        long duration = TimeUnit.MILLISECONDS.toSeconds(result.getEndMillis() - result.getStartMillis());
        log.info("Test {} {}. Execution time: {}s", result.getName(), status, duration);
    }

    @Attachment(value = "Screenshot on failure", type = "image/png")
    private byte[] takeScreenshot() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        return driver != null ? ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES) : new byte[0];
    }

    @Attachment(value = "Test logs", type = "text/plain")
    private String attachLogs(ITestResult result) {
        return "Test: " + result.getName() + "\n" +
               "Status: " + (result.isSuccess() ? "PASSED" : "FAILED") + "\n" +
               "Exception: " + (result.getThrowable() != null ? result.getThrowable().toString() : "None");
    }
}