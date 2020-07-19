package org.itstep.selenium.product.yandex.test;

import static org.itstep.selenium.framework.common.SystemProperties.BROWSER_NAME;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import org.itstep.selenium.framework.browser.BrowserType;
import org.itstep.selenium.framework.common.SystemProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

  protected WebDriver driver;

  @BeforeSuite(alwaysRun = true)
  public void initSuite(ITestContext testContext) {
    SystemProperties[] systemProperties = SystemProperties.values();
    ISuite suite = testContext.getSuite();
    String propertyNameInSystem;
    String defaultValue;
    for (SystemProperties property : systemProperties) {
      defaultValue = suite.getParameter(property.name());
      propertyNameInSystem = property.getSystemName();
      if (System.getProperty(propertyNameInSystem) == null) {
        System.setProperty(propertyNameInSystem, defaultValue);
      }
    }
  }

  @BeforeMethod(alwaysRun = true)
  public void init() {
    System.setProperty("wdm.cachePath",
        new File(System.getProperty(SystemProperties.DRIVERS_PATH.getSystemName())).getAbsolutePath());
    BrowserType browserType = BrowserType.valueOf(System.getProperty(BROWSER_NAME.getSystemName()).toUpperCase());
    switch (browserType) {
      case CHROME:
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        break;
      case FIREFOX:
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        break;
    }
    driver.manage().window().maximize();
  }

  @AfterMethod(alwaysRun = true)
  public void teardown() {
    driver.quit();
  }
}
