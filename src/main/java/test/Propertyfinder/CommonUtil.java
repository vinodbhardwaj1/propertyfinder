package test.Propertyfinder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonUtil {

	public WebDriver launchBrowserAndDriver(String browser) {
		WebDriver driver = null;
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + File.separator + "grid/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("disable-infobars");
				
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
	
	public boolean waitForElement(WebDriver driver, int maxWait, String findBy, String findByText) {
		boolean result = false;
		WebElement waitElement = null;
		WebDriverWait wait = new WebDriverWait(driver, maxWait);
		try {
			if (findBy.equalsIgnoreCase("id"))
				waitElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.id(findByText)));
			else if (findBy.equalsIgnoreCase("xpath"))
				waitElement = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(findByText)));

			if (waitElement != null && waitElement.isDisplayed()) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public void clickOnElement(WebElement elem) {
		try {
			elem.click();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void enterText(WebElement elem, String text) throws InterruptedException {
		try {
			elem.click();
			elem.clear();
			elem.sendKeys(text);
			Thread.sleep(500);
		} catch (NoSuchElementException e) {
			System.out.println("Search Box not found");
		}
	}

	public void selectFromDropDown(WebElement element, List<WebElement> elemList, String text) {
		clickOnElement(element);
		for (WebElement elem : elemList) {
			if (elem.getText().equalsIgnoreCase(text)) {
				elem.click();
				break;
			}
		}
	}

	public void writeDataIntoCSVFile(ArrayList<String> price) {
		final String NEW_LINE_SEPERATOR = "\n";
		FileWriter fw = null;
		try {
			
			File file = new File(System.getProperty("user.dir") + "/Price.csv");
			fw = new FileWriter(file);

			fw.append("PRICES");
			fw.append(NEW_LINE_SEPERATOR);
			
			for (String s : price) {
				fw.append(s);
				fw.append(NEW_LINE_SEPERATOR);
			}
			System.out.println("Data written into CSV file!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getCurrentTimeDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}
	
	public void takeScreenShot(WebDriver driver) {
		try {
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			String filePath = System.getProperty("user.dir") + File.separator + "ScreenShots";
			new File(filePath).mkdir();

			String screenShotPath = filePath + File.separator + "screenshot"+getCurrentTimeDate()+".png";

			File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(screenShotPath));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	 public void writeUsingFileWriter(LinkedHashMap<String,String> hm) {
	        File file = new File(System.getProperty("user.dir")+"/AgentDetails.txt");
	        FileWriter fr = null;
	        try {
	            fr = new FileWriter(file);
	            for(Entry<String, String> m:hm.entrySet()){
					fr.write(m.getKey()+" : "+m.getValue());
					fr.write(System.getProperty("line.separator"));
				}
	            System.out.println("Text File written completed!");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            try {
	                fr.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 
	 public void pageScrollDown(WebDriver driver) throws InterruptedException{
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		 Thread.sleep(10000);
	 }
	 
	 public void pageScrollDown(WebDriver driver, int scrollValue) throws InterruptedException{
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 
		 Long startValue = (Long) js.executeScript("return window.pageYOffset;");
		 Long endValue = startValue+scrollValue;
		 
		 js.executeScript("window.scrollTo("+startValue+","+endValue+")");
		 Thread.sleep(10000);
	 }
	 
	 
	 public void pageScrollUpToElement(WebDriver driver, WebElement element) throws InterruptedException{
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("arguments[0].scrollIntoView();", element);
	 }
	 
	 
	 
	 public boolean isElementDisplayed(WebElement element){
		 boolean result=false;
		 try{
			 if(element.isDisplayed()){
				 result=true;
			 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return result;
	 }
	 
	public boolean waitUntilElementNotClickable(WebDriver driver, WebElement element) {
		boolean result=false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(element));
			if(elem!=null){
				result = true;
			}
			
		} catch (NoSuchElementException | WebDriverException e) {
			e.toString();
		}
		return result;
	}
	
	 
	 
}
