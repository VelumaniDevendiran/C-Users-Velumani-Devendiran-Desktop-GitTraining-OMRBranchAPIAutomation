Feature: ChangeProfile Picture Module API automation

  @profilepic
  Scenario: Verify add new profilepic to the application through API
    Given User add header and bearer authrozation for accessing ProfilePic end points
    When User add request and bearer for "profile_picture"
    And User send "POST" request for Changeprofile picture end point
    Then User verify the status coe is 200
    And User verify the profile picture Change message "Profile updated Successfully"
