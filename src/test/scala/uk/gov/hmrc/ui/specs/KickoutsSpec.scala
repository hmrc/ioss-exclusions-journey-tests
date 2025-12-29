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

class KickoutsSpec extends BaseSpec {

  private val exclusion = Exclusion
  private val auth      = Auth

  Feature("Kickout journeys") {

    Scenario("Failure when submitting exclusion for trader moving country") {

      Given("the trader accesses the IOSS Returns Service")
      auth.goToAuthJourney()
      auth.loginUsingAuthorityWizard("user", "exclusionFailure", "600000022")

      When("the trader selects yes on the moving-to-an-eu-country page")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")
      exclusion.answerRadioButton("yes")

      And("the trader selects Hungary on the which-eu-country page")
      exclusion.checkJourneyUrl("which-eu-country")
      exclusion.selectCountry("Hungary")

      And("the trader enters today's date on the move-date page")
      exclusion.checkJourneyUrl("move-date")
      exclusion.enterDate("today")

      And("the trader enters VAT number HU12345678 on the eu-vat-number page")
      exclusion.checkJourneyUrl("eu-vat-number")
      exclusion.enterVatNumber("HU12345678")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the failed submission page")
      exclusion.checkJourneyUrl("submission-failure")
    }

    Scenario("Failure when submitting exclusion for trader no longer selling eligible goods") {

      Given("the trader accesses the IOSS Returns Service")
      auth.goToAuthJourney()
      auth.loginUsingAuthorityWizard("user", "exclusionFailure", "600000022")

      When("the trader selects no on the moving-to-an-eu-country page")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")
      exclusion.answerRadioButton("no")

      And("the trader selects yes on the stop-selling-goods page")
      exclusion.checkJourneyUrl("stop-selling-goods")
      exclusion.answerRadioButton("yes")

      And("the trader enters today's date on the stopped-selling-goods-date page")
      exclusion.checkJourneyUrl("stopped-selling-goods-date")
      exclusion.enterDate("today")

      And("the trader continues on the check-your-answers page")
      exclusion.checkJourneyUrl("check-your-answers")
      exclusion.submit()

      Then("the trader is on the failed submission page")
      exclusion.checkJourneyUrl("submission-failure")
    }

    Scenario("Failure when submitting exclusion for voluntarily leaving the service") {

      Given("the trader accesses the IOSS Returns Service")
      auth.goToAuthJourney()
      auth.loginUsingAuthorityWizard("user", "exclusionFailure", "600000022")

      When("the trader selects no on the moving-to-an-eu-country page")
      exclusion.checkJourneyUrl("moving-to-an-eu-country")
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

      Then("the trader is on the failed submission page")
      exclusion.checkJourneyUrl("submission-failure")
    }

    Scenario("Excluded trader is not able to access self exclusion journey") {

      Given("the excluded trader accesses the IOSS Returns Service")
      auth.goToAuthJourney()
      auth.loginUsingAuthorityWizard("user", "alreadyExcluded", "100000001")
      exclusion.checkReturnsJourneyUrl()

      When("the trader manually navigates to the self-exclude journey")
      exclusion.goToExclusionsJourney()

      Then("the trader is on the already-left-scheme-error page")
      exclusion.checkJourneyUrl("already-left-scheme-error")

    }

    Scenario("A trader is unable to access the self exclude journey if they are not registered for IOSS") {

      Given("the non-registered trader accesses the IOSS Returns Service")
      auth.goToAuthJourney()
      auth.loginUsingAuthorityWizard("user", "notRegistered", "100000001")

      Then("the trader is on the cannot-use-not-registered page")
      exclusion.checkJourneyUrl("cannot-use-not-registered")

    }
  }
}
