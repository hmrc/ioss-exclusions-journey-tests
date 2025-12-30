/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.specs

import uk.gov.hmrc.ui.pages.{Auth, Exclusion}

class ReversalsSpec extends BaseSpec {

  private val exclusion = Exclusion
  private val auth      = Auth

  Feature("Reversal journeys") {

    Scenario("Reversing a self exclusion for trader moving country") {

      Given("the trader accesses the IOSS Returns Service")
      auth.goToAuthJourney()
      auth.loginUsingAuthorityWizard("user", "reversalMoveCountry", "100000001")
      exclusion.checkReturnsJourneyUrl()

      When("the trader clicks on the Cancel your request to leave link")
      exclusion.selectLink("cancel-your-request-to-leave")

      Then("the trader has been redirected to the exclusions service to cancel their request to leave")
      exclusion.checkJourneyUrl("cancel-leave-scheme")

      And("the trader selects yes on the cancel-leave-scheme page")
      exclusion.answerRadioButton("yes")

      Then("the trader is on the successful acknowledgement page")
      exclusion.checkJourneyUrl("cancel-leave-scheme-complete")

    }

    Scenario("Reversing a self exclusion for trader voluntarily leaving service") {

      Given("the trader accesses the IOSS Returns Service")
      auth.goToAuthJourney()
      auth.loginUsingAuthorityWizard("user", "reversalVoluntary", "100000001")
      exclusion.checkReturnsJourneyUrl()

      When("the trader clicks on the Cancel your request to leave link")
      exclusion.selectLink("cancel-your-request-to-leave")

      Then("the trader has been redirected to the exclusions service to cancel their request to leave")
      exclusion.checkJourneyUrl("cancel-leave-scheme")

      And("the trader selects yes on the cancel-leave-scheme page")
      exclusion.answerRadioButton("yes")

      Then("the trader is on the successful acknowledgement page")
      exclusion.checkJourneyUrl("cancel-leave-scheme-complete")

    }

    Scenario("Reversing a self exclusion for trader no longer supplying eligible goods") {

      Given("the trader accesses the IOSS Returns Service")
      auth.goToAuthJourney()
      auth.loginUsingAuthorityWizard("user", "reversalNoLongerSelling", "100000001")
      exclusion.checkReturnsJourneyUrl()

      When("the trader clicks on the Cancel your request to leave link")
      exclusion.selectLink("cancel-your-request-to-leave")

      Then("the trader has been redirected to the exclusions service to cancel their request to leave")
      exclusion.checkJourneyUrl("cancel-leave-scheme")

      And("the trader selects yes on the cancel-leave-scheme page")
      exclusion.answerRadioButton("yes")

      Then("the trader is on the successful acknowledgement page")
      exclusion.checkJourneyUrl("cancel-leave-scheme-complete")
    }

    Scenario("Trader cannot reverse a self exclusion if the effective exclusion date is in the past") {

      Given("the trader accesses the IOSS Returns Service")
      auth.goToAuthJourney()
      auth.loginUsingAuthorityWizard("user", "exclusionPast", "100000001")
      exclusion.checkReturnsJourneyUrl()

      When("the trader manually navigates to the cancel exclusion link")
      exclusion.goToReversalsJourney()

      Then("the user is on the cancel-leave-scheme-error page")
      exclusion.checkJourneyUrl("cancel-leave-scheme-error")
    }

    Scenario("Trader cannot reverse an exclusion that is not one of the self exclusion reasons") {

      Given("the trader accesses the IOSS Returns Service")
      auth.goToAuthJourney()
      auth.loginUsingAuthorityWizard("user", "nonSelfExclusion", "100000001")
      exclusion.checkReturnsJourneyUrl()

      When("the trader manually navigates to the cancel exclusion link")
      exclusion.goToReversalsJourney()

      Then("the user is on the cancel-leave-scheme-error page")
      exclusion.checkJourneyUrl("cancel-leave-scheme-error")
    }

    Scenario("Trader who has reversed a self-exclusion can do another self-exclusion") {

      Given("the trader accesses the IOSS Exclusions Service")
      auth.goToAuthJourney()
      auth.loginUsingAuthorityWizard("user", "reversedExclusion", "100000001")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")

      When("the trader selects no on the moving-to-an-eu-country page")
      exclusion.answerRadioButton("no")

      And("the trader selects no on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods")
      exclusion.answerRadioButton("no")

      And("the trader selects yes on the leave-scheme page")
      exclusion.checkJourneyUrl("leave-scheme")
      exclusion.answerRadioButton("yes")

      And("the trader enters today's date on the stopped-using-service-date page")
      exclusion.checkJourneyUrl("stopped-using-service-date")
      exclusion.enterDate("today")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the leave-request-received page")
      exclusion.checkJourneyUrl("leave-request-received")
    }

    Scenario("Failure from ETMP when reversing a self exclusion") {

      Given("the trader accesses the reversal journey within the IOSS Exclusions service")
      auth.goToAuthJourney()
      auth.loginUsingAuthorityWizard("user", "reversalFailure", "100000001")

      And("the trader selects yes on the cancel-leave-scheme page")
      exclusion.checkJourneyUrl("cancel-leave-scheme")
      exclusion.answerRadioButton("yes")

      Then("the user is on the cancel-leave-scheme-submission-failure page")
      exclusion.checkJourneyUrl("cancel-leave-scheme-submission-failure")
    }
  }
}
