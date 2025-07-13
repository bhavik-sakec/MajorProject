package userDefinedFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class checkClickMethod {

    // Check if the element is displayed and enabled (clickable), without clicking
    public static boolean isElementClickable(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed() && element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    // Wait for element to be clickable and then click it safely
    public static boolean safeClick(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Just wait and return true if element becomes clickable (no click)
    public static boolean waitForClickability(WebDriver driver, By locator, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
