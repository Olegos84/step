package org.itstep.selenium.product.yandex.page;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

  protected WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  @SneakyThrows
  protected static void waitFor(int seconds) {
    Thread.sleep(seconds * 1000);
  }
}
