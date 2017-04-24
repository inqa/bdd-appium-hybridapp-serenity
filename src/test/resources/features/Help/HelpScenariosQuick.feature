@my
Feature: Help dialog (2)

  @Release:Release-3
  Scenario: When the help is open, it can be closed (2)
    Given the app is running
    When the user opens the help
    And the user closes the help
    Then the help does not display
