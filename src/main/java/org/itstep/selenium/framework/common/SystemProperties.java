package org.itstep.selenium.framework.common;

/**
 * Перечисление всех системных переменных, которые в обязательном порядке должны быть записаны
 * в System.setProperty. Сами имена(которые в верхнем регистре) это те имена, которые мы используем
 * в коде. В скобках - имя под которым эта переменная будет храниться в системе.
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 * @see java.lang.Enum
 */
public enum SystemProperties {
  DEFAULT_PRODUCT_URL("test.selenium.baseUrl"),
  DRIVERS_PATH("test.selenium.driversPath"),
  BROWSER_NAME("test.selenium.browserName"),
  IMPLICIT_WAIT("test.selenium.implicitWait");

  /**
   * Переменная, которая хранит имя, под которым в системе будут записана системная переменная
   */
  private String systemName;

  /**
   * Получение системного
   * @return Имя под которой переменная хранится в системе
   */
  public String getSystemName() {
    return systemName;
  }

  /**
   * Конструктор, который используется для каждого значения перечисленного в Enum
   * @param systemName Имя под которой переменная хранится в системе
   */
  SystemProperties(String systemName) {
    this.systemName = systemName;
  }
}
