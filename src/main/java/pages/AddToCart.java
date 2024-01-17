package pages;

import base.baseMethods;
import data_driven.ReadPropertiesFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class AddToCart {

    public static WebDriver driver;

    public AddToCart(WebDriver driver) {
        AddToCart.driver = driver;
    }
    static ReadPropertiesFile getPropValue = new ReadPropertiesFile();

    public void addProductsToCart(String product) throws IOException {
        //String colorBeforeAdding = driver.findElement(By.id("add-to-cart-"+product)).getCssValue("color");
        //Color colorBeforeAdding = Color.fromString(driver.findElement(By.id("add-to-cart-"+product)).getCssValue("color"));
        //Assert.assertEquals(colorBeforeAdding, "#132322");
        WebElement selectProduct = driver.findElement(By.id(product));
        selectProduct.click();
    }
    public void clickOnCartIcon() throws IOException {
        WebElement cartIcon = driver.findElement(By.id(getPropValue.readPropertiesFile("CartIcon")));
        cartIcon.click();
        WebElement YourCart = driver.findElement(By.xpath(getPropValue.readPropertiesFile("YourCartTitle")));
        Assert.assertTrue(YourCart.isDisplayed());
    }

    public void checkoutProducts(String firstName, String lastName, String postalCode) throws IOException {
        WebElement checkoutCTA = driver.findElement(By.id(getPropValue.readPropertiesFile("CheckoutCTA")));
        while(!checkoutCTA.isDisplayed())
        {
            new baseMethods(driver).scrollUpDownPage();
        }
        new baseMethods(driver).clickOnElement(By.id(getPropValue.readPropertiesFile("CheckoutCTA")));

        WebElement CheckoutTitle = driver.findElement(By.xpath(getPropValue.readPropertiesFile("CheckoutTitle")));
        Assert.assertTrue(CheckoutTitle.isDisplayed());

        new baseMethods(driver).enterText(driver.findElement(By.name(getPropValue.readPropertiesFile("FirstNameField"))), firstName);
        new baseMethods(driver).enterText(driver.findElement(By.name(getPropValue.readPropertiesFile("LastNameField"))), lastName);
        new baseMethods(driver).enterText(driver.findElement(By.name(getPropValue.readPropertiesFile("PostalCode"))), postalCode);

        new baseMethods(driver).clickOnElement(By.id(getPropValue.readPropertiesFile("ContinueCTA")));

        WebElement finishCTA = driver.findElement(By.id(getPropValue.readPropertiesFile("FinishCTA")));
        while(!finishCTA.isDisplayed())
        {
            new baseMethods(driver).scrollUpDownPage();
        }
        new baseMethods(driver).clickOnElement(By.id(getPropValue.readPropertiesFile("FinishCTA")));

    }

    public void clickOnSpecificProduct(String productElement) throws IOException {
        new baseMethods(driver).clickOnElement(By.xpath(productElement));
    }
    public void checkThatConfirmationIsDisplayed(String ExpectedConfirmationMsg) throws IOException {

        Assert.assertEquals(new baseMethods(driver).getPresentedText(By.xpath(getPropValue.readPropertiesFile("ConfirmMsg"))),
                ExpectedConfirmationMsg);
    }
    public void checkErrorTextIfProductDescriptionNotRetrieved(String ExpectedErrorDesc) throws IOException {

        Assert.assertEquals(new baseMethods(driver).getPresentedText(By.xpath(getPropValue.readPropertiesFile("ErrorDescription"))),
                ExpectedErrorDesc);
    }


}
