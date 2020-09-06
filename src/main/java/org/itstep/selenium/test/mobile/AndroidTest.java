package org.itstep.selenium.test.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class AndroidTest {

  @Test
  public void testCalculator() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
    capabilities.setCapability("appPackage", "com.android.calculator2");
    capabilities.setCapability("appActivity", ".Calculator");
    URL url = null;
    try {
      url = new URL("http://localhost:4723/wd/hub");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    AndroidDriver driver = new AndroidDriver(url, capabilities);

    WebElement digit_9 = driver.findElement(By.id("digit_9"));
    digit_9.click();
  }

}
