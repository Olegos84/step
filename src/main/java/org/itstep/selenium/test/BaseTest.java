package org.itstep.selenium.test;

import org.itstep.selenium.framework.common.SystemProperties;
import org.itstep.selenium.framework.ui.WebDriverFactory;
import org.openqa.selenium.WebDriver;
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
    driver = WebDriverFactory.getWebDriver();
  }

  @AfterMethod(alwaysRun = true)
  public void teardown() {
    driver.quit();
  }
}
