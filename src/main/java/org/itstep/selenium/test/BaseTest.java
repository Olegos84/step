package org.itstep.selenium.test;

import org.itstep.selenium.framework.assertion.Assertion;
import org.itstep.selenium.framework.reporter.Reporter;
import org.itstep.selenium.framework.ui.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * Класс для общей логики всех тестов. А также в нем инициализируются все необходимые переменные
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 */
public abstract class BaseTest {

  protected Reporter reporter;
  protected Assertion assertion;

  @BeforeClass(alwaysRun = true)
  public void initClass() {
    reporter = Reporter.getReporter();
  }

  @BeforeMethod(alwaysRun = true)
  public void initMethod() {
    assertion = new Assertion();
  }

  /**
   * Метод, который после каждого метода закрывает браузер и останавливает драйвер
   */
  @AfterMethod(alwaysRun = true)
  public void teardown() {
    Browser.close();
  }
}
