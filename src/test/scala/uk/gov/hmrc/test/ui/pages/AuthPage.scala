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

package uk.gov.hmrc.test.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Select
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object AuthPage extends BasePage {

  def loginUsingAuthorityWizard(
    vrn: String,
    iossNumber: String,
    service: String
  ): Unit = {

    val stubUrl: String = TestConfiguration.url("auth-login-stub") + "/gg-sign-in"
    driver.getCurrentUrl should startWith(stubUrl)

    driver.findElement(By.id("redirectionUrl")).clear()
    if (service == "returns") {
      driver.findElement(By.id("redirectionUrl")).sendKeys(TestConfiguration.url("ioss-returns-frontend"))
    } else {
      driver.findElement(By.id("redirectionUrl")).sendKeys(TestConfiguration.url("ioss-exclusions-frontend"))
    }

    val selectAffinityGroup = new Select(driver.findElement(By.id("affinityGroupSelect")))
    selectAffinityGroup.selectByValue("Organisation")

    driver.findElement(By.id("enrolment[0].name")).sendKeys("HMRC-MTD-VAT")
    driver
      .findElement(By.id("input-0-0-name"))
      .sendKeys("VRN")
    driver
      .findElement(By.id("input-0-0-value"))
      .sendKeys(vrn)

    if (iossNumber != "none") {
      driver.findElement(By.id("enrolment[1].name")).sendKeys("HMRC-IOSS-ORG")
      driver
        .findElement(By.id("input-1-0-name"))
        .sendKeys("IOSSNumber")
      driver
        .findElement(By.id("input-1-0-value"))
        .sendKeys(iossNumber)
    }
    driver.findElement(By.cssSelector("Input[value='Submit']")).click()
  }

}
