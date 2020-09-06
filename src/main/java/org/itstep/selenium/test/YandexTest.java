package org.itstep.selenium.test;

import static org.itstep.selenium.product.yandex.common.ErrorMessage.INCORRECT_PASSWORD;

import org.itstep.selenium.product.yandex.dto.User;
import org.itstep.selenium.product.yandex.page.LoginPage;
import org.itstep.selenium.product.yandex.service.LoginService;
import org.testng.annotations.Test;

/**
 * Класс для тестов
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 */
public class YandexTest extends BaseTest {

  /**
   * Тестовый метод, который идет на яндекс, пытается войти с неверными логином и паролем, и проверяет сообщение об
   * ошибке
   */
  @Test(testName = "testYandexLoginNegative",
      groups = {"testThatWork"},
      description = "Verify that user can not login with invalid credentials")
  public void testYandexLoginNegative() {
    User incorrectTestUser = new User("Aleh", "123456789");

    LoginPage loginPage = new LoginService(reporter).loginWithIncorrectUser(incorrectTestUser);

    reporter.reportStep("Verify error message");
    String actualErrorMessageText = loginPage.getErrorMessageText();
    assertion.assertEquals(actualErrorMessageText, INCORRECT_PASSWORD.getMessage(), "Verify Error message text");

    assertion.assertAll();
  }
}
