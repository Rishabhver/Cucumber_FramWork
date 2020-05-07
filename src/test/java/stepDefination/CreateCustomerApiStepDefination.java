package stepDefination;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import util.ConfigProperty;
import customListerners.CustomerListeners;
import gherkin.ast.Feature;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Scenario;

public class CreateCustomerApiStepDefination extends CustomerListeners {
	
	RequestSpecification requestSpecification = null;
	Response response = null;
	ExtentTest logInfo = null;
	
	/*
	 * In cucumber we have only 2 annotations: -
	 * 
	 * @Before:- Gets executed before every scenario
	 * 
	 * @After:- Gets executed after every scenario
	 * 
	 * 
	 */
	
	@Before("@CresteCustomerAPI")
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
	
	
	
	@Given("I set a valid auth")
	public void i_set_a_valid_auth() {
		
		requestSpecification = given().auth().basic(CustomerListeners.configProperty.getValidSecretKey(), "");
		
		try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "I set a valid auth");

			logInfo.info("I set the authentication successfully");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	  
	}

	@Given("I pass {string} as email")
	public void i_pass_as_email(String email) {
		requestSpecification.formParam("email", email);
		
		try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "I set email");

			logInfo.info("I set the email successfully");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	    
	}

	@Given("I pass {string} as description")
	public void i_pass_as_description(String Desc) {
		requestSpecification.formParam("description", Desc);
		
		try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "I set a description");

			logInfo.info("I set the description successfully");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}

	@When("I send a post request  URI")
	public void i_send_a_post_request_URI() {
		
		response = requestSpecification.post(CustomerListeners.configProperty.getCustomerApiEndPoint());
		response.prettyPrint();
		
		try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "I send post request");

			logInfo.info("I send post request successfully");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	    
	}
	
	@Given("I set a invalid auth")
	public void i_set_a_invalid_auth() {
            requestSpecification = given().auth().basic(CustomerListeners.configProperty.getInValidSecretKey(), "");
		
		try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "I set a Invalid auth");

			logInfo.info("I set the InValid Key successfully");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}

	@Then("I sould get {int} as a status code in response")
	public void i_sould_get_as_a_status_code_in_response(int statusCode) {
	    
		System.out.println("Status code is--> " +  response.statusCode());
	    Assert.assertEquals(response.getStatusCode(), statusCode);
	    
	    try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "The should get " +statusCode +"as expected status code");

			logInfo.info("I got "  +response.getStatusCode() +     " as status code successfully");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}

	@Then("I should get {string} in the response email field")
	public void i_should_get_in_the_response_email_field(String RespEmail) {
		String actualEmailInResponse = response.jsonPath().get("email");
		Assert.assertEquals(actualEmailInResponse, RespEmail);
		
		 try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "The should get " +RespEmail +"as the email in response");

			logInfo.info("I got " +response.jsonPath().get("email")  +   " as email successfuly");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}

	@Then("I should get {string} in the response	description field")
	public void i_should_get_in_the_response_description_field(String resDesc) {
		String actualDescriptionInResponse = response.jsonPath().get("description");
		Assert.assertEquals(actualDescriptionInResponse, resDesc);
		
		try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "The should get " +resDesc +"as the description in response");

			logInfo.info("I got "+ response.jsonPath().get("description")    + " as description");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	}

	@Then("The Customer id field should not be null")
	public void the_Customer_id_field_should_not_be_null() {
		
		Assert.assertNotNull(response.jsonPath().get("id"));
		
		try {

			 logInfo = test.createNode(new GherkinKeyword("Given"), "The should get id the description in response");

			logInfo.info("I got"+ response.jsonPath().get("id")    + " as id");

		} catch (AssertionError | Exception e) {
			testStepHandle("FAIL", logInfo, e);
		}
	    
	}
}

