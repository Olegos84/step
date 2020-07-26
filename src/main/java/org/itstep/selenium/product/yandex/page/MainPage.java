package org.itstep.selenium.product.yandex.page;

import static org.itstep.selenium.framework.ui.element.LocatorType.XPATH;

import org.itstep.selenium.framework.common.SystemProperties;
import org.itstep.selenium.framework.ui.browser.Browser;
import org.itstep.selenium.framework.ui.element.Button;

/**
 * Класс который описывает <a href="https://yandex.by/">главную страницу</a>.<br> Смотри: <a
 * href="http://internetka.in.ua/selenium-page-object/">Selenium и Page Object паттерн</a>
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 */
public class MainPage extends BasePage {

  //Locators
  private static final String SIGN_IN_BUTTON_XPATH_LOCATOR = "//*[@class='desk-notif-card__login-title']/following-sibling::*[@role='button']";

  //Elements
  private static Button signInButton = new Button(XPATH, SIGN_IN_BUTTON_XPATH_LOCATOR);

  /**
   * Метод который открывает в браузере новую страницу. Инициализирует класс MainPage
   *
   * @return объект MainPage
   */
  public static MainPage open() {
    Browser.getBrowser().open(System.getProperty(SystemProperties.DEFAULT_PRODUCT_URL.getSystemName()));
    return new MainPage();
  }

  /**
   * Метод ищет и нажимает кнопку 'Войти' на странице. После этого происходит переход на новую вкладку. Данный метод так
   * же заставляет перейти веб драйвер на эту вкладку и закрыть предыдущую
   *
   * @return новую страницу {@link LoginPage}
   */
  public LoginPage clickSingInButton() {
    signInButton.click();
    Browser.getBrowser().switchToNewTab();
    return new LoginPage();
  }

}
