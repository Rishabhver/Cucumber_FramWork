package stepDefination;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import customListerners.CustomerListeners;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

public class UpdateCustomerAPI extends CustomerListeners {
	
	RequestSpecification requestSpecification = null;
	Response response = null;
	ExtentTest logInfo = null;
	
	@Given("I set valid auth for updated customer")
	public void i_set_valid_auth_for_updated_customer() {
		requestSpecification = given().auth().basic(configProperty.getValidSecretKey(), "");
	}
	@Given("I pass email {string} Which is to be updated")
	public void i_pass_email_Which_is_to_be_updated(String updateEmail) {
	    
		requestSpecification.formParam("email", updateEmail);
	}

	@Given("I pass description {string} to be updated")
	public void i_pass_description_to_be_updated(String updatedDesc) {
	    
		requestSpecification.formParam("description", updatedDesc);
	}
	
	@When("I send a post request  URI od update CustomerAPI")
	public void i_send_a_post_request_URI_od_update_CustomerAPI() {
		System.out.println("Customer ID is --- > " + CreateCustomerApiStepDefination.customerID);
	    response = requestSpecification.post(configProperty.getCustomerApiEndPoint()+"/"+ CreateCustomerApiStepDefination.customerID);
	    response.prettyPrint();
	}

	@Then("I sould get {int} as a status code in response of updated API")
	public void i_sould_get_as_a_status_code_in_response_of_updated_API(int statusCode) {
	    
		Assert.assertEquals(response.statusCode(), statusCode);
	}

	@Then("I should get {string} as updated email ID")
	public void i_should_get_as_updated_email_ID(String updEmail) {
	    
	   Assert.assertEquals(response.jsonPath().get("email"), updEmail);
	}

	@Then("I should get {string} as updated	description field")
	public void i_should_get_as_updated_description_field(String updDesc) {
	    
		 Assert.assertEquals(response.jsonPath().get("description"), updDesc);
	}
	
}
