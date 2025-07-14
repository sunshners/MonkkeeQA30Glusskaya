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

    protected LoginSteps loginSteps;
    protected RegistrationSteps registrationSteps;

    protected static final String LOGIN_URL = PropertyReader.getProperty("loginUrl");
    protected static final String REGISTRATION_URL = PropertyReader.getProperty("registrationUrl");
    protected static final String TEST_USER_EMAIL = PropertyReader.getProperty("login");
    protected static final String TEST_USER_PASSWORD = PropertyReader.getProperty("password");
    protected static final String TEST_INVALID_PASSWORD = "invalid_password";
    protected static final String REGISTRATION_EMAIL = PropertyReader.getProperty("registrationEmail");
    protected static final String REGISTRATION_PASSWORD = PropertyReader.getProperty("registrationPassword");
    protected static final String REGISTRATION_PASSWORD_CONFIRMATION = PropertyReader.getProperty("registrationPasswordConfirmation");
    protected static final String REGISTRATION_PASSWORD_HINT = PropertyReader.getProperty("registrationPasswordHint");
    protected static final String SHORT_PASSWORD = PropertyReader.getProperty("registrationShortPassword");
    protected static final String WEAK_PASSWORD = PropertyReader.getProperty("registrationBadRedPassword");
    protected static final String STRONG_PASSWORD = PropertyReader.getProperty("registrationStrongGreenPassword");
    protected static final String MISMATCHED_PASSWORD = PropertyReader.getProperty("registrationPasswordConfirmationNotEqualPassword");

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