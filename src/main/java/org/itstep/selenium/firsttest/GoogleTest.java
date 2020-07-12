package org.itstep.selenium.firsttest;

import org.testng.annotations.Test;

public class GoogleTest extends AbstractTest {

  @Test(testName = "test1")
  public void test1() {
    browser.get("http://mail.ru");
  }

  @Test(testName = "test2")
  public void test2() {
    browser.get("http://google.com");
  }
}
