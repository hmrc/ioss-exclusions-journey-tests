@Exclusions @Accessibility @ZAP

Feature: Exclusions Feature

  Scenario: Moving to a different country exclusions journey
    Given the user accesses the IOSS Returns service
    When the user signs into returns as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    And the user clicks on the Leave this service link
    Then the user answers yes on the move-country page
    And the user selects Hungary on the eu-country page
    And the user enters today for move-date
    And the user adds HU12345678 on the eu-vat-number page
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the successful page
    And the user clicks on the sign out link

  Scenario: Stopped selling eligible goods to EU/NI exclusions journey
    Given the user accesses the IOSS Returns service
    When the user signs into returns as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    And the user clicks on the Leave this service link
    Then the user answers no on the move-country page
    And the user answers yes on the stop-selling-goods page
    And the user enters today for stopped-selling-goods-date
    Then the user is on the successful page
    And the user clicks on the sign out link

  Scenario: Voluntary exclusions journey
    Given the user accesses the IOSS Returns service
    When the user signs into returns as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    And the user clicks on the Leave this service link
    Then the user answers no on the move-country page
    And the user answers no on the stop-selling-goods page
    And the user answers yes on the leave-scheme page
    And the user enters today for stopped-using-service-date
    Then the user is on the successful page
    And the user clicks on the sign out link

  Scenario: An assistant user can access the exclusions service
    Given the user accesses the IOSS Returns service
    When the assistant signs into returns as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    And the user clicks on the Leave this service link
    Then the user is on the move-country page


