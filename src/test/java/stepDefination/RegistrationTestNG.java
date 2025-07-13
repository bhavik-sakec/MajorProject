package stepDefination;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObject.registeration;

public class RegistrationTestNG {
    WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) {
        System.setProperty("browser", browser); 
        userDefinedFiles.DriverSetup.setBrowserType(browser);
        driver = userDefinedFiles.DriverSetup.getDriver();
    }

    @Test
    public void testRegistration() throws Exception {
        // Run the registration main method
        registeration.main(new String[]{});
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
} 