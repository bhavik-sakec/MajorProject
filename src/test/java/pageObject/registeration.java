package pageObject;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import userDefinedFiles.DriverSetup;
import userDefinedFiles.ExcelUtility;
import userDefinedFiles.Locators;
import userDefinedFiles.checkClickMethod;

public class registeration {
    static WebDriver driver;
    static WebDriverWait wait;
    static JavascriptExecutor js;
    static ExcelUtility excel;
    static final String sheetName = "Registration";
    static final String baseUrl = "https://inkarto.com/";
    
    public static void main(String[] args) throws IOException, InterruptedException {
        excel = new ExcelUtility("TestData/data.xlsx");
        int rowCount = excel.getRowCount(sheetName);
        
        try {
            // Initialize WebDriver once before the loop
            driver = DriverSetup.getDriver();
            js = (JavascriptExecutor) driver;
            
            // Iterate through each row of test data
            for(int row = 1; row <= rowCount; row++) {
                driver.get(baseUrl);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                // Account Button Click
                if (checkClickMethod.safeClick(driver, Locators.accountButton)) {
                    System.out.println("[Success] Account Button Clicked");
                } else {
                    System.out.println("[Failed] Account Button Not Clicked");
                }

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                // Scroll to New Customer Section
                WebElement newCustomerScroll = driver.findElement(Locators.newCustomerScoll);
                js.executeScript("arguments[0].scrollIntoView(true)", newCustomerScroll);
                Thread.sleep(500);

                // Click on Registration Button
                if (checkClickMethod.safeClick(driver, Locators.registeration)) {
                    System.out.println("[Success] Registration Button Clicked");
                } else {
                    System.out.println("[Failed] Registration Button Not Clickable");
                }

                // Scroll to Registration Form
                WebElement registerScroll = driver.findElement(Locators.registerScroll);
                js.executeScript("arguments[0].scrollIntoView(true)", registerScroll);
                
                // Read test data from Excel
                String firstName = excel.getCellData(sheetName, row, 0);
                String lastName = excel.getCellData(sheetName, row, 1);
                String email = excel.getCellData(sheetName, row, 2);
                String password = excel.getCellData(sheetName, row, 3);
                String expectedResult = excel.getCellData(sheetName, row, 4);
                
                // Fill Registration Details
                System.out.println("\nEntering details for registration...\n");
                driver.findElement(Locators.firstName).sendKeys(firstName);
                driver.findElement(Locators.lastName).sendKeys(lastName);
                driver.findElement(Locators.email).sendKeys(email);
                driver.findElement(Locators.password).sendKeys(password);

                // Click Register Button on Account Page
                if (checkClickMethod.safeClick(driver, Locators.registerButton_account)) {
                    System.out.println("[Success] Register Button on Account Page Clicked");
                } else {
                    System.out.println("[Failed] Register Button on Account Page Not Clickable");
                }
                
                // Wait for page to load and check registration result
                Thread.sleep(2000);
                String currentUrl = driver.getCurrentUrl();
                String actualResult;
                String reason = "";
                
                try {
                    boolean accountExists = driver.findElement(Locators.accountExist).isDisplayed();
                    if (accountExists) {
                        actualResult = "Failed";
                        reason = "Account Exist";
                    } else {
                        actualResult = "Success";
                    }
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    // Only consider it a success if we're redirected to home page
                    if (currentUrl.equals(baseUrl)) {
                        actualResult = "Success";
                    } else {
                        actualResult = "Failed";
                        reason = "Registration unsuccessful";
                    }
                }
                
                // Write results back to Excel
                excel.setCellData(sheetName, row, 5, actualResult);
                excel.setCellData(sheetName, row, 6, reason);
                
                // Apply color formatting based on actual result being "Success" or "Failed"
                if(actualResult.equalsIgnoreCase("Success")) {
                    excel.fillCellColor(sheetName, row, 5, IndexedColors.GREEN);
                } else {
                    excel.fillCellColor(sheetName, row, 5, IndexedColors.RED);
                }
                
                System.out.println(actualResult.equals("Success") ? 
                    "[Success] Registration Successful" : 
                    "[Failed] " + reason);
                
                Thread.sleep(2000);
            }
        } finally {
            // Quit WebDriver after all tests are complete
            if (driver != null) {
                driver.quit();
            }
        }
    }
}