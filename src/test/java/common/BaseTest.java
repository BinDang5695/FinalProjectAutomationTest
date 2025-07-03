package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import listeners.TestListener;
import driver.DriverManager;
import helpers.PropertiesHelper;
import io.qameta.allure.Step;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeSuite
    public void setupEnvironment() {
        PropertiesHelper.loadAllFiles();
    }

    @BeforeMethod
    @Parameters({"browser"})
    @Step("Run with browser {0}")
    public void createDriver(@Optional("chrome") String browser, Method method) {

    	WebDriver driver;

        if (PropertiesHelper.getValue("BROWSER").isEmpty() || PropertiesHelper.getValue("BROWSER").isBlank()) {
            // dùng giá trị từ tham số
        } else {
            browser = PropertiesHelper.getValue("BROWSER");
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("autofill.profile_enabled", false);
                options.setExperimentalOption("prefs", prefs);

                options.addArguments("--disable-extensions");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-notifications");
                options.addArguments("--remote-allow-origins=*");
                options.setAcceptInsecureCerts(true);

                driver = new ChromeDriver(options);
                System.out.println("Khởi chạy trình duyệt Chrome...");
                break;
            case "edge":
                driver = new EdgeDriver();
                System.out.println("Khởi chạy trình duyệt Edge...");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                System.out.println("Khởi chạy trình duyệt Firefox...");
                break;
            case "safari":
                driver = new SafariDriver();
                System.out.println("Khởi chạy trình duyệt Safari...");
                break;
            default:
                driver = new ChromeDriver();
                System.out.println("Khởi chạy trình duyệt Chrome mặc định...");
                break;
        }

        DriverManager.setDriver(driver);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }
    
    @AfterMethod
    @Step("Closed browser")
    public void closeDriver() {
        // ❌ KHÔNG gọi stopRecord() ở đây nữa, đã làm trong TestListener rồi
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }
}
