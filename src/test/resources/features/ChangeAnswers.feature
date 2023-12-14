@Exclusions

Feature: Changing Answers Feature

  Scenario: Changing the move date for moving to a different country exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs in as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers yes on the move-country page
    And the user selects Austria on the eu-country page
    And the user enters today for move-date
    And the user adds ATU12345678 on the tax-number page
    Then the user is on the check-your-answers page
    When the user selects the change link for move-date
    And the user amends to tomorrow for move-date
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the successful page

  Scenario: Changing the tax number for moving to a different country exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs in as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers yes on the move-country page
    And the user selects Slovakia on the eu-country page
    And the user enters today for move-date
    And the user adds SK1234567890 on the tax-number page
    Then the user is on the check-your-answers page
    When the user selects the change link for tax-number
    And the user amends to SK1234123490 on the tax-number page
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the successful page

  Scenario: Changing the country for moving to a different country exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs in as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user answers yes on the move-country page
    And the user selects Poland on the eu-country page
    And the user enters today for move-date
    And the user adds PL1234567890 on the tax-number page
    Then the user is on the check-your-answers page
    When the user selects the change link for eu-country
    Then the user reselects Finland on the eu-country page
#    Awaiting dev work to redirect to tax number
#    And the user adds FI12345678 on the tax-number page
    Then the user is on the check-your-answers page
    Then the user presses the continue button
    And the user is on the successful page

