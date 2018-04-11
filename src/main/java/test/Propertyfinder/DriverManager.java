package test.Propertyfinder;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class DriverManager {

	public WebDriver driver=null;
	CommonUtil cObj = new CommonUtil();
	
	@BeforeMethod
	@Parameters({"browser", "url"})
	public void startDriver(String browser, String url){
		System.out.println("Launching the browser...");
		driver= cObj.launchBrowserAndDriver(browser);
		driver.get(url);
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		if(driver!=null)
			driver.quit();
	}
	
	
}
