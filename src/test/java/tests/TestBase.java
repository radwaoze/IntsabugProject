package tests;

import base.baseMethods;
import base.browserSetup;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;


public class TestBase extends AbstractTestNGCucumberTests {

    public static WebDriver driver;

    @BeforeSuite
    @Parameters("browserName")
    public void setup(@Optional("Chrome") String browserName){
       driver = new browserSetup().BrowserName(browserName);
        browserSetup.maximizeScreen();
        System.out.println("The value of driver in TestBase is " + driver);
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE)
        {
            System.out.println("Result Status is " + result.getStatus());
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screen = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screen , new File("./screenshots/" + result.getTestName()+ ".png"));
        }
    }

    @AfterSuite
    public void afterSuite() {
        browserSetup.closeBrowser();
    }
}
