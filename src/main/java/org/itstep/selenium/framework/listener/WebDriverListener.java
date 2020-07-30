package org.itstep.selenium.framework.listener;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebDriverListener extends AbstractWebDriverEventListener {

  @Override
  public void beforeClickOn(WebElement element, WebDriver driver) {
    LogManager.getLogger().debug("Click on element: " + element);
  }

  @Override
  public void afterNavigateTo(String url, WebDriver driver) {
    LogManager.getLogger().info("WebDriver navigate to: " + url);
  }

  @Override
  public void beforeFindBy(By by, WebElement element, WebDriver driver) {
    LogManager.getLogger().debug("Find element by: " + by);
  }
}
