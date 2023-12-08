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

import java.time.LocalDate

class ExclusionsStepDef extends BaseStepDef {

  Given("the user accesses the IOSS Exclusions service") { () =>
    CommonPage.goToExclusionsJourney()
  }

  Given(
    "^the user signs in as an Organisation with VRN (.*) and IOSS Number (.*)$"
  ) { (vrn: String, iossNumber: String) =>
    AuthPage.loginUsingAuthorityWizard(vrn, iossNumber)
  }

  When("""^the user answers (yes|no) on the (.*) page$""") { (data: String, url: String) =>
    CommonPage.checkUrl(url)
    CommonPage.selectAnswer(data)
  }

  When("""^the user selects (.*) on the (.*) page$""") { (data: String, page: String) =>
    CommonPage.checkUrl(page)
    CommonPage.selectValueAutocomplete(data)
  }

  When("^the user enters today for (.*)$") { (url: String) =>
    val date = LocalDate.now()
    CommonPage.checkUrl(url)
    CommonPage.enterDate(
      date.getDayOfMonth.toString,
      date.getMonthValue.toString,
      date.getYear.toString
    )
    CommonPage.clickContinue()
  }

  When("""^the user adds (.*) on the (.*) page$""") { (data: String, url: String) =>
    CommonPage.checkUrl(url)
    CommonPage.enterData("value", data)
    CommonPage.clickContinue()
  }

  Then("""^the user is on the (.*) page$""") { (url: String) =>
    CommonPage.checkUrl(url)
  }

}
