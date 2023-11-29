@Exclusions @Accessibility

Feature: Exclusions Feature

  @ZAP
  Scenario: User can access Exclusions journey
    Given the user accesses the IOSS Exclusions service
    When the user signs in as an Organisation with VRN 100000001 and IOSS Number IM9001234567
    Then the user is at the beginning of the signed in IOSS Exclusions journey


