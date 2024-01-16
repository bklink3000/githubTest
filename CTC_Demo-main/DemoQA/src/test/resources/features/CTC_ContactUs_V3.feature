#Author: Sri Tanguturi
#Email: sri.tanguturi@usa-ctc.com
#Keywords Summary : CTC Demo
@tag
Feature: CTC-QA Automation Demo

  Background: Launch browser
    Given Launch browser
    And Navigate to CTC contact us page
    Then Send Popup that "We first navigate to the Contact Us page"

  @demo
  Scenario: Validate the error messages displayed on the Contact us page
    And Click on Send Email button
    Then Send Popup that "Clicking on the Send Email button with no fields completed to test for the error"
    Then Verify that below error message is displayed for required fields
      | "Please fill the required field." |
    Then Send Popup that "We see the error and will continue with the test"
    Then take a screenshot
    And I enter Name as "Demo Automation" in the name field
    Then Send Popup that "We will now enter an invalid email to test for the error"
    And I enter Email as "Invalid Email" in the email field
    Then Send Popup that "We see the invalid email error and will continue with the test"
    Then Send Popup that "We will now click the Send Email button to verify we see the Please fill in the required field error"
    And Click on Send Email button
    And Verify that "Email address seems invalid." message was displayed
    Then Verify that Failed to send your message text is displayed
    Then Send Popup that "The error was found, The first test scenario is complete"
    Then take a screenshot
    And Quit the driver

  @demo
  Scenario Outline: Fill the contact us form and submit
    When The page is loaded, I check for CTC address is displayed
    Then Send Popup that "Here we enter the name"
    And I enter Name as "<name>" in the name field
    Then Send Popup that "Here we enter the email"
    And I enter Email as "<email>" in the email field
    Then Send Popup that "Here we enter the subject"
    And I enter the Subject as "CTC Demo"
    And I select a value "<customer>" from dropdown field
    And I select "<interested_in>" checkbox
    And I select "<contact>" radio button
    And I enter the description in the Your Message field
      | "The purpose of this demo is to create the possiblities of what QA automation can achieve during this demonstration" |
    And Click on Send Email button
    Then Verify that your message was sent successfully confirmation is displayed
    And Quit the driver

    Examples: 
      | name      | email                  | customer         | interested_in | contact |
      | Brian CTC | testemail1@usa-ctc.com | Current Customer | Cybersecurity | Phone   |
#      | Sri CTC   | testemail2@usa-ctc.com | Interested in becoming a CTC partner | Automated Testing | Email   |
