package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            log.warn("Retrying test {} - Attempt {}/{}", 
                     result.getName(), retryCount, MAX_RETRY_COUNT);
            result.setStatus(ITestResult.FAILURE);
            return true;
        }
        return false;
    }

    public static void resetRetryCount(ITestResult result) {
        Retry retryAnalyzer = (Retry) result.getMethod().getRetryAnalyzer();
        if (retryAnalyzer != null) {
            retryAnalyzer.retryCount = 0;
        }
    }
}