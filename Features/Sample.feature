Feature: Validate login based on different Role

  @TC_RoleBased_Login
  Scenario: Check successful Login
    Given Launch Pega_Local_Server URL
    When Enter UserID & Password of operator
    Then Validate Create option

  @TC_Create_New_Case
  Scenario: Check case ID
    Given Launch Pega_Local_Server URL
    When Enter UserID & Password of operator
    And Create New case & enter case details
    Then Validate Case ID
