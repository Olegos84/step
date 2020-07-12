package org.itstep.selenium.firsttest;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {

  protected WebDriver browser;

  @BeforeMethod
  public void init() {
    final String driverPathPattern = "external_resources/drivers/%s.exe";
    System.setProperty("webdriver.chrome.driver",
        new File(String.format(driverPathPattern, "chromedriver")).getAbsolutePath());
    System.setProperty("webdriver.gecko.driver",
        new File(String.format(driverPathPattern, "geckodriver")).getAbsolutePath());
    browser = new ChromeDriver();
    browser.manage().window().maximize();
  }

  @AfterMethod
  public void teardown() {
    browser.close();
    browser.quit();
  }
}
