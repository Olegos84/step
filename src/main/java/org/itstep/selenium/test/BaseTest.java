package org.itstep.selenium.test;

import org.itstep.selenium.framework.ui.browser.Browser;
import org.testng.annotations.AfterMethod;

/**
 * Класс для общей логики всех тестов. А также в нем инициализируются все необходимые переменные
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 */
public abstract class BaseTest {

  /**
   * Метод, который после каждого метода закрывает браузер и останавливает драйвер
   */
  @AfterMethod(alwaysRun = true)
  public void teardown() {
    Browser.close();
  }
}
