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
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait}
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object CommonPage extends BasePage {

  val exclusionsUrl: String = TestConfiguration.url("ioss-exclusions-frontend")

  def goToExclusionsJourney(): Unit =
    driver.navigate().to(exclusionsUrl)

  def checkJourneyUrl(): Unit =
    driver.getCurrentUrl should startWith(s"$exclusionsUrl/move-country")

  def checkUrl(url: String): Unit =
    driver.getCurrentUrl should startWith(s"${TestConfiguration.url("ioss-exclusions-frontend")}/$url")

  def selectAnswer(data: String): Unit = {
    data match {
      case "yes" => driver.findElement(By.id("value")).click()
      case "no"  => driver.findElement(By.id("value-no")).click()
      case _     => throw new Exception("Option doesn't exist")
    }
    CommonPage.clickContinue()
  }

  def clickContinue(): Unit =
    driver.findElement(By.id("continue")).click()

  def selectValueAutocomplete(data: String): Unit = {
    waitForElement(By.id("value"))
    enterData("value", data)
    clickContinue()
  }

  def waitForElement(by: By) =
    new FluentWait(driver).until {
      ExpectedConditions.presenceOfElementLocated(by)
    }

  def enterData(inputId: String, data: String): Unit =
    driver.findElement(By.id(inputId)).sendKeys(data)

  def enterDate(day: String, month: String, year: String): Unit = {
    driver.findElement(By.id("value.day")).sendKeys(day)
    driver.findElement(By.id("value.month")).sendKeys(month)
    driver.findElement(By.id("value.year")).sendKeys(year)

  }

}
