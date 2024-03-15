Feature: Login to Family Core

  Scenario: User logs in with valid credentials
    Given user with username "jd_fc@yopmail.com" and password "jdPassword1"
    When user on log in page
    And user proceeds to log in
    Then user should be on verification page

  Scenario: User logs in with valid credentials and verification code
    Given user with username "jd_fc@yopmail.com" and password "jdPassword1"
    When user on log in page
    And user proceeds to log in
    And user gets verification code
    And user enters verification code
    And user select their family
    Then user should see verification