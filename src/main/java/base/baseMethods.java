package base;

import data_driven.ReadPropertiesFile;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class baseMethods extends browserSetup {

    static WebDriver driver;

    public baseMethods(WebDriver driver) {
        baseMethods.driver = driver;
    }

    static ReadPropertiesFile getPropValue = new ReadPropertiesFile();

    public WebElement waitExpectedCondition(By elementPath, String waitCon) {
        WebElement element = null;
        try {
            switch (waitCon) {
                case "presenceOfElement":
                    element = (new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(elementPath));
                    return element;

                case "elementToBeClickable":
                    element = (new WebDriverWait(driver, 15)).until(ExpectedConditions.elementToBeClickable(elementPath));
                    return element;

                case "visibilityOfElement":
                    element = (new WebDriverWait(driver, 15)).until(ExpectedConditions.visibilityOfElementLocated(elementPath));
                    return element;
            }
        } catch (Exception e) {
            System.out.println("Failed :" + e.getMessage());
            return null;
        }
        return element;
    }
    public void logoutFromAccount() throws IOException {
        WebElement Menu = driver.findElement(By.id(getPropValue.readPropertiesFile("Menu")));
        Menu.click();
        WebElement Logout = driver.findElement(By.id(getPropValue.readPropertiesFile("Logout")));
        Logout.click();
    }

    public void clickOnElement(By elementPath) {
        WebElement element;
        try {
            // element= waitExpectedCondition(elementPath, "presenceOfElement");
            element = waitExpectedCondition(elementPath, "elementToBeClickable");
            assert element != null;
            element.click();
        } catch (Exception e) {
            element = waitExpectedCondition(elementPath, "elementToBeClickable");
            assert element != null;
            element.submit();
        }
    }

    public String getPresentedText(By path) {
        WebElement element = null;
        try {
            return driver.findElement(path).getText();
        } catch (Exception e) {
            System.out.println("Failed to get text ...");
            return null;
        }
    }

    public void enterText(WebElement textField, String text) {
        try {
            textField.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Failed to enter text in field ...");
        }
    }

    public void scrollUpDownPage()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
    }
}