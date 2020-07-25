package org.itstep.selenium.product.yandex.page;

import org.openqa.selenium.WebDriver;

/**
 * Абстрактный класс от которого должны наследоваться все классы описывающие внешний вид и элементы содержащиеся
 * на странице, а также методы, описывающие действия доступные на странице. В данном классе содержатся общие для всех
 * страниц методы и переменные, например {@link #driver веб драйвер}.<br>
 * Смотри: <a href="http://internetka.in.ua/selenium-page-object/">Selenium и Page Object паттерн</a>
 *
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 */
public abstract class BasePage {

  /**
   * Переменная для хранения инициализированного драйвера
   */
  protected WebDriver driver;

  /**
   * Конструктор, который сохраняет драйвер в поле {@link #driver WebDriver driver}
   * @param driver Инициализированный и настроенный драйвер
   */
  public BasePage(WebDriver driver) {
    this.driver = driver;
  }
}
