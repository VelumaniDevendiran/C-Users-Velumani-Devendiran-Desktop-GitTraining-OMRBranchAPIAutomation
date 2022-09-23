@Login
Feature: Login Module API automation

  Scenario: Get User logtoken from login endpoint
    Given User add header
    And User add basic authentication for login
    When User send "POST" request for login endpoint
    Then User verify the status coe is 200
    And User verify the login response body firstName present as "Velumani" and get the logtoken saved
