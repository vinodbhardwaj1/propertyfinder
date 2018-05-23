package test.Propertyfinder;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LinkedInPageUtil {

	public WebDriver driver = null;
	CommonUtil cObj = new CommonUtil();

	@FindBy(id = "login-email")
	private static WebElement userId;

	@FindBy(id = "login-password")
	private static WebElement password;

	@FindBy(id = "login-submit")
	private static WebElement submitBtn;

	@FindBy(css = "img[alt='Vinod Kumar Bhardwaj']")
	private static WebElement loginPic;

	@FindBy(id = "mynetwork-tab-icon")
	private static WebElement myNetworkTab;

	@FindBy(xpath = "//span[contains(@class,'pymk-card__occupation Sans')]")
	private static List<WebElement> profileTextList;

	@FindBy(css = "button[data-control-name='invite']")
	private static List<WebElement> connectBtnList;

	@FindBy(css = "div[class='artdeco-toast-dismiss']")
	private static WebElement requestSentPopupCloseBtn;

	@FindBy(css = "button[class='pv-s-profile-actions pv-s-profile-actions--connect button-primary-large mh1']")
	private static WebElement connectBtn;

	@FindBy(css = "button[class='button-primary-large ml1']")
	private static WebElement requestSendNowBtn;

	@FindBy(css = "button[class='pv-s-profile-actions__overflow-toggle  button-secondary-large-muted mh1']")
	private static WebElement moreBtn;


	public LinkedInPageUtil(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}

	// =======================================================================================

	public void loginIntoLinkedIn() throws InterruptedException {
		try {
			cObj.enterText(userId, "xxxxxxxx@gmail.com");
			cObj.enterText(password, "xxxxxxxxx");
			cObj.clickOnElement(submitBtn);

			if (cObj.isElementDisplayed(loginPic)) {
				System.out.println("Login is successful");
			} else {
				System.out.println("Seems login is failed or page is not loaded");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendConnectionRequest() {
		try {
			cObj.waitForElement(driver, 90, "id", "mynetwork-tab-icon");
			cObj.clickOnElement(myNetworkTab);
			Thread.sleep(5000);

			int size = profileTextList.size();
			System.out.println("First time size: " + size);

			for (int i = 0; i < profileTextList.size(); i++) {
				
				WebElement profile = profileTextList.get(i);
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", profile);
				Thread.sleep(2000);
				
				String profileText = profileTextList.get(i).getText().toString();
				System.out.println(i+" Profile: " + profileText);

				if (profileText.toUpperCase().contains("HR") 
						|| profileText.toUpperCase().contains("TALENT")
						|| profileText.toUpperCase().contains("HUMAN RESOURCES")
						|| profileText.toUpperCase().contains("RESOURCES")
						|| profileText.toUpperCase().contains("STAFFING")
						|| profileText.toUpperCase().contains("HIRING") 
						|| profileText.toUpperCase().contains("SDET")
						|| profileText.toUpperCase().contains("AUTOMATION")
						|| profileText.toUpperCase().contains("RECRUITMENT")
						|| profileText.toUpperCase().contains("LEAD")
						|| profileText.toUpperCase().contains("CTO")
						|| profileText.toUpperCase().contains("CEO")
						|| profileText.toUpperCase().contains("QA MANAGER")
						|| profileText.toUpperCase().contains("RECRUITER")) {

					try {
						cObj.clickOnElement(connectBtnList.get(i));
						System.out.println("Clicked to Connect");
						Thread.sleep(8000);

					} catch (WebDriverException e) {
						System.out.println(e.getMessage());
					} finally {
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
