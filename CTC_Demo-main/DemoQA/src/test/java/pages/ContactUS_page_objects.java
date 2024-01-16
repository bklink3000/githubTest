package pages;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import module.ContactUS_code;

public class ContactUS_page_objects {

	@FindBy(xpath = "(//li/a[contains(text(),'Contact')])[2]")
	@CacheLookup
	public WebElement ContactUS;
	
	@FindBy(xpath = "//input[@value='Send Email']")
	@CacheLookup
	public WebElement SendEmailBtn;
	
	@FindBy(xpath = "//*[@id='wpcf7-f3093-p1979-o1']/form/p[1]/span/input[@name='your-name']")
	@CacheLookup
	public WebElement Name_txt;
	
	@FindBy(xpath = "//span[@data-name='your-name']/span[contains(text(),'Please fill the required field')]")
	@CacheLookup
	public WebElement Name_txt_Error;
	
	@FindBy(xpath = "//div[2]//input[@name='your-email']")
	@CacheLookup
	public WebElement Email_txt;
	
	@FindBy(xpath = "//span[@data-name='your-email']/span[@class='wpcf7-not-valid-tip']")
	@CacheLookup
	public WebElement Email_Err_Tip;
	
	@FindBy(xpath = "//div[2]//input[@name='your-subject']")
	@CacheLookup
	public WebElement Subject_txt;
	
	@FindBy(xpath = "//select[@class='wpcf7-form-control wpcf7-select wpcf7-validates-as-required']")
	@CacheLookup
	public WebElement Select_dropdown;
	
	@FindBy(xpath = "//span[@class='wpcf7-form-control wpcf7-checkbox']/span/label/input")
	@CacheLookup
	public List<WebElement> Select_checkbox;
	
	@FindBy(xpath = "//span[@class='wpcf7-form-control wpcf7-radio']/span/label/input")
	@CacheLookup
	public List<WebElement> Select_radio;
	
	@FindBy(xpath = "//textarea[@name='your-message']")
	@CacheLookup
	public WebElement Message_box;
	
	@FindBy(xpath = "(//div[@class='wpb_wrapper']/p)[2]")
	@CacheLookup
	public WebElement Address;
	
	@FindBy(xpath = "//*[@id='wpcf7-f3093-p1979-o1']/form/div[3]")
	public WebElement formSentConfirmation;
	
	
	public void EnterText(WebElement field, String text) {
		JavascriptExecutor js = (JavascriptExecutor) ContactUS_code.getDriver();
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
		field.click();
		field.clear();
		field.sendKeys(text + Keys.TAB);
	}
	
	public void select_drpdownbyValue(String value) {
		
		Select dropdown = new Select(this.Select_dropdown);
		try {
		this.Select_dropdown.click();
		dropdown.selectByVisibleText(value);
		} catch(WebDriverException e) {
			((JavascriptExecutor) ContactUS_code.getDriver()).executeScript("arguments[0].scrollIntoView(true);", Select_dropdown);
			//Thread.sleep(500); 
			JavascriptExecutor js = (JavascriptExecutor) ContactUS_code.getDriver();
			js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
			this.Select_dropdown.click();
			dropdown.selectByVisibleText(value);
		}
		
	}
	
	public void checkboxByValue(List<WebElement> item, String chkBox) {
		item.forEach(k -> {
			if (k.getAttribute("value").equals(chkBox)) {
				k.click();
				System.out.println(k.getAttribute("type") + " " + chkBox + " has been selected");
			} 

		});
	}
	
	
}
