package org.itstep.selenium.framework.ui.browser;

import java.util.Set;
import org.itstep.selenium.framework.ui.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WrapsDriver;

public class Browser implements WrapsDriver {

  private WebDriver driver;
  private static ThreadLocal<Browser> instance = new ThreadLocal<Browser>();

  private Browser() {
    driver = WebDriverFactory.getWebDriver();
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
    driver.findElement(by).sendKeys(text);
  }

  public String getText(By by) {
    return driver.findElement(by).getText();
  }

  public void click(By by) {
    driver.findElement(by).click();
  }

  public void switchToNewTab() {
    Set<String> windowHandles = driver.getWindowHandles();
    String currentTab = driver.getWindowHandle();
    windowHandles.remove(currentTab);
    String newTab = (String) windowHandles.toArray()[0];
    driver.close();
    driver.switchTo().window(newTab);
  }

  public void close() {
    if (instance.get() != null) {
      driver.quit();
    }
    instance.set(null);
  }

  public WebDriver getWrappedDriver() {
    return driver;
  }
}
