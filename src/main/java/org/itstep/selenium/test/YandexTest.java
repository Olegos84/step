package org.itstep.selenium.test;

import static org.itstep.selenium.product.yandex.common.ErrorMessage.INCORRECT_PASSWORD;

import org.itstep.selenium.product.yandex.page.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YandexTest extends BaseTest {

  @Test(testName = "testYandexLoginNegative",
      groups = {"testThatWork"},
      description = "Verify that user can not login with invalid credentials")
  public void testYandexLoginNegative() {
    String actualErrorMessageText = MainPage.open(driver)
        .clickSingInButton()
        .typeLogin("Aleh")
        .clickSingInButton()
        .typePassword("123456789")
        .clickSingInButton()
        .getErrorMessageText();
    Assert.assertEquals(actualErrorMessageText, INCORRECT_PASSWORD.getMessage(), "Verify Error message text");
  }
}
