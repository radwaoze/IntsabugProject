package pages;


import base.baseMethods;
import data_driven.ReadPropertiesFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginForm {

    public static WebDriver driver;

    public LoginForm(WebDriver driver) {
        LoginForm.driver = driver;
    }
    static ReadPropertiesFile getPropValue = new ReadPropertiesFile();


    public static void navigateToSauceDemo() throws IOException {
        System.out.println("The value of driver in LoginForm is " + driver);
        //new baseMethods(driver).navigateToAnyWebsite("https://www.saucedemo.com/");
    }

    public void loginByUsernameAndPassword(String Username, String Password) throws IOException {
        new baseMethods(driver).enterText(driver.findElement(By.id(getPropValue.readPropertiesFile("UN_Field"))), Username);
        new baseMethods(driver).enterText(driver.findElement(By.id(getPropValue.readPropertiesFile("PW_Field"))), Password);
    }

    public static void clickOnLoginCTA() throws IOException {
        WebElement LoginCTA = driver.findElement(By.id(getPropValue.readPropertiesFile("LoginCTA")));
        LoginCTA.click();
    }

    public static void checkThatUserOnDashboard() throws IOException {
        WebElement ProductsTitle = driver.findElement(By.xpath(getPropValue.readPropertiesFile("ProductsTitle")));
        Assert.assertTrue(ProductsTitle.isDisplayed());
    }

    public static void checkThatAnErrorPopupIsDisplayed(String ExpectedError) throws IOException {

        Assert.assertEquals(new baseMethods(driver).getPresentedText(By.xpath(getPropValue.readPropertiesFile("ErrorMsg"))),
                ExpectedError);
    }

    public static boolean checkTheScreensOfCardsOnDashboard() {
        List<WebElement> Cards = driver.findElements(By.xpath("//img[@class='inventory_item_img']"));
        System.out.println("Cards size is " + Cards.size());

        List<String> srcAllImages = new ArrayList<>();
        for (int i = 0; i<Cards.size(); i++) {
            srcAllImages.add(Cards.get(i).getAttribute("src"));
            System.out.println("Src attribute of Cards: " + Cards.get(i).getAttribute("src"));

        }
        System.out.println("srcAllImages is: " + srcAllImages);

        String srcCompare = Cards.get(0).getAttribute("src");
        System.out.println("srcCompare is: " + srcCompare);

        boolean flag = false;
        for (int j = 0; j<srcAllImages.size(); j++) {
            if (srcAllImages.get(j).equalsIgnoreCase(srcCompare)) {
                System.out.println("Src of Images: " + srcAllImages.get(j));
                flag=true;
            }
            else flag = false;
        }
        return flag;
    }

    public static void addProductsToCart(String product) throws IOException {
        //String colorBeforeAdding = driver.findElement(By.id("add-to-cart-"+product)).getCssValue("color");
        //Color colorBeforeAdding = Color.fromString(driver.findElement(By.id("add-to-cart-"+product)).getCssValue("color"));
        //Assert.assertEquals(colorBeforeAdding, "#132322");
        WebElement selectProduct = driver.findElement(By.id(product));
        selectProduct.click();
    }


}