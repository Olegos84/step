package org.itstep.selenium.test;

import org.itstep.selenium.framework.common.SystemProperties;
import org.itstep.selenium.framework.common.SystemPropertiesInitializer;
import org.itstep.selenium.framework.ui.browser.Browser;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

/**
 * Класс для общей логики всех тестов. А также в нем инициализируются все необходимые переменные
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 */
public abstract class BaseTest {

  /**
   * Метод который будет автоматически запускаться перед каждым новым сьютом.<br>
   *   Смотри например тут: <a href='https://habr.com/ru/post/121234/'>Тестирование в Java. TestNG</a><br>
   * Метод берет у testNG контекст у которого смотрит все установленные properties. В нашем случае это то, что мы
   * установили в testNG.xml файле. <br>
   * После этого, он проверяет в системе все переменные, описанные в классе {@link SystemProperties}.
   * И если в системе эта переменная не задана, т.е. == null, то устанавливается значение из xml файла.
   *
   * @param testContext TestNG контекст
   */
  @BeforeSuite(alwaysRun = true)
  public void initSuite(ITestContext testContext) {
    SystemPropertiesInitializer.initSystemProperties(testContext);
  }

  /**
   * Метод, который после каждого метода закрывает браузер и останавливает драйвер
   */
  @AfterMethod(alwaysRun = true)
  public void teardown() {
    Browser.getBrowser().close();
  }
}
