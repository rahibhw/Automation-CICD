
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

   Background: 
   Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with my username <name> and password <password> 
    When I add product <productName> to cart
    And  Checkout <productName> and submit the order
    Then Confirmation message <confirmMessage> is displayed on confirmation page

    Examples: 
      | name           | password | productName | confirmMessage |
      | rahi@gmail.com | Mom@1966 | ZARA COAT 3 | THANKYOU FOR THE ORDER. |

