package test.Propertyfinder;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PageUtil {

	WebDriver driver = null;
	CommonUtil cObj = new CommonUtil();

	@FindBy(css = "input[placeholder='City or Community or Tower']")
	private static WebElement city;

	@FindBy(css = "div[class='searchproperty_column searchproperty_type']")
	private static WebElement propertyType;

	@FindBy(xpath = "//div[@class='searchproperty_column searchproperty_type']/div/div/div")
	private static List<WebElement> propertyTypeList;

	@FindBy(css = "div[class='autocomplete_suggestions']>div>span")
	private static List<WebElement> cityAutoSuggestionList;

	@FindBy(xpath = "//div[@class='searchproperty_column searchproperty_bed']/div[1]")
	private static WebElement minBed;

	@FindBy(xpath = "//div[@class='searchproperty_column searchproperty_bed']/div[2]")
	private static WebElement maxBed;

	@FindBy(xpath = "//div[@class='searchproperty_column searchproperty_bed']/div[1]/div/div")
	private static List<WebElement> minBedList;

	@FindBy(xpath = "//div[@class='searchproperty_column searchproperty_bed']/div[2]/div/div")
	private static List<WebElement> maxnBedList;

	@FindBy(xpath = "//button[@class='button button-fullheight button-connectedright']")
	private static WebElement findBtn;

	@FindBy(xpath = "//span[@class='card_pricevalue']")
	private static List<WebElement> propertyAmountList;

	@FindBy(xpath = "//div[@class='propertyheader_sortfield']")
	private static WebElement sortByBox;

	@FindBy(xpath = "//div[@class='dropdown_popup dropdown_popup-opened false']/div")
	private static List<WebElement> sortingOptionList;

	@FindBy(xpath = "//a[@href='/en/find-agent']")
	private static WebElement findAgentLink;

	@FindBy(xpath = "//div[@data-qs='agent-dropdowns']/div/div[2]")
	private static WebElement languagesBox;

	@FindBy(xpath = "//div[@data-qs='agent-dropdowns']/div/div[2]/div/div/div")
	private static List<WebElement> languageList;

	@FindBy(xpath = "//h1[@class='title']")
	private static WebElement totalAgents;

	@FindBy(xpath = "//div[@class='searchform_row searchform_row-last searchform_row-lastserp']/div[3]/div")
	private static WebElement nationalityBox;

	@FindBy(xpath = "//div[@class='searchform_row searchform_row-last searchform_row-lastserp']/div[3]/div/div[2]/div[contains(text(),'India')]")
	private static WebElement india;

	 @FindBy(xpath="//div[@class='tiles']/a[1]")
	 private static WebElement firstAgent;
	
	 @FindBy(css="h1[class='title title-size1 bioinfo_name']")
	 private static WebElement agentName;
	
	 @FindBy(xpath="//div[@class='bioinfo_personal']//p[1]/span[2]")
	 private static WebElement agentNationality;
	
	 @FindBy(xpath="//div[@class='bioinfo_personal']//p[2]/span[2]")
	 private static WebElement agentLanguage;
	
	 @FindBy(xpath="//div[@class='pane-textsmall pane_columns']/div/p[1]//a")
	 private static WebElement agentActiveProperties;
	
	 @FindBy(xpath="//div[@class='pane-textsmall pane_columns']/div/p[2]/span[2]")
	 private static WebElement agentLicenceNumber ;
	
	 @FindBy(xpath="//div[@class='pane-textsmall pane_columns']/div/p[3]/span[2]")
	 private static WebElement agentExpSince;
	
	 @FindBy(xpath="//div[@class='pane-textsmall pane_columns']/div/p[4]/span[2]/a")
	 private static WebElement agentLinkedInLink;
	 
	 @FindBy(css="a[data-qs='phone-button']")
	 private static WebElement agentPhoneNumber;
	 
	 @FindBy(xpath="//div[@class='brokerthumbnail_text']/p[1]")
	 private static WebElement agentCompanyName;
	 
	 @FindBy(css="a[href='#tab-about']")
	 private static WebElement agentAboutMeLink;
	 
	 @FindBy(css="div[data-qs-content='tab-about']")
	 private static WebElement agentAboutMeText;
	 
	 @FindBy(xpath="//div[@class='header_section']//a[@class='globalswitch_langlink globalswitch_langlink-ar'][1]")
	 private static WebElement arbicLanguageLink;

	public PageUtil(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
	}

	public void searchAndPropertyByPrice() throws InterruptedException {
		ArrayList<String> priceList = new ArrayList<String>();
		cObj.enterText(city, "THE PEARL");
		for (WebElement elem : cityAutoSuggestionList) {
			if (elem.getText().equalsIgnoreCase("THE PEARL")) {
				elem.click();
				break;
			}
		}
		cObj.selectFromDropDown(propertyType, propertyTypeList, "Villa");
//		cObj.clickOnElement(propertyType);
//		WebElement types = driver .findElement(By.xpath("//div[@class='searchproperty_column searchproperty_type']/div/div/div"));
//		Select dropdown = new Select(types);
//		dropdown.selectByValue("Villa");
		
		
		
		
		cObj.selectFromDropDown(minBed, minBedList, "3 Bedrooms");
		cObj.selectFromDropDown(maxBed, maxnBedList, "7 Bedrooms");

		cObj.clickOnElement(findBtn);
		Thread.sleep(4000);

		cObj.selectFromDropDown(sortByBox, sortingOptionList, "Price (high)");
		Thread.sleep(2000);

		for (WebElement element : propertyAmountList) {
			System.out.println(element.getText());
			priceList.add(element.getText().split(" ")[0].replace(",", ""));
		}
		cObj.writeDataIntoCSVFile(priceList);
	}

	public boolean filterAgents() {
		boolean result = false;
		try {
			cObj.clickOnElement(findAgentLink);
			cObj.selectFromDropDown(languagesBox, languageList, "ARABIC");
			cObj.clickOnElement(languagesBox);
			cObj.selectFromDropDown(languagesBox, languageList, "ENGLISH");
			cObj.clickOnElement(languagesBox);
			cObj.selectFromDropDown(languagesBox, languageList, "HINDI");
			cObj.clickOnElement(findBtn);

			Thread.sleep(3000);
			int iTotalAgent = Integer.parseInt(totalAgents.getText().split("\\s")[0].trim());
			System.out.println("Total Agents: " + iTotalAgent);

			cObj.clickOnElement(nationalityBox);
			cObj.clickOnElement(india);
			Thread.sleep(2000);

			int iTotalAgent2 = Integer.parseInt(totalAgents.getText().split("\\s")[0].trim());
			System.out.println("Total Agents: " + iTotalAgent2);
			
			if(iTotalAgent>iTotalAgent2){
				result=true;
			}
		} catch (Exception e) {

		}
		return result;
	}

	public void getAgentDetails() {
		LinkedHashMap<String,String> hm = new LinkedHashMap<String,String>();
		try {
			cObj.clickOnElement(findAgentLink);
			cObj.clickOnElement(firstAgent);
			Thread.sleep(2000);
			
			hm.put("Name", agentName.getText().toString());
			hm.put("Nationality", agentNationality.getText().toString());
			hm.put("Languages", agentLanguage.getText().toString());
			hm.put("LicenceNo", agentLicenceNumber.getText().toString());
			hm.put("PhoneNo", agentPhoneNumber.getAttribute("href").toString());
			hm.put("CompanyName", agentCompanyName.getText().toString());
			hm.put("ExperienceSince", agentExpSince.getText().toString());
			hm.put("ActiveListing", agentActiveProperties.getText().toString());
			hm.put("LinkedInUrl", agentLinkedInLink.getAttribute("href").toString());
			
			cObj.clickOnElement(agentAboutMeLink);
			hm.put("AboutMe", agentAboutMeText.getText().toString());
			
			for(Entry<String, String> m:hm.entrySet()){
				System.out.println(m.getKey()+" : "+m.getValue());
			}
			
			cObj.writeUsingFileWriter(hm);
			
			cObj.takeScreenShot(driver);
			cObj.clickOnElement(arbicLanguageLink);
			cObj.takeScreenShot(driver);
			
		} catch (Exception e) {

		}
	}
	
	
	public void verifyAllLinks(){
		
		List<WebElement> linkList = driver.findElements(By.tagName("a"));
		System.out.println("Total links: "+linkList.size());
		int i=1;
		for(WebElement elem:linkList){
			String url = elem.getAttribute("href");
			verifyLinkUrl(url);
			System.out.println(i++);
		}
		
	}
	
	private void verifyLinkUrl(String url){
		try {
			URL link = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)link.openConnection();
			connection.setConnectTimeout(2000);
			connection.connect();
			
			if(connection.getResponseCode()==200){
				System.out.println(url+" is working fine.");
				System.out.println(connection.getResponseMessage());
			}else if(connection.getResponseCode()==404){
				System.out.println(url+" is broken");
				System.out.println(connection.getResponseMessage());
			}else{
				System.out.println(url+ " not opening");
				System.out.println(connection.getResponseMessage());
			}
			System.out.println("---------------------------------------");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
