
@tag
Feature: Error Validation
  I want to use this template for my feature file

 
  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When Logged in with the user name <email> and passowrd <password>
    Then "Incorrect email or password." message is displayed
    
    Examples: 
    | email |                | password | 
    |iks@gmail.com|          |Capital@10| 
    |shetty@gmail.com|       |Iamking@000|
