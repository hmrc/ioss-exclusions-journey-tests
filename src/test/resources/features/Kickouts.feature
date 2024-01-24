@Exclusions

Feature: Kickouts Feature
  @Accessibility
  Scenario: Failure when submitting moving to a different country exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 600000022 and IOSS Number IM9002222222
    Then the user answers yes on the move-country page
    And the user selects Hungary on the eu-country page
    And the user enters today for move-date
    And the user adds HU12345678 on the eu-vat-number page
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the submission-failure page
    And the user clicks on the sign out link

  Scenario: Failure when submitting stopped selling eligible goods to EU/NI exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 600000022 and IOSS Number IM9002222222
    Then the user answers no on the move-country page
    And the user answers yes on the stop-selling-goods page
    And the user enters today for stopped-selling-goods-date
    Then the user is on the submission-failure page
    And the user clicks on the sign out link

  Scenario: Failure when submitting voluntary exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 600000022 and IOSS Number IM9002222222
    Then the user answers no on the move-country page
    And the user answers no on the stop-selling-goods page
    And the user answers yes on the leave-scheme page
    And the user enters today for stopped-using-service-date
    Then the user is on the submission-failure page
    And the user clicks on the sign out link

  @Accessibility
  Scenario: A trader is unable to access the self exclude journey when already excluded
    Given the user accesses the IOSS Returns service
    When the user signs into returns as an Organisation with VRN 100000001 and IOSS Number IM9009999995
    Then the user manually navigates to the self exclude journey
    And the user is on the already-left-scheme-error page
    And the user clicks on the sign out link



