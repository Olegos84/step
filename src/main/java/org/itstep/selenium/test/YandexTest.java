package org.itstep.selenium.test;

import static org.itstep.selenium.product.yandex.common.ErrorMessage.INCORRECT_PASSWORD;

import org.itstep.selenium.product.yandex.page.LoginPage;
import org.itstep.selenium.product.yandex.page.MainPage;
import org.testng.annotations.Test;

/**
 * Класс для тестов
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 */
public class YandexTest extends BaseTest {

  /**
   * Тестовый метод, который идет на яндекс, пытается войти с неверными логином и паролем,
   * и проверяет сообщение об ошибке
   */
  @Test(testName = "testYandexLoginNegative",
      groups = {"testThatWork"},
      description = "Verify that user can not login with invalid credentials")
  public void testYandexLoginNegative() {

    reporter.reportStep("Open login page");
    MainPage mainPage = MainPage.open();

    String testLogin = "Aleh";
    String testPassword = "123456789";
    reporter.reportStep("Try to login", "login: " + testLogin, "password: " + testPassword);
    LoginPage loginPage = mainPage.clickSingInButton()
        .typeLogin(testLogin)
        .clickSingInButton()
        .typePassword(testPassword)
        .clickSingInButton();

    reporter.reportStep("Verify error message");
    String actualErrorMessageText = loginPage.getErrorMessageText();
    assertion.assertEquals(actualErrorMessageText, INCORRECT_PASSWORD.getMessage(), "Verify Error message text");

    assertion.assertAll();
  }
}
