package org.itstep.selenium.product.yandex.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс который описывает <a href="https://passport.yandex.by/">Страницу входа</a>.<br>
 * Смотри: <a href="http://internetka.in.ua/selenium-page-object/">Selenium и Page Object паттерн</a>
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 */
public class LoginPage extends BasePage {

  /**
   * CSS локатор по классу поля где появляется сообщение об ошибке
   */
  private static final String ERROR_MESSAGE_CLASS_LOCATOR = "passp-form-field__error";
  /**
   * Переменная для хранения поля ввода имени пользователя
   */
  @FindBy(id = "passp-field-login")
  private WebElement loginInput;
  /**
   * Переменная для хранения поля ввода пароля
   */
  @FindBy(id = "passp-field-passwd")
  private WebElement passwordInput;
  /**
   * Переменная для хранения кнопки войти
   */
  @FindBy(xpath = "//*[@type='submit']")
  private WebElement signInButton;

  /**
   * Конструктор, предназначен для создания объекта данной страницы. <br>
   *   В конструкторе вызывается PageFactory.initElements(driver, this) для инициализации элементов с аннотациями
   *   FindBy
   *
   * @param driver Веб драйвер
   */
  public LoginPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  /**
   * Вводит имя пользователя на странице
   *
   * @param login имя пользователя
   * @return возвращает текущую страницу
   */
  public LoginPage typeLogin(String login) {
    loginInput.sendKeys(login);
    return new LoginPage(driver);
  }

  /**
   * Вводит пароль на странице
   *
   * @param password пароль
   * @return возвращает текущую страницу
   */
  public LoginPage typePassword(String password) {
    passwordInput.sendKeys(password);
    return new LoginPage(driver);
  }

  /**
   * Нажимает кнопку войти
   *
   * @return возвращает текущую страницу
   */
  public LoginPage clickSingInButton() {
    signInButton.click();
    return new LoginPage(driver);
  }

  /**
   * Метод ищет элемент в котором должна быть ошибка и возвращает текст этой ошибки
   *
   * @return текст ошибки
   */
  public String getErrorMessageText() {
    return driver.findElement(By.className(ERROR_MESSAGE_CLASS_LOCATOR)).getText();
  }
}
