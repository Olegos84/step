package org.itstep.selenium.framework.ui.browser;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.itstep.selenium.framework.ui.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;

public class Browser implements WrapsDriver {

  private WebDriver driver;
  private static ThreadLocal<Browser> instance = new ThreadLocal<>();

  private Browser() {
    Logger logger = LogManager.getLogger();
    logger.info("New Browser is starting...");
    driver = WebDriverFactory.getWebDriver();
    logger.info("Browser is started");
  }

  public synchronized static Browser getBrowser() {
    if (instance.get() == null) {
      instance.set(new Browser());
    }
    return instance.get();
  }

  public void open(String url) {
    this.driver.get(url);
  }

  public void type(By by, String text) {
    findElement(by).sendKeys(text);
  }

  public String getText(By by) {
    return findElement(by).getText();
  }

  public void click(By by) {
    WebElement element = findElement(by);
    try {
      //wait!!!!
      element.click();
    } catch (Exception e) {
      screenshot();
      throw new RuntimeException(e);
    }
  }

  private WebElement findElement(By by) {
    WebElement element;
    try {
      //Wait!!!
      element = driver.findElement(by);
    } catch (NoSuchElementException e) {
      screenshot();
      throw e;
    }
    return element;
  }

  public void switchToNewTab() {
    Set<String> windowHandles = driver.getWindowHandles();
    String currentTab = driver.getWindowHandle();
    windowHandles.remove(currentTab);
    String newTab = (String) windowHandles.toArray()[0];
    driver.close();
    driver.switchTo().window(newTab);
  }

  public static void close() {
    if (instance.get() != null) {
      instance.get().getWrappedDriver().quit();
    }
    instance.set(null);
  }

  public void screenshot() {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFileToDirectory(screenshot, new File("external_resources/report/screenshotes/"));
    } catch (IOException e) {
      LogManager.getLogger().error(e);
      throw new RuntimeException(e);
    }
  }

  public WebDriver getWrappedDriver() {
    return driver;
  }
}
