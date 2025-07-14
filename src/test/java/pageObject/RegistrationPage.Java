package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='site-header-account__link site-header-account__link--icon']")
    public WebElement accountButton;

    @FindBy(xpath = "//a[contains(text(),'New Customer')]")
    public WebElement newCustomerScroll;

    @FindBy(xpath = "//a[text()='Register']")
    public WebElement registrationButton;

    @FindBy(xpath = "//input[@id='RegisterForm-FirstName']")
    public WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='RegisterForm-LastName']")
    public WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='RegisterForm-email']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@id='RegisterForm-password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement registerSubmit;

    @FindBy(xpath = "//div[contains(text(),'already have an account')]")
    public WebElement accountExistsMessage;

    @FindBy(xpath = "//form[@id='create_customer']")
    public WebElement registerScroll;
}