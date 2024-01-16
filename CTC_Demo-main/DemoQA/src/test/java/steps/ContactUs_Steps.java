package steps;

import io.cucumber.java.Before;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import io.cucumber.core.api.Scenario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import module.ContactUS_code;

public class ContactUs_Steps {

	ContactUS_code code = new ContactUS_code();

	private Scenario myScenario;

	@Before
	public void setup(Scenario s) {
		this.myScenario = s;
	}

	@Given("Launch browser")
	public void launch_browser() {
		code.BrowserInitiate(myScenario);

	}

	@Given("Navigate to CTC contact us page")
	public void navigate_to_CTC_contact_us_page() {

		code.NavigateToContactUsPage();
		this.sleep();
	}

	@Given("Click on Send Email button")
	public void click_on_Send_Email_button() {
		this.i_take_a_screenshot();
		code.ClickSendEmailButton();
		this.sleep();
		
	}

	@Then("Verify that below error message is displayed for required fields")
	public void verify_that_below_error_message_is_displayed_for_required_fields(DataTable dataTable) {

		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.
		String test = dataTable.cell(0, 0);
		System.out.println(test);
		try {
			code.assertErrorMessage(test);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	@Then("Verify that {string} message was displayed")
	public void verify_that_message_was_displayed(String emailErr) {
		code.assertInvalidEmailMsg(emailErr);
	}

	@Then("Send Popup that {string}")
	public void send_demo_popup(String string) {
		code.send_demo_message_popup(string);
	}
	    
	@When("The page is loaded, I check for CTC address is displayed")
	public void the_page_is_loaded_I_check_for_CTC_address_is_displayed() {
		code.assertCTC_address_section();

	}

	@When("I enter Name as {string} in the name field")
	public void i_enter_Name_as_in_the_name_field(String string) {
		code.enterName(string);
	}

	@When("I enter Email as {string} in the email field")
	public void i_enter_Email_as_in_the_email_field(String string) {
		code.enterEmail(string);
	}

	@When("I enter the Subject as {string}")
	public void i_enter_the_Subject_as(String string) {
		code.enterSubject(string);
	}

	@When("I enter the description in the Your Message field")
	public void i_enter_the_description_in_the_Your_Message_field(DataTable dataTable) {

		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.

		String description = dataTable.cell(0, 0);
		code.enterdescription(description);
	}

	@When("I select a value {string} from dropdown field")
	public void i_select_a_value_from_dropdown_field(String string) {
		code.selectDropDownBylist(string);

	}

	@When("I select {string} checkbox")
	public void i_select_checkbox(String string) {
		code.selectCheckboxByValue(string);
	}

	@When("I select {string} radio button")
	public void i_select_radio_button(String string) {
		code.selectradioByValue(string);
	}

	@Then("Verify that Failed to send your message text is displayed")
	public void verify_that_Failed_to_send_your_message_text_is_displayed() {
		code.assertFailedtoSend();
	}
	
	@Then("Verify that your message was sent successfully confirmation is displayed")
	public void verify_that_your_message_was_sent_successfully_confirmation_is_displayed() {
	    code.assertSuccessMessage();
	}

	@And("^Quit the driver")
	public void quitDriver() {
		code.quitBrowser();
	}

	@Then("^take a screenshot$")
	public void i_take_a_screenshot() {
		try {
			myScenario.write("Current Page URL is " + ContactUS_code.getDriver().getCurrentUrl());
			byte[] screenshot = ((TakesScreenshot) ContactUS_code.getDriver()).getScreenshotAs(OutputType.BYTES);
			myScenario.embed(screenshot, "image/png"); // Stick it in the report
		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			System.out.println(somePlatformsDontSupportScreenshots.getMessage());
		} catch (ClassCastException cce) {
			cce.printStackTrace();
		}
	}

	//The method to stop the browser for certain of time in Milliseconds. Thread.sleep will make the execution to stop 
	//for specified amount of time.
	public void sleep() {
		try {
			Thread.sleep(1000);

		} catch (InterruptedException e) { 
			e.printStackTrace();

		}

	}
}
