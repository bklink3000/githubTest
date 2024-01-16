#Author: Sri Tanguturi
#Email: sri.tanguturi@usa-ctc.com
#Keywords Summary : CTC Demo
@tag
Feature: CTC-QA Automation Demo

  Background: Launch browser
    Given Launch browser
    And Navigate to CTC contact us page

  @demo
  Scenario: Validate the error messages displayed on the Contact us page
    And Click on Send Email button
    Then Verify that below error message is displayed for required fields
      | "Please fill the required field." |
    Then take a screenshot
    And I enter Name as "Demo Automation" in the name field
    And I enter Email as "Invalid Email" in the email field
    And Click on Send Email button
    And Verify that "Email address seems invalid." message was displayed
    Then Verify that Failed to send your message text is displayed
    Then take a screenshot
    And Quit the driver

  @demo
  Scenario Outline: Fill the contact us form and submit
    When The page is loaded, I check for CTC address is displayed
    And I enter Name as "<name>" in the name field
    And I enter Email as "<email>" in the email field
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
      | name      | email                  | customer                             | interested_in     | contact |
      | Brian CTC | testemail1@usa-ctc.com | Current Customer                     | Cybersecurity     | Phone   |
      | Sri CTC   | testemail2@usa-ctc.com | Interested in becoming a CTC partner | Automated Testing | Email   |
