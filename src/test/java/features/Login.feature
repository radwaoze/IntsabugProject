Feature: Login Functionality
  I want to validate the functionality of Login on website saucedemo

  @E2E
  Scenario: Verify that user can login successfully [E2E]
    When User enters valid Username and valid Password
    And User clicks on Login button
    Then User should be redirected to dashboard
    When User adds some products to his cart
    And User clicks on cart icon
    And User clicks on Checkout CTA
    And User clicks on Logout

  @lockedUser
  Scenario: Verify that when locked user tries to login an error screen is displayed
    When Locked User tries to login
    And User clicks on Login button
    Then An error[Sorry, this user has been locked out.] should be displayed

  @errorUser
  Scenario: Check that error screen is displayed if the description of product isn't retrieved
    When User tries to login with error user data
    And User clicks on Login button
    Then User should be redirected to dashboard
    When User tries to click on specific product
    Then An error[A description should be here, but it failed to render!] should be displayed
    And User clicks on Logout

  @problemUser
  Scenario: Check the behavior of website if a user that has problem tires to login
    When Problem User tries to login
    And User clicks on Login button
    Then check the dashboard
    And User clicks on Logout

  @invalidCredentials
  Scenario: Verify that an error screen is displayed when user tries to login without entering neither UN nor PW
    When User keeps the fields of Username and Password empty
    And User clicks on Login button
    Then An error[Username is required] should be displayed

  @invalidCredentials
  Scenario: Verify that an error screen is displayed when user tries to login without entering PW
    When User enters Username and keeps Password empty
    And User clicks on Login button
    Then An error[Password is required] should be displayed

  @invalidCredentials
  Scenario: Verify that an error screen is displayed when user tries to login with invalid data
    When User enters valid Username and invalid Password
    And User clicks on Login button
    Then An error[Username and password do not match any user in this service] should be displayed

