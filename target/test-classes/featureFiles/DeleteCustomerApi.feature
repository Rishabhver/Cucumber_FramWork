@DeleteCustomerAPI
Feature: Validate delete customer API

Scenario Outline: Validate delete customer API with valid auth and valid customer ID
Given I set valid auth

When I send post request with customer id tobe deleted
Then I should get status code <statusCode> in the response
And I should get deleted customerId  "<deletedCustomerID>" in the response

Examples:

    |statusCode|deletedCustomerID|
    |200|cus_HEvI0imkigvp0u|