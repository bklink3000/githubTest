package module;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.core.api.Scenario;
import pages.ContactUS_page_objects;

public class ContactUS_code {
	
	protected static WebDriver driver;
	private ContactUS_page_objects pageObjs = new ContactUS_page_objects();
	
	
	private ContactUS_page_objects loadPageObjects()
	{
		try {
			
			pageObjs = PageFactory.initElements(driver, ContactUS_page_objects.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return pageObjs;
	}
	

	public void BrowserInitiate(Scenario scn) {
		
		//Setting system properties of ChromeDriver 
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");

		//Creating an object of ChromeDriver
		driver = new ChromeDriver();

		//launching the specified URL 
		driver.get("https://demo.usa-ctc.com/");
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.titleIs("Demo – Leading provider of information technology services"));
		
	}

	public static WebDriver getDriver() {
		return driver;
	}
	
	public void NavigateToContactUsPage() {
		this.loadPageObjects();
//		PopupAlertMessageBoxModifiable("We are now loading the main page and will click the Contact Us link to go to the next page");
		pageObjs.ContactUS.click();
	}
	
	public void PopupAlertMessageBox() {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("alert('CTC Demo page testing is inprogress. The demonstration will continue automatically');");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}
	
	public void PopupAlertMessageBoxModifiable(String string) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("alert('"+string+"');");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// 
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}


	public void ClickSendEmailButton() {
		this.loadPageObjects();
//		PopupAlertMessageBoxModifiable("We are now clicking the Send Email button with information missing from all of the fields to assure that an error is generated");
		Actions action = new Actions(driver); 
		action.moveToElement(pageObjs.SendEmailBtn).perform();
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section[@id='pageloader' and @style='display: none;']")));
		wait.until(ExpectedConditions.elementToBeClickable(pageObjs.SendEmailBtn));
		pageObjs.SendEmailBtn.click();
	}


	public void assertErrorMessage(String test) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,30);
		this.loadPageObjects();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-name='your-name']/span[contains(text(),'Please fill the required field')]")));
		
		Assert.assertTrue(pageObjs.Name_txt_Error.isDisplayed());
		
	}
	
	//@SuppressWarnings("deprecation")
	public void embedScreenshotOnFail(Scenario s) {
	        try {
	            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	            s.embed(screenshot, "image/png" );
	            s.write("URL at failure: " + driver.getCurrentUrl());
	        } catch (WebDriverException wde) {
	            s.write("Embed Failed " + wde.getMessage());
	        } catch (ClassCastException cce) {
	            cce.printStackTrace();
	        }
	}

	public void enterName(String string) {
		this.waitForPage();
//		PopupAlertMessageBoxModifiable("Here we enter the name");
		pageObjs.EnterText(pageObjs.Name_txt, string);
		
	}

	public void enterEmail(String string) {
		this.waitForPage();
//		PopupAlertMessageBoxModifiable("Here we enter the email");
		pageObjs.EnterText(pageObjs.Email_txt, string);
		
	}

	public void enterSubject(String string) {
		this.waitForPage();
//		PopupAlertMessageBoxModifiable("Here we enter the subject");
		pageObjs.EnterText(pageObjs.Subject_txt, string);	
		
	}
	
	public WebDriverWait waitForPage() {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section[@id='pageloader' and @style='display: none;']")));
		
		return wait;
	}

	public void quitBrowser() {
		driver.close();
		
	}

	public void selectDropDownBylist(String string) {
			this.waitForPage().until(ExpectedConditions.elementToBeClickable(pageObjs.Select_dropdown));
//			PopupAlertMessageBoxModifiable("Here we select the dropdown"+" "+string);
			this.pageObjs.select_drpdownbyValue(string);
		
	}

	public void enterdescription(String description) {
//		PopupAlertMessageBoxModifiable("Here we enter the description");
		pageObjs.EnterText(pageObjs.Message_box, description);
		
	}

	public void selectCheckboxByValue(String string) {
//		PopupAlertMessageBoxModifiable("Here we select the checkbox"+" "+string);
		pageObjs.checkboxByValue(pageObjs.Select_checkbox, string);
	}

	public void selectradioByValue(String string) {
//		PopupAlertMessageBoxModifiable("Here we select the radio button"+" "+string);
		pageObjs.checkboxByValue(pageObjs.Select_radio, string);
		
	}
	
	public void send_demo_message_popup(String string) {
		PopupAlertMessageBoxModifiable(string);
		
	}

	public void assertFailedtoSend() {
		this.waitForPage().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='wpcf7-f3093-p1979-o1']/form/div[@class='wpcf7-response-output']")));
//		PopupAlertMessageBoxModifiable("Here we confirm that the Validation error message appears as we gave an invalid email");
		this.loadPageObjects();
		Assert.assertTrue("Error is not displayed", pageObjs.formSentConfirmation.isDisplayed());
		Assert.assertEquals("Validation errors occurred. Please confirm the fields and submit it again.", pageObjs.formSentConfirmation.getText());
		System.out.println("Failed to send email message - " + pageObjs.formSentConfirmation.getText());
	}
	
	public void assertSuccessMessage() {
		this.waitForPage().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='wpcf7-f3093-p1979-o1']/form/div[@class='wpcf7-response-output']")));
		this.loadPageObjects();
		Assert.assertTrue("Successfully email sent message is not displayed", pageObjs.formSentConfirmation.isDisplayed());
		Assert.assertEquals("Your message was sent successfully. Thanks.", pageObjs.formSentConfirmation.getText());
		System.out.println("Success message - " + pageObjs.formSentConfirmation.getText());
	}


	public void assertCTC_address_section() {
		this.waitForPage().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='wpb_wrapper']/p)[2]")));
		this.loadPageObjects();
		Assert.assertTrue("Company Address Text is not displyed on Contact us page.", pageObjs.Address.isDisplayed());
		
	}


	public void assertInvalidEmailMsg(String emailErr) {
		driver.manage().window().setPosition(new Point(0,0));
		this.waitForPage().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='wpb_wrapper']/p)[2]")));
//		PopupAlertMessageBoxModifiable("Here we enter an invalid email address and verfify that it has alerted the address is invalid");
		this.loadPageObjects();
		Assert.assertTrue("Entered invalid email address message was not displayed.", pageObjs.Email_Err_Tip.isDisplayed());
		Assert.assertEquals(pageObjs.Email_Err_Tip.getText(), emailErr);
		
	}
	
}
