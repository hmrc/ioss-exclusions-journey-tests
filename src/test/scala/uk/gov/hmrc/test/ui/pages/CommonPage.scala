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

import org.openqa.selenium.{By, Keys}
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait}
import uk.gov.hmrc.test.ui.conf.TestConfiguration

object CommonPage extends BasePage {

  val exclusionsUrl: String = TestConfiguration.url("ioss-exclusions-frontend")
  val returnsUrl: String    = TestConfiguration.url("ioss-returns-frontend")

  def goToExclusionsJourney(): Unit =
    driver.navigate().to(exclusionsUrl)

  def goToPage(page: String): Unit =
    driver.navigate().to(s"$exclusionsUrl/$page")

  def goToReturnsDashboard(): Unit =
    driver.navigate().to(returnsUrl)

  def checkJourneyUrl(): Unit =
    driver.getCurrentUrl should startWith(s"$exclusionsUrl/moving-to-an-eu-country")

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

  def clickSubmit(): Unit =
    driver.findElement(By.id("submit")).click()

  def selectValueAutocomplete(data: String): Unit = {
    val inputId = "value"
    driver.findElement(By.id(inputId)).sendKeys(data)
    waitForElement(By.id(inputId))
    driver.findElement(By.cssSelector("li#value__option--0")).click()
    clickContinue()
  }

  def waitForElement(by: By) =
    new FluentWait(driver).until {
      ExpectedConditions.presenceOfElementLocated(by)
    }

  def enterData(inputId: String, data: String): Unit =
    driver.findElement(By.id(inputId)).sendKeys(data)

  def clearData(): Unit =
    driver.findElement(By.id("value")).clear()

  def clearDropdown(): Unit = {
    val input = driver.findElement(By.id("value")).getAttribute("value")
    if (input != null) {
      for (n <- input)
        driver.findElement(By.id("value")).sendKeys(Keys.BACK_SPACE)
    }
  }

  def enterDate(day: String, month: String, year: String): Unit = {
    driver.findElement(By.id("value.day")).sendKeys(day)
    driver.findElement(By.id("value.month")).sendKeys(month)
    driver.findElement(By.id("value.year")).sendKeys(year)
  }

  def selectLink(link: String): Unit =
    driver.findElement(By.cssSelector(s"a[href*=$link]")).click()

  def clearDate(): Unit = {
    driver.findElement(By.id("value.day")).clear()
    driver.findElement(By.id("value.month")).clear()
    driver.findElement(By.id("value.year")).clear()
  }

  def goToCancelExclusion(): Unit =
    driver.navigate().to(s"$exclusionsUrl/cancel-leave-scheme")

  def clickBackButton(): Unit =
    driver
      .navigate()
      .back()

}
