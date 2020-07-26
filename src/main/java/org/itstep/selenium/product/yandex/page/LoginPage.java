package org.itstep.selenium.product.yandex.page;

import static org.itstep.selenium.framework.ui.element.LocatorType.*;

import org.itstep.selenium.framework.ui.element.Button;
import org.itstep.selenium.framework.ui.element.Input;
import org.itstep.selenium.framework.ui.element.MessageField;

/**
 * Класс который описывает <a href="https://passport.yandex.by/">Страницу входа</a>.<br> Смотри: <a
 * href="http://internetka.in.ua/selenium-page-object/">Selenium и Page Object паттерн</a>
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 */
public class LoginPage extends BasePage {

  //Locators
  private static final String ERROR_MESSAGE_CSS_SELECTOR = ".passp-form-field__error";
  private static final String LOGIN_INPUT_CSS_SELECTOR = "#passp-field-login";
  private static final String PASSWORD_INPUT_CSS_SELECTOR = "#passp-field-passwd";
  private static final String SIGN_IN_BUTTON_XPATH_LOCATOR = "//*[@type='submit']";

  //Elements
  private static MessageField errorMessage = new MessageField(CSS, ERROR_MESSAGE_CSS_SELECTOR);
  private static Input loginInput = new Input(CSS, LOGIN_INPUT_CSS_SELECTOR);
  private static Input passwordInput = new Input(CSS, PASSWORD_INPUT_CSS_SELECTOR);
  private static Button signInButton = new Button(XPATH, SIGN_IN_BUTTON_XPATH_LOCATOR);

  /**
   * Вводит имя пользователя на странице
   *
   * @param login имя пользователя
   * @return возвращает текущую страницу
   */
  public LoginPage typeLogin(String login) {
    loginInput.type(login);
    return this;
  }

  /**
   * Вводит пароль на странице
   *
   * @param password пароль
   * @return возвращает текущую страницу
   */
  public LoginPage typePassword(String password) {
    passwordInput.type(password);
    return this;
  }

  /**
   * Нажимает кнопку войти
   *
   * @return возвращает текущую страницу
   */
  public LoginPage clickSingInButton() {
    signInButton.click();
    return this;
  }

  /**
   * Метод ищет элемент в котором должна быть ошибка и возвращает текст этой ошибки
   *
   * @return текст ошибки
   */
  public String getErrorMessageText() {
    return errorMessage.getText();
  }
}
