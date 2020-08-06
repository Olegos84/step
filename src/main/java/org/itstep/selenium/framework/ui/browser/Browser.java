package org.itstep.selenium.framework.ui.browser;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.itstep.selenium.framework.common.SystemProperties;
import org.itstep.selenium.framework.reporter.Reporter;
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
    Reporter.getReporter().reportInfo("Browser is started");
  }

  public synchronized static Browser getBrowser() {
    if (instance.get() == null) {
      instance.set(new Browser());
    }
    return instance.get();
  }

  public void open(String url) {
    this.driver.get(url);
    Reporter.getReporter().reportInfo("Browser go to URL: " + url);
  }

  public void type(By by, String text) {
    WebElement element = findElement(by);
    try {
      element.sendKeys(text);
    } catch (Exception e) {
      Reporter.getReporter().reportError(String.format("Can not type %s to By: %s", text, by));
      screenshot();
      throw new RuntimeException(e);
    }
    Reporter.getReporter().reportPass(String.format("Typed %s to By: %s", text, by));
  }

  public String getText(By by) {
    return findElement(by).getText();
  }

  public void click(By by) {
    WebElement element = findElement(by);
    try {
      element.click();
    } catch (Exception e) {
      Reporter.getReporter().reportError("Can not click by element By: " + by);
      screenshot();
      throw new RuntimeException(e);
    }
    Reporter.getReporter().reportPass("Clicked  by element", by.toString());
  }

  private WebElement findElement(By by) {
    WebElement element;
    try {
      element = driver.findElement(by);
    } catch (NoSuchElementException e) {
      Reporter.getReporter().reportError("Can not find element", by.toString());
      screenshot();
      throw e;
    }
    Reporter.getReporter().reportPass("Element is found", by.toString());
    return element;
  }

  public void switchToNewTab() {
    try {
      Set<String> windowHandles = driver.getWindowHandles();
      String currentTab = driver.getWindowHandle();
      windowHandles.remove(currentTab);
      String newTab = (String) windowHandles.toArray()[0];
      driver.close();
      driver.switchTo().window(newTab);
    } catch (Exception e) {
      Reporter.getReporter().reportError("Can not switch to new tab");
      screenshot();
      throw new RuntimeException(e);
    }
  }

  public static void close() {
    if (instance.get() != null) {
      instance.get().getWrappedDriver().quit();
    }
    instance.set(null);
  }

  public File screenshot() {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    File outputFolder = new File(System.getProperty(SystemProperties.SCREENSHOT_PATH.getSystemName()));
    String fileName = outputFolder + "/" + screenshot.getName();
    try {
      FileUtils.copyFileToDirectory(screenshot, outputFolder);
    } catch (IOException e) {
      Reporter.getReporter().reportError("Browser can not save screenshot: ");
      Reporter.getReporter().reportError(e);
      throw new RuntimeException(e);
    }
    File screenshotFile = new File(fileName);
    Reporter.getReporter().reportImage(screenshotFile);
    return screenshotFile;
  }

  public WebDriver getWrappedDriver() {
    return driver;
  }
}
