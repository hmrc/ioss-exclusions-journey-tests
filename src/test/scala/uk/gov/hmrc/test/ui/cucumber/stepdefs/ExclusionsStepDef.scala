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

import org.junit.Assert
import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.{AuthPage, CommonPage}

import java.time.LocalDate

class ExclusionsStepDef extends BaseStepDef {

  Given("the user accesses the IOSS Exclusions service") { () =>
    CommonPage.goToExclusionsJourney()
  }

  Given("the user accesses the IOSS Returns service") { () =>
    CommonPage.goToReturnsDashboard()
  }

  Given(
    "^the user signs into (exclusions|returns) as an Organisation with VRN (.*) and IOSS Number (.*)$"
  ) { (service: String, vrn: String, iossNumber: String) =>
    AuthPage.loginUsingAuthorityWizard(vrn, iossNumber, service)
  }

  When("""^the user answers (yes|no) on the (.*) page$""") { (data: String, url: String) =>
    CommonPage.checkUrl(url)
    CommonPage.selectAnswer(data)
  }

  When("""^the user (selects|reselects) (.*) on the (.*) page$""") { (select: String, data: String, page: String) =>
    CommonPage.checkUrl(page)
    if (select == "reselects") {
      CommonPage.clearDropdown
    }
    CommonPage.selectValueAutocomplete(data)
  }

  When("^the user (enters|amends to) (today|tomorrow) for (.*)$") { (mode: String, dateEntered: String, url: String) =>
    val date = {
      if (dateEntered == "today") {
        LocalDate.now()
      } else {
        LocalDate.now().plusDays(1)
      }
    }
    CommonPage.checkUrl(url)
    if (mode == "amends to") {
      CommonPage.clearDate()
    }
    CommonPage.enterDate(
      date.getDayOfMonth.toString,
      date.getMonthValue.toString,
      date.getYear.toString
    )
    CommonPage.clickContinue()
  }

  When("""^the user (adds|amends to) (.*) on the (.*) page$""") { (mode: String, data: String, url: String) =>
    CommonPage.checkUrl(url)
    if (mode == "amends to") {
      CommonPage.clearData
    }
    CommonPage.enterData("value", data)
    CommonPage.clickContinue()
  }

  Then("""^the user is on the (.*) page$""") { (url: String) =>
    CommonPage.checkUrl(url)
  }

  Then("""^the user selects the change link for (.*)$""") { (link: String) =>
    CommonPage.selectLink(s"$link\\?waypoints\\=check-your-answers")
  }

  Then("""^the user presses the continue button$""") { () =>
    CommonPage.clickContinue()
  }

  Then("""^the user clicks on the sign out link$""") { () =>
    CommonPage.selectLink("\\/account\\/sign-out-survey")
  }

  Then("""^the user clicks on the Cancel your request to leave link$""") { () =>
    driver.findElement(By.id("cancel-your-request-to-leave")).click()
  }

  Then("""^the user clicks on the Leave this service link$""") { () =>
    driver.findElement(By.id("leave-scheme")).click()
  }

  Then("""^the link to cancel the self exclusion is not displayed on the dashboard$""") { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertFalse(htmlBody.contains("Cancel your request to leave"))
  }

  Then("""^the link to Leave this service is not displayed on the dashboard$""") { () =>
    val htmlBody = driver.findElement(By.tagName("body")).getText
    Assert.assertFalse(htmlBody.contains("Leave this service"))
  }

  When("""^the user manually navigates to the cancel exclusion link$""") { () =>
    CommonPage.goToCancelExclusion()
  }

}
