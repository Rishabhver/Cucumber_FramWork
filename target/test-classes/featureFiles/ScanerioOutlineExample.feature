@CresteCustomerAPI
Feature: Validate Create Customer API

Scenario Outline: Validate Create Customer API with Valid Auth Key and data
Given I set a valid auth
And I pass "<email>" as email
And I pass "<description>" as description
When I send a post request  URI
Then I sould get <statusCode> as a status code in response
And I should get "<ExpectedEmailInResponse>" in the response email field
And I should get "<ExpectedDescriptionInResponse>" in the response	description field
And The Customer id field should not be null


Examples:

     |email|description|statusCode|ExpectedEmailInResponse|ExpectedDescriptionInResponse|
     |test_011@gmail.com|Sample Description 1|200|test_011@gmail.com|Sample Description 1|
     
     

Scenario Outline: Validate Create Customer API with InValid Auth Key and data
Given I set a invalid auth
And I pass "<email>" as email
And I pass "<description>" as description
When I send a post request  URI
Then I sould get <statusCode> as a status code in response


Examples:

     |email|description|statusCode|
     |test_012@gmail.com|Sample Description 2|401|