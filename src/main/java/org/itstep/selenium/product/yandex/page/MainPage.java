package org.itstep.selenium.product.yandex.page;

import java.util.Set;
import org.itstep.selenium.framework.common.SystemProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс который описывает <a href="https://yandex.by/">главную страницу</a>.<br>
 * Смотри: <a href="http://internetka.in.ua/selenium-page-object/">Selenium и Page Object паттерн</a>
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 */
public class MainPage extends BasePage {

  /**
   * Поле класса, для хранение элемента-кнопки 'Войти', на главной странице
   */
  @FindBy(xpath = "//*[@class='desk-notif-card__login-title']/following-sibling::*[@role='button']")
  private WebElement signInButton;

  /**
   * Конструктор, предназначен для создания объекта данной страницы. Он приватный. Что бы получить эту страницу
   * необходимо пользоваться статическим методом {@link #open(WebDriver) open}<br>
   *   В конструкторе вызывается PageFactory.initElements(driver, this) для инициализации элементов с аннотациями
   *   FindBy
   *
   * @param driver Веб драйвер
   */
  private MainPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  /**
   * Метод который открывает в браузере новую страницу. Инициализирует класс MainPage
   *
   * @param driver инициализированный веб драйвер
   * @return объект MainPage
   */
  public static MainPage open(WebDriver driver) {
    MainPage mainPage = new MainPage(driver);
    driver.get(System.getProperty(SystemProperties.DEFAULT_PRODUCT_URL.getSystemName()));
    return mainPage;
  }

  /**
   * Метод ищет и нажимает кнопку 'Войти' на странице. После этого происходит переход на новую вкладку. Данный метод
   * так же заставляет перейти веб драйвер на эту вкладку и закрыть предыдущую
   *
   * @return новую страницу {@link LoginPage}
   */
  public LoginPage clickSingInButton() {
    signInButton.click();
    Set<String> windowHandles = driver.getWindowHandles();
    String currentTab = driver.getWindowHandle();
    windowHandles.remove(currentTab);
    String newTab = (String) windowHandles.toArray()[0];
    driver.close();
    driver.switchTo().window(newTab);
    return new LoginPage(driver);
  }

}
