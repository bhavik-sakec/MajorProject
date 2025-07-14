package pageObject;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import userDefinedFiles.DriverSetup;
import userDefinedFiles.ExcelUtility;
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
            driver = DriverSetup.getDriver();
            js = (JavascriptExecutor) driver;
            RegistrationPage page = new RegistrationPage(driver);

            for (int row = 1; row <= rowCount; row++) {
                driver.get(baseUrl);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                if (checkClickMethod.safeClick(driver, page.accountButton)) {
                    System.out.println("[Success] Account Button Clicked");
                } else {
                    System.out.println("[Failed] Account Button Not Clicked");
                }

                js.executeScript("arguments[0].scrollIntoView(true)", page.newCustomerScroll);
                Thread.sleep(500);

                if (checkClickMethod.safeClick(driver, page.registrationButton)) {
                    System.out.println("[Success] Registration Button Clicked");
                } else {
                    System.out.println("[Failed] Registration Button Not Clickable");
                }

                js.executeScript("arguments[0].scrollIntoView(true)", page.registerScroll);

                String firstName = excel.getCellData(sheetName, row, 0);
                String lastName = excel.getCellData(sheetName, row, 1);
                String email = excel.getCellData(sheetName, row, 2);
                String password = excel.getCellData(sheetName, row, 3);
                String expectedResult = excel.getCellData(sheetName, row, 4);

                page.firstNameInput.sendKeys(firstName);
                page.lastNameInput.sendKeys(lastName);
                page.emailInput.sendKeys(email);
                page.passwordInput.sendKeys(password);

                if (checkClickMethod.safeClick(driver, page.registerSubmit)) {
                    System.out.println("[Success] Register Button Clicked");
                } else {
                    System.out.println("[Failed] Register Button Not Clickable");
                }

                Thread.sleep(2000);
                String currentUrl = driver.getCurrentUrl();
                String actualResult;
                String reason = "";

                try {
                    boolean accountExists = page.accountExistsMessage.isDisplayed();
                    if (accountExists) {
                        actualResult = "Failed";
                        reason = "Account Exist";
                    } else {
                        actualResult = "Success";
                    }
                } catch (org.openqa.selenium.NoSuchElementException e) {
                    actualResult = currentUrl.equals(baseUrl) ? "Success" : "Failed";
                    reason = "Registration unsuccessful";
                }

                excel.setCellData(sheetName, row, 5, actualResult);
                excel.setCellData(sheetName, row, 6, reason);

                if (actualResult.equalsIgnoreCase("Success")) {
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
            if (driver != null) {
                driver.quit();
            }
        }
    }
}