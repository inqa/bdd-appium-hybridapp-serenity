@my
Feature: Help dialog

  @Release:Release-1
  Scenario: At any time, the user can open the Help and get useful information
    Given the app is running
    When the user opens the help
    Then the help displays
    And the help shows "Press the Play button to get started"
    And the help shows "The main window will display a location using Google Street View. You have to work out where you are"

  @Release:Release-2
  Scenario: When the help is open, it can be closed
    Given the app is running
    When the user opens the help
    And the user closes the help
    Then the help does not display
