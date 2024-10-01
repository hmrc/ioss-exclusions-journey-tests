@Exclusions

Feature: Changing Answers Feature

  Scenario: Changing the move date for moving to a different country exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers yes on the moving-to-an-eu-country page
    And the user selects Austria on the which-eu-country page
    And the user enters today for move-date
    And the user adds ATU12345678 on the eu-vat-number page
    Then the user is on the check-your-answers page
    When the user selects the change link for move-date
    And the user amends to tomorrow for move-date
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the leave-request-received page
    And the user clicks on the sign out link

  Scenario: Changing the tax number for moving to a different country exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers yes on the moving-to-an-eu-country page
    And the user selects Slovakia on the which-eu-country page
    And the user enters today for move-date
    And the user adds SK1234567890 on the eu-vat-number page
    Then the user is on the check-your-answers page
    When the user selects the change link for eu-vat-number
    And the user amends to SK1234123490 on the eu-vat-number page
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the leave-request-received page
    And the user clicks on the sign out link

  Scenario: Changing the country for moving to a different country exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers yes on the moving-to-an-eu-country page
    And the user selects Poland on the which-eu-country page
    And the user enters today for move-date
    And the user adds PL1234567890 on the eu-vat-number page
    Then the user is on the check-your-answers page
    When the user selects the change link for which-eu-country
    Then the user reselects Finland on the which-eu-country page
    And the user adds FI12345678 on the eu-vat-number page
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the leave-request-received page
    And the user clicks on the sign out link

  Scenario: Changing the move date for stopped selling eligible goods exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers no on the moving-to-an-eu-country page
    And the user answers yes on the stop-selling-goods page
    And the user enters today for stopped-selling-goods-date
    Then the user is on the check-your-answers page
    When the user selects the change link for stopped-selling-goods-date
    And the user amends to tomorrow for stopped-selling-goods-date
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the leave-request-received page
    And the user clicks on the sign out link

  Scenario: Changing the move date for voluntary exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers no on the moving-to-an-eu-country page
    And the user answers no on the stop-selling-goods page
    And the user answers yes on the leave-scheme page
    And the user enters today for stopped-using-service-date
    Then the user is on the check-your-answers page
    When the user selects the change link for stopped-using-service-date
    And the user amends to tomorrow for stopped-using-service-date
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the leave-request-received page
    And the user clicks on the sign out link

  Scenario: Changing from moving to a different country to stopped selling eligible goods exclusion journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers yes on the moving-to-an-eu-country page
    And the user selects Poland on the which-eu-country page
    And the user enters today for move-date
    And the user adds PL1234567890 on the eu-vat-number page
    Then the user is on the check-your-answers page
    When the user selects the change link for moving-to-an-eu-country
    Then the user answers no on the moving-to-an-eu-country page
    And the user answers yes on the stop-selling-goods page
    And the user enters today for stopped-selling-goods-date
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the leave-request-received page
    And the user clicks on the sign out link

  Scenario: Changing from moving to a different country to voluntary exclusion journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers yes on the moving-to-an-eu-country page
    And the user selects Poland on the which-eu-country page
    And the user enters today for move-date
    And the user adds PL1234567890 on the eu-vat-number page
    Then the user is on the check-your-answers page
    When the user selects the change link for moving-to-an-eu-country
    Then the user answers no on the moving-to-an-eu-country page
    And the user answers no on the stop-selling-goods page
    And the user answers yes on the leave-scheme page
    And the user enters today for stopped-using-service-date
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the leave-request-received page
    And the user clicks on the sign out link

  Scenario: Changing from stopped selling eligible goods to moving to another country exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers no on the moving-to-an-eu-country page
    And the user answers yes on the stop-selling-goods page
    And the user enters today for stopped-selling-goods-date
    Then the user is on the check-your-answers page
    When the user selects the change link for moving-to-an-eu-country
    Then the user answers yes on the moving-to-an-eu-country page
    And the user selects Greece on the which-eu-country page
    And the user enters today for move-date
    And the user adds EL123456789 on the eu-vat-number page
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the leave-request-received page
    And the user clicks on the sign out link

  Scenario: Changing from stopped selling eligible goods to voluntary exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers no on the moving-to-an-eu-country page
    And the user answers yes on the stop-selling-goods page
    And the user enters today for stopped-selling-goods-date
    Then the user is on the check-your-answers page
    When the user selects the change link for stop-selling-goods
    And the user answers no on the stop-selling-goods page
    And the user answers yes on the leave-scheme page
    And the user enters today for stopped-using-service-date
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the leave-request-received page
    And the user clicks on the sign out link

  Scenario: Changing from voluntary to moving to another country exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers no on the moving-to-an-eu-country page
    And the user answers no on the stop-selling-goods page
    And the user answers yes on the leave-scheme page
    And the user enters today for stopped-using-service-date
    Then the user is on the check-your-answers page
    When the user selects the change link for moving-to-an-eu-country
    Then the user answers yes on the moving-to-an-eu-country page
    And the user selects Slovenia on the which-eu-country page
    And the user enters today for move-date
    And the user adds SI12345678 on the eu-vat-number page
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the leave-request-received page
    And the user clicks on the sign out link

  Scenario: Changing from voluntary to stopped selling eligible goods exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs into exclusions as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers no on the moving-to-an-eu-country page
    And the user answers no on the stop-selling-goods page
    And the user answers yes on the leave-scheme page
    And the user enters today for stopped-using-service-date
    Then the user is on the check-your-answers page
    When the user selects the change link for stop-selling-goods
    And the user answers yes on the stop-selling-goods page
    And the user enters today for stopped-selling-goods-date
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the leave-request-received page
    And the user clicks on the sign out link


