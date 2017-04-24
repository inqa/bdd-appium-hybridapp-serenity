@no
Feature: App starts up and shows initial launch screen
  An example rubbish feature file to be deleted soon

  Scenario: On first usage, the initial launch screen says "Welcome, Get started by clicking below"
    Given WhereAmIAgain is running
    When I start the game
    Then a street view displays

