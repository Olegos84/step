package org.itstep.selenium.framework.ui;

import static org.itstep.selenium.framework.common.SystemProperties.BROWSER_NAME;
import static org.itstep.selenium.framework.common.SystemProperties.IMPLICIT_WAIT;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.NotImplementedException;
import org.itstep.selenium.framework.common.SystemProperties;
import org.itstep.selenium.framework.listener.WebDriverListener;
import org.itstep.selenium.framework.ui.browser.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Класс-фабрика для инициализации {@link WebDriver WebDriver} в зависимости от того, что установлено в
 * {@link SystemProperties системной переменной}.<br>
 *   Класс финализирован, т.е. наследоваться от него нельзя<br>
 *   Класс используется только для вызова метода статического {@link #getWebDriver() getWebDriver()}
 *
 * Смотри также <a href="https://vertex-academy.com/tutorials/ru/pattern-factory-java/">Паттерн проектирования Factory - Часть 1</a>
 * @author <a href="mailto:olegos84@gmail.com">Aleh Ulizko</a>
 */
public final class WebDriverFactory {

  /**
   * Конструктор объявлен как приватный, что запрещает создание объектов данного класса.
   */
  private WebDriverFactory() {
  }

  public static WebDriver getWebDriver() {
    System.setProperty("wdm.cachePath",
        new File(System.getProperty(SystemProperties.DRIVERS_PATH.getSystemName())).getAbsolutePath());
    String browserName = System.getProperty(BROWSER_NAME.getSystemName());
    BrowserType browserType;
    try {
      browserType = BrowserType.valueOf(browserName.toUpperCase());
    } catch (Exception e) {
      throw new NotImplementedException(
          String.format("Browser name '%s' is incorrect or not implemented", browserName));
    }

    WebDriver driver;
    switch (browserType) {
      case CHROME:
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        break;
      case FIREFOX:
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        break;
      default:
        throw new NotImplementedException("Not implemented for browser: " + browserType);
    }
    long implicitWait = Long.parseLong(System.getProperty(IMPLICIT_WAIT.getSystemName()));
    Timeouts timeouts = driver.manage().timeouts();
    timeouts.implicitlyWait(implicitWait, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
    eventFiringWebDriver.register(new WebDriverListener());
    driver = eventFiringWebDriver;
    return driver;
  }
}
