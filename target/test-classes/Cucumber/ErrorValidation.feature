
@tag
Feature: Error Validation
  



  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce page
    Given Logged in with my username <name> and password <password>
    Then "Incorrect email or password." message is displayed

  Examples: 
      | name           | password |
      | rahi@gmail.com | Mom@1969 | 

