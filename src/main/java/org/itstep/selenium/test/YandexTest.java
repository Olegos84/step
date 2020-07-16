package org.itstep.selenium.test;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YandexTest extends AbstractTest {

  @Test(testName = "testYandexLoginNegative",
      groups = {"testThatWork"},
      description = "Verify that user can not login with invalid credentials")
  public void testYandexLoginNegative() {
    driver.get(baseUrl);
    waitFor(2);
    WebElement signInButton = driver
        .findElement(By.xpath("//*[@class='desk-notif-card__login-title']/following-sibling::*[@role='button']"));
    signInButton.click();
    waitFor(2);
    Set<String> windowHandles = driver.getWindowHandles();
    String currentTab = driver.getWindowHandle();
    windowHandles.remove(currentTab);
    String newTab = (String) windowHandles.toArray()[0];
    driver.close();
    driver.switchTo().window(newTab);
    waitFor(2);
    driver.findElement(By.id("passp-field-login")).sendKeys("Aleh");
    waitFor(2);
    signInButton = driver
        .findElement(By.xpath("//*[@type='submit']"));
    signInButton.click();
    waitFor(2);
    driver.findElement(By.id("passp-field-passwd")).sendKeys("123456789");
    signInButton = driver
        .findElement(By.xpath("//*[@type='submit']"));
    signInButton.click();
    waitFor(2);
    String actualErrorMessage = driver.findElement(By.className("passp-form-field__error")).getText();
    Assert.assertEquals(actualErrorMessage, "Неверный пароль" , "Verify Error message text");
  }
}
