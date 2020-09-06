package org.itstep.selenium.framework.step;

import org.itstep.selenium.framework.reporter.Reporter;
import org.itstep.selenium.framework.ui.browser.Browser;
import org.itstep.selenium.product.yandex.page.LoginPage;
import org.itstep.selenium.product.yandex.page.MainPage;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.ScenarioType;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.testng.Assert;

public class LoginTestStep {

  private MainPage mainPage;
  private LoginPage loginPage;

  @BeforeScenario(uponType = ScenarioType.ANY)
  public void beforeScenario() {
    System.setProperty("test.selenium.baseUrl", "https://yandex.by/");
    System.setProperty("test.selenium.driversPath", "external_resources/drivers/");
    System.setProperty("test.selenium.browserName", "chrome");
    System.setProperty("test.selenium.implicitWait", "2");
    System.setProperty("test.selenium.reportPath", "external_resources/report/html/");
    System.setProperty("test.selenium.screenshotePath", "external_resources/report/screenshotes/");
    Reporter.getReporter();
    Reporter.startTest("BDD", "BDD");
  }

  @Given("I open yandex page")
  public void givenIOpenMainPage() {
    mainPage = MainPage.open();
  }

  @When("I log in using not existing $login and $password")
  public void whenILogInUsingWrongCreds(@Named("login") String login, @Named("password") String password) {
    loginPage = mainPage.clickSingInButton()
        .typeLogin(login)
        .clickSingInButton()
        .typePassword(password)
        .clickSingInButton();
  }

  @Then("I have an error $errorMessage")
  public void iAmNotLogged(@Named("$errorMessage") String errorMessage) {
    String actualErrorMessageText = loginPage.getErrorMessageText();
    Assert.assertEquals(actualErrorMessageText, errorMessage, "Verify Error message text");
  }

  @AfterScenario(uponType = ScenarioType.ANY)
  public void afterScenario() {
    Browser.close();
  }
}
