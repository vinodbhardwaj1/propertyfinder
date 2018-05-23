package test.Propertyfinder;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCases extends DriverManager {

	PageUtil pageObj;
	LinkedInPageUtil linkedInUtilObj;

	
	@Test
	public void sendRequest() throws InterruptedException{
		System.out.println("Test Execution started....");
		linkedInUtilObj = new LinkedInPageUtil(driver);
		
		linkedInUtilObj.loginIntoLinkedIn();
		linkedInUtilObj.sendConnectionRequest();
		
		
	}
	
	
	
	
//	 @Test
		public void verifyAllLinks() {
			System.out.println("Test Execution started...");
			pageObj = new PageUtil(driver);
			pageObj.verifyAllLinks();
			System.out.println("Test execution completed");

		}
	 
	 
//	 @Test
	public void verifySearchProperty() {
		try {
			System.out.println("Test Execution started...");
			pageObj = new PageUtil(driver);
			pageObj.searchAndPropertyByPrice();
			System.out.println("Test execution completed");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

//	@Test
	public void verifyAgentFiltering() {
		boolean result = false;
		try {
			System.out.println("Test Execution started...");
			pageObj = new PageUtil(driver);
			result = pageObj.filterAgents();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Test execution completed");
			Assert.assertEquals(result, true);
		}
	}
	
//	@Test
	public void verifyAgentDetails() {
		try {
			System.out.println("Test Execution started...");
			pageObj = new PageUtil(driver);
			pageObj.getAgentDetails();
			System.out.println("Test execution completed");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
