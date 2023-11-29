/*
 * Copyright 2023 HM Revenue & Customs
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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import uk.gov.hmrc.test.ui.pages.{AuthPage, CommonPage}

class ExclusionsStepDef extends BaseStepDef {

  Given("the user accesses the IOSS Exclusions service") { () =>
    CommonPage.goToExclusionsJourney()
  }

  Given(
    "^the user signs in as an Organisation with VRN (.*) and IOSS Number (.*)$"
  ) { (vrn: String, iossNumber: String) =>
    AuthPage.loginUsingAuthorityWizard(vrn, iossNumber)
  }

  Given("the user is at the beginning of the signed in IOSS Exclusions journey") { () =>
    CommonPage.checkJourneyUrl()
  }

}
