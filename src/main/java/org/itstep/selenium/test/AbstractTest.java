package org.itstep.selenium.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import lombok.SneakyThrows;
import org.itstep.selenium.browser.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public abstract class AbstractTest {

  protected WebDriver driver;
  protected String baseUrl;
  private String driverPath;
  private BrowserType browserType;

  @BeforeSuite(alwaysRun = true)
  @Parameters({"driversPath", "browserName", "baseUrl"})
  public void initSuite(String driversPath, String browserName, String baseUrl) {
    this.driverPath = driversPath;
    this.browserType = BrowserType.valueOf(browserName.toUpperCase());
    this.baseUrl = baseUrl;
  }

  @BeforeMethod(alwaysRun = true)
  public void init() {
    System.setProperty("wdm.cachePath", new File(driverPath).getAbsolutePath());
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

  @SneakyThrows
  protected void waitFor(int seconds) {
    Thread.sleep(seconds * 1000);
  }
}
