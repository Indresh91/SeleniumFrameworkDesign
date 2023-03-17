
@tag
Feature: Purchase the Order from the E-commerce Website
  //I want to use this template for my feature file

Background:
Given I landed on Ecommerce Page 

  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with the user name <email> and passowrd <password> 
    When I add <productName> to the cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the screen
    Examples: 
    | email |             | password | | productName |
    | iks@gmail.com |     |Capital@10| |ZARA COAT 3|
    |shetty@gmail.com|    |Iamking@000||ADIDAS ORIGINAL|
 