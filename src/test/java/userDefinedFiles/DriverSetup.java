package userDefinedFiles;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverSetup {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static String browserType = "chrome"; // default, can be overridden
    
    public static void setBrowserType(String browser) {
        browserType = browser;
    }
    
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver(browserType); // use the set browser type
        }
        return driver;
    }
    
    public static WebDriver initializeDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
            	driver = new FirefoxDriver();
				driver.manage().window().maximize();
				break; 
            case "edge":
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException("Browser " + browserName + " is not supported");
        }
        
        // Configure timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        
        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        return driver;
    }
    
    public static WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        }
        return wait;
    }
    
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }
}