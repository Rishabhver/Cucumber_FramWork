Feature: Update CustomerInfo API

Scenario Outline: Verify Update Customer API with valid data

Given I set valid auth for updated customer
And I pass email "<emaiID>" Which is to be updated
And I pass description "<description>" to be updated
When I send a post request  URI od update CustomerAPI
Then I sould get <statusCode> as a status code in response of updated API
And I should get "<ExpectedEmailInResponse>" as updated email ID
And I should get "<ExpectedDescriptionInResponse>" as updated	description field


Examples:

     |emaiID|description|statusCode|ExpectedEmailInResponse|ExpectedDescriptionInResponse|
     |updatedEmail1@gmail.com|I Updated the description|200|updatedEmail1@gmail.com|I Updated the description|