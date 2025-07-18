package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.LoginSteps;
import steps.RegistrationSteps;
import utils.PropertyReader;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {

    LoginSteps loginSteps;
    RegistrationSteps registrationSteps;

    protected static final String REGISTRATION_URL = System.getenv().getOrDefault("registration.url", PropertyReader.getProperty("registration.url"));
    protected static final String TEST_USER_EMAIL = System.getenv().getOrDefault("email", PropertyReader.getProperty("email"));
    protected static final String TEST_USER_PASSWORD = System.getenv().getOrDefault("password", PropertyReader.getProperty("password"));
    protected static final String TEST_INVALID_PASSWORD = "invalid_password";
    protected static final String REGISTRATION_EMAIL = System.getenv().getOrDefault("registration.email", PropertyReader.getProperty("registration.email"));
    protected static final String REGISTRATION_PASSWORD = System.getenv().getOrDefault("registration.password", PropertyReader.getProperty("registration.password"));
    protected static final String REGISTRATION_PASSWORD_CONFIRMATION = System.getenv().getOrDefault("registration.password.confirm", PropertyReader.getProperty("registration.password.confirm"));
    protected static final String REGISTRATION_PASSWORD_HINT = System.getenv().getOrDefault("registration.password.hint", PropertyReader.getProperty("registration.password.hint"));
    protected static final String SHORT_PASSWORD = System.getenv().getOrDefault("password.validation.short", PropertyReader.getProperty("password.validation.short"));
    protected static final String WEAK_PASSWORD = System.getenv().getOrDefault("password.validation.weak", PropertyReader.getProperty("password.validation.weak"));
    protected static final String STRONG_PASSWORD = System.getenv().getOrDefault("password.validation.strong", PropertyReader.getProperty("password.validation.strong"));
    protected static final String MISMATCHED_PASSWORD = System.getenv().getOrDefault("password.validation.mismatch", PropertyReader.getProperty("password.validation.mismatch"));

    @BeforeMethod
    public void setUp() {
        configureBrowser();
        initAllureReporting();
        initSteps();
    }

    private void configureBrowser() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://my.monkkee.com";
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
        Configuration.screenshots = true;
        Configuration.savePageSource = false;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
    }

    private void initAllureReporting() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true));
    }

    private void initSteps() {
        loginSteps = new LoginSteps();
        registrationSteps = new RegistrationSteps();
    }

    @AfterMethod
    public void tearDown() {
        if (getWebDriver() != null) {
            getWebDriver().quit();
        }
    }
}