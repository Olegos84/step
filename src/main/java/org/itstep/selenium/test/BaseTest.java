package org.itstep.selenium.test;

import org.itstep.selenium.framework.common.SystemProperties;
import org.itstep.selenium.framework.ui.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * Класс для общей логики всех тестов. А также в нем инициализируются все необходимые переменные
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 */
public abstract class BaseTest {

  /**
   * Переменная для хранения веб драйвера
   */
  protected WebDriver driver;

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
    SystemProperties[] systemProperties = SystemProperties.values();
    ISuite suite = testContext.getSuite();
    String propertyNameInSystem;
    String defaultValue;
    for (SystemProperties property : systemProperties) {
      defaultValue = suite.getParameter(property.name());
      propertyNameInSystem = property.getSystemName();
      if (System.getProperty(propertyNameInSystem) == null) {
        System.setProperty(propertyNameInSystem, defaultValue);
      }
    }
  }

  /**
   * Метод, который запускается перед каждым тестовым методом. В нем создается каждый раз новый драйвер,
   * а следовательно открывается новый браузер
   */
  @BeforeMethod(alwaysRun = true)
  public void init() {
    driver = WebDriverFactory.getWebDriver();
  }

  /**
   * Метод, который после каждого метода закрывает браузер и останавливает драйвер
   */
  @AfterMethod(alwaysRun = true)
  public void teardown() {
    driver.quit();
  }
}
