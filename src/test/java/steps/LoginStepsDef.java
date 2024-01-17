package steps;
import base.baseMethods;
import base.browserSetup;
import data_driven.ReadPropertiesFile;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddToCart;
import pages.LoginForm;
import tests.TestBase;
import java.io.IOException;

public class LoginStepsDef extends TestBase{

    static ReadPropertiesFile getPropValue = new ReadPropertiesFile();

    @Given("The user in the SauceDemo")
    public void the_user_in_the_sauce_demo() throws IOException {
        System.out.println("The value of driver in LoginStepsDef is " + driver);
        LoginForm.navigateToSauceDemo();
    }
    @When("User enters valid Username and valid Password")
    public void user_enters_valid_username_and_enters_valid_password() throws IOException {
        new LoginForm(driver).loginByUsernameAndPassword(getPropValue.readPropertiesFile("StandardUser") , getPropValue.readPropertiesFile("Password"));
    }
    @When("User clicks on Login button")
    public void user_clicks_on_login_button() throws IOException {
        LoginForm.clickOnLoginCTA();
    }
    @Then("User should be redirected to dashboard")
    public void user_should_be_redirected_to_dashboard() throws IOException {
        browserSetup.waitScreen();

        LoginForm.checkThatUserOnDashboard();
    }

    @And("User clicks on Logout")
    public void userClicksOnLogout() throws IOException {
        new baseMethods(driver).logoutFromAccount();
    }

    @When("User keeps the fields of Username and Password empty")
    public void userKeepsTheFieldsOfUsernameAndPasswordEmpty() throws IOException {
        driver.navigate().refresh();
        new LoginForm(driver).loginByUsernameAndPassword("" , "");
    }

    @Then("An error[Username is required] should be displayed")
    public void anErrorUsernameIsRequiredShouldBeDisplayed() throws IOException {
        LoginForm.checkThatAnErrorPopupIsDisplayed("Epic sadface: Username is required");
    }

    @When("User enters Username and keeps Password empty")
    public void userEntersUsernameAndKeepsPasswordEmpty() throws IOException {
        driver.navigate().refresh();
        new LoginForm(driver).loginByUsernameAndPassword(getPropValue.readPropertiesFile("StandardUser")  , "");
    }

    @Then("An error[Password is required] should be displayed")
    public void anErrorPasswordIsRequiredShouldBeDisplayed() throws IOException {
        LoginForm.checkThatAnErrorPopupIsDisplayed("Epic sadface: Password is required");
    }

    @When("User enters valid Username and invalid Password")
    public void userEntersValidUsernameAndInvalidPassword() throws IOException {
        driver.navigate().refresh();
        new LoginForm(driver).loginByUsernameAndPassword(getPropValue.readPropertiesFile("StandardUser")  , "radwa");
    }

    @Then("An error[Username and password do not match any user in this service] should be displayed")
    public void anErrorUsernameAndPasswordDoNotMatchAnyUserInThisServiceShouldBeDisplayed() throws IOException {
        LoginForm.checkThatAnErrorPopupIsDisplayed("Epic sadface: Username and password do not match any user in this service");

    }

    @When("Locked User tries to login")
    public void lockedUserTriesToLogin() throws IOException {
        new LoginForm(driver).loginByUsernameAndPassword(getPropValue.readPropertiesFile("LockedUser") , getPropValue.readPropertiesFile("Password"));

        System.out.println("Locked User is " + getPropValue.readPropertiesFile("LockedUser") );
        System.out.println("Password is " + getPropValue.readPropertiesFile("Password") );


    }

    @Then("An error[Sorry, this user has been locked out.] should be displayed")
    public void anErrorSorryThisUserHasBeenLockedOutShouldBeDisplayed() throws IOException {
        browserSetup.waitScreen();
        LoginForm.checkThatAnErrorPopupIsDisplayed("Epic sadface: Sorry, this user has been locked out.");
        driver.navigate().refresh();
    }

    @When("Problem User tries to login")
    public void problemUserTriesToLogin() throws IOException {
        driver.navigate().refresh();
        new LoginForm(driver).loginByUsernameAndPassword(getPropValue.readPropertiesFile("ProblemUser") , getPropValue.readPropertiesFile("Password"));
    }

    @Then("check the dashboard")
    public void checkTheDashboard() {
        if (LoginForm.checkTheScreensOfCardsOnDashboard())
        {
            System.out.println("All Images Sources are the same, There is a problem in the account!");
        }
        else
            System.out.println("All Images Sources aren't the same, There isn't a problem");
    }

    @When("User adds some products to his cart")
    public void userAddsSomeProductsToHisCart() throws IOException {
        new AddToCart(driver).addProductsToCart("add-to-cart-sauce-labs-backpack");
        new AddToCart(driver).addProductsToCart("add-to-cart-sauce-labs-bike-light");
    }

    @And("User clicks on cart icon")
    public void userClicksOnCartIcon() throws IOException {
        new AddToCart(driver).clickOnCartIcon();
    }

    @And("User clicks on Checkout CTA")
    public void userClicksOnCheckoutCTA() throws IOException {
        new AddToCart(driver).checkoutProducts("Radwa"  , "Mahfouz" , "12345");
        new AddToCart(driver).checkThatConfirmationIsDisplayed("Thank you for your order!");
    }

    @When("User tries to login with error user data")
    public void userTriesToLoginWithErrorUserData() throws IOException {
        driver.navigate().refresh();
        new LoginForm(driver).loginByUsernameAndPassword(getPropValue.readPropertiesFile("ErrorUser") , getPropValue.readPropertiesFile("Password"));
    }

    @When("User tries to click on specific product")
    public void userTriesToClickOnSpecificProduct() throws IOException {
        new AddToCart(driver).clickOnSpecificProduct("//*[text()='Sauce Labs Backpack']");
    }

    @Then("An error[A description should be here, but it failed to render!] should be displayed")
    public void anErrorADescriptionShouldBeHereButItFailedToRenderShouldBeDisplayed() throws IOException {
        browserSetup.waitScreen();
        new AddToCart(driver).checkErrorTextIfProductDescriptionNotRetrieved("A description should be here, but it failed to render! This error has been reported to Backtrace.");
        driver.navigate().refresh();
    }
}
