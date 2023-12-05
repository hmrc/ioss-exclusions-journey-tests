@Exclusions @Accessibility

Feature: Exclusions Feature

  @ZAP
  Scenario: Moving to a different country exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs in as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers yes on the move-country page
    And the user selects Hungary on the eu-country page
    And the user enters today for move-date
    And the user adds HU12345678 on the tax-number page
    Then the user is on the check-your-answers page



