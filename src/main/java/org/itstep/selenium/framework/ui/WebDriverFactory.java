package org.itstep.selenium.framework.ui;

import static org.itstep.selenium.framework.common.SystemProperties.BROWSER_NAME;
import static org.itstep.selenium.framework.common.SystemProperties.IMPLICIT_WAIT;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.NotImplementedException;
import org.itstep.selenium.framework.common.SystemProperties;
import org.itstep.selenium.framework.ui.browser.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class WebDriverFactory {

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
    return driver;
  }
}
