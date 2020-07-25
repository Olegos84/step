package org.itstep.selenium.framework.ui.browser;

/**
 * Перечисление всех имен браузеров для которых работают тесты. Что бы добавить новый браузер, необходимо добавить
 * его в здесь, а также в {@link org.itstep.selenium.framework.ui.WebDriverFactory WebDriverFactory} необходимо
 * реализовать инициализацию драйвера
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 * @see java.lang.Enum
 * @see org.itstep.selenium.framework.ui.WebDriverFactory
 */
public enum BrowserType {
  CHROME, FIREFOX
}
