@Exclusions @Accessibility

Feature: Reversals Feature

  Scenario: Reversing a self exclusion for trader moving country
    Given the user accesses the IOSS Returns service
    When the user signs into returns as an Organisation with VRN 100000001 and IOSS Number IM9009999995
    Then the user clicks on the Cancel your request to leave link
    When the user answers yes on the cancel-leave-scheme page
    Then the user is on the cancel-leave-scheme-complete page

  Scenario: Reversing a self exclusion for trader no longer selling eligible goods
    Given the user accesses the IOSS Returns service
    When the user signs into returns as an Organisation with VRN 100000001 and IOSS Number IM9009999997
    Then the user clicks on the Cancel your request to leave link
    When the user answers yes on the cancel-leave-scheme page
    Then the user is on the cancel-leave-scheme-complete page

  Scenario: Reversing a self exclusion for trader leaving scheme voluntarily
    Given the user accesses the IOSS Returns service
    When the user signs into returns as an Organisation with VRN 100000001 and IOSS Number IM9009999996
    Then the user clicks on the Cancel your request to leave link
    When the user answers yes on the cancel-leave-scheme page
    Then the user is on the cancel-leave-scheme-complete page

  Scenario: Trader cannot reverse a self exclusion if the effective exclusion date is in the past
    Given the user accesses the IOSS Returns service
    When the user signs into returns as an Organisation with VRN 100000001 and IOSS Number IM9009999994
    Then the link to cancel the self exclusion is not displayed on the dashboard
    When the user manually navigates to the cancel exclusion link
#    Should give an error

  Scenario: Trader cannot reverse an exclusion that is not one of the self exclude reasons
    Given the user accesses the IOSS Returns service
    When the user signs into returns as an Organisation with VRN 100000001 and IOSS Number IM9009999993
    Then the link to cancel the self exclusion is not displayed on the dashboard
    When the user manually navigates to the cancel exclusion link
#    Should give an error

