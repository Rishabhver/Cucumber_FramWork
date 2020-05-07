package customListerners;

import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



import io.restassured.RestAssured;
import util.ConfigProperty;

public class CustomerListeners extends ExtentReportListener implements ITestListener {
	
	public static ConfigProperty configProperty;

	

	public void onStart(ITestContext arg0) {
		
		 configProperty = ConfigFactory.create(ConfigProperty.class);

		RestAssured.baseURI= configProperty.getBaseURI();
		RestAssured.basePath= configProperty.getBasePath();
		
		 extent= ExtentReportListener.setUp();
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		
		
	}

	public void onTestFailure(ITestResult arg0) {
		
		
	}

	public void onTestSkipped(ITestResult arg0) {
		
		
	}

	public void onTestStart(ITestResult arg0) {
		
		
	}

	public void onTestSuccess(ITestResult arg0) {
		
		
	}
	
    public void onFinish(ITestContext arg0) {
		
		extent.flush();
	}


}
