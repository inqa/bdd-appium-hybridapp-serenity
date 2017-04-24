@my
Feature: App starts up and shows initial launch screen
  As the app launches, an initial screen should appear that provides the first-time user with some useful info on getting started

  @Release:Release-2
  Scenario: On first usage, the initial launch screen says "Welcome, Get started by clicking below"
    Given the app is running for the first time
    Then the initial page shows "Welcome! Get started by clicking below"

