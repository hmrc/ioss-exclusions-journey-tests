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

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.scalatest.matchers.should.Matchers.should
import uk.gov.hmrc.configuration.TestEnvironment

object Auth extends BasePage {

  private val authUrl: String       = TestEnvironment.url("auth-login-stub") + "/auth-login-stub/gg-sign-in"
  private val exclusionsUrl: String =
    TestEnvironment.url("ioss-exclusions-frontend") + "/pay-vat-on-goods-sold-to-eu/leave-import-one-stop-shop"
  private val returnsUrl: String    =
    TestEnvironment.url(
      "ioss-returns-frontend"
    ) + "/pay-vat-on-goods-sold-to-eu/import-one-stop-shop-returns-payments"

  def loginUsingAuthorityWizard(user: String, journey: String, vrn: String): Unit = {

    if (journey == "exclusions" || journey == "exclusionFailure") {
      sendKeys(By.name("redirectionUrl"), exclusionsUrl)
    } else {
      sendKeys(By.name("redirectionUrl"), returnsUrl)
    }

    selectByValue(By.id("affinityGroupSelect"), "Organisation")

    if (user == "assistant") {
      selectByValue(By.id("credential-role-select"), "Assistant")
    }

    sendKeys(By.id("enrolment[0].name"), "HMRC-MTD-VAT")
    sendKeys(By.id("input-0-0-name"), "VRN")
    sendKeys(By.id("input-0-0-value"), vrn)

    if (journey != "notRegistered") {

      sendKeys(By.id("enrolment[1].name"), "HMRC-IOSS-ORG")
      sendKeys(By.id("input-1-0-name"), "IOSSNumber")

      val iossNumber =
        journey match {
          case "alreadyExcluded" || "reversalMoveCountry => "IM9009999995"
          case "exclusionFailure" => "IM9002222222"
          case "reversalNoLongerSelling" => "IM9009999997"
          case "reversalVoluntary" => "IM9009999996"
          case "exclusionPast" => "IM9009999994"
          case _ => "IM9001234567"
        }

      sendKeys(By.id("input-1-0-value"), iossNumber)
    }

    click(By.cssSelector("Input[value='Submit']"))

  }

  def goToAuthJourney(): Unit = {
    get(authUrl)
    fluentWait.until(ExpectedConditions.urlContains(authUrl))
  }

}
