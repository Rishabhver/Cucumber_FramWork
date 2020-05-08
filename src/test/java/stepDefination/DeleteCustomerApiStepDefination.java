package stepDefination;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import customListerners.CustomerListeners;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import org.testng.Assert;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Scenario;

public class DeleteCustomerApiStepDefination extends CustomerListeners {
	
	RequestSpecification requestSpecification= null;
	Response response= null;
	ExtentTest logInfo = null;
	
	@Before(order=1)
	public void beforeCreateCustomerAPIScenario(cucumber.api.Scenario scenario) {
		System.out.println("Scenario.getId()-->" + scenario.getId());
		String[] splitedName = scenario.getId().split("/");
		// scenario.getId():-

		String featureName = splitedName[splitedName.length - 1];

		featureName = featureName.substring(0, featureName.indexOf("."));

		String scenarioName = scenario.getName();
		if (createCustomerTest == null) {
			System.out.println("Extent report-->"+extent);
			createCustomerTest = extent.createTest(featureName, featureName);
		}

		// test = extent.createTest(Feature.class, featureName);

		test = createCustomerTest.createNode(Scenario.class, scenarioName);
		System.out.println("Feature Name-->" + featureName);
	}
	
	
	
	@Given("I set valid auth")
	public void i_set_valid_auth() {
	    
		requestSpecification= given().auth().basic(configProperty.getValidSecretKey(), "");
		
		try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "I set a valid auth");

			logInfo.info("I set the authentication successfully");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}


	@When("I send post request with customer id tobe deleted")
	public void i_send_post_request_with_customer_id_tobe_deleted() {
	    
	    response = requestSpecification.delete(configProperty.getCustomerApiEndPoint()+"/"+ CreateCustomerApiStepDefination.customerID);
	    response.prettyPrint();
	    
	    try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "I set a post request");

			logInfo.info("I send Post Request Successfully");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}

	@Then("I should get status code {int} in the response")
	public void i_should_get_status_code_in_the_response(int expectedStatusCode) {
	    
	    Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
	    
	    try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "I should get " +expectedStatusCode + " as status code " );

			logInfo.info("I I got statusCode Successfully");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}

	@Then("I should get deleted customerId  {string} in the response")
	public void i_should_get_deleted_customerId_in_the_response(String expCustID) {
	    
	    Assert.assertEquals(response.jsonPath().get("id"), expCustID);
	    
	    try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "I should get " +expCustID + " as status as deleted cust ID " );

			logInfo.info("User deleted Successfully");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}

}
