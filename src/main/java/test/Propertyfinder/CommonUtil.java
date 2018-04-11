package test.Propertyfinder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CommonUtil {

	public WebDriver launchBrowserAndDriver(String browser) {
		WebDriver driver = null;
		DesiredCapabilities capability = null;

		try {
			if (browser.equalsIgnoreCase("chrome")) {
				// capability = DesiredCapabilities.chrome();
				// capability.setCapability("chrome.switches",
				// Arrays.asList("--incognito"));
				// capability.setCapability("chrome.binary",
				// "D:\\Automation\\Propertyfinder\\src\\main\\resources\\chromedriver.exe");
				//
				// ChromeOptions option = new ChromeOptions();
				// option.addArguments("--start-maximized");
				// capability.setCapability(ChromeOptions.CAPABILITY, option);
				
				// driver = new RemoteWebDriver(new
				// URL("http://localhost:4444/wd/hub"), capability);

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + File.separator + "grid/chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
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
}
