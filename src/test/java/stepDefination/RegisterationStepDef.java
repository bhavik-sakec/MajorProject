package stepDefination;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import userDefinedFiles.DriverSetup;
import userDefinedFiles.Locators;
import userDefinedFiles.checkClickMethod;
import java.time.Duration;

public class RegisterationStepDef {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;
    static String browser = System.getProperty("browser", "chrome");

    @Given("user is on the registration page")
    public void user_is_on_registration_page() {
        driver = DriverSetup.initializeDriver(browser);
        js = (JavascriptExecutor) driver;
        driver.get("https://inkarto.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @When("user clicks on account and registration buttons")
    public void user_clicks_account_and_registration() throws InterruptedException {
        checkClickMethod.safeClick(driver, Locators.accountButton);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement newCustomerScroll = driver.findElement(Locators.newCustomerScoll);
        js.executeScript("arguments[0].scrollIntoView(true)", newCustomerScroll);
        Thread.sleep(500);
        checkClickMethod.safeClick(driver, Locators.registeration);
    }

    @And("user enters registration details {string}, {string}, {string}, {string}")
    public void user_enters_registration_details(String firstName, String lastName, String email, String password) {
        WebElement registerScroll = driver.findElement(Locators.registerScroll);
        js.executeScript("arguments[0].scrollIntoView(true)", registerScroll);
        driver.findElement(Locators.firstName).sendKeys(firstName);
        driver.findElement(Locators.lastName).sendKeys(lastName);
        driver.findElement(Locators.email).sendKeys(email);
        driver.findElement(Locators.password).sendKeys(password);
    }

    @And("user clicks register button on account page")
    public void user_clicks_register_button_on_account_page() {
        checkClickMethod.safeClick(driver, Locators.registerButton_account);
    }

    @Then("registration should be {string}")
    public void registration_should_be(String expectedResult) throws InterruptedException {
        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        String actualResult;
        try {
            boolean accountExists = driver.findElement(Locators.accountExist).isDisplayed();
            if (accountExists) {
                actualResult = "Failed";
            } else {
                actualResult = "Success";
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            if (currentUrl.equals("https://inkarto.com/")) {
                actualResult = "Success";
            } else {
                actualResult = "Failed";
            }
        }
        assert actualResult.equalsIgnoreCase(expectedResult);
        driver.quit();
    }
} 