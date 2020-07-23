package org.itstep.selenium.product.yandex.page;

import java.util.Set;
import org.itstep.selenium.framework.common.SystemProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

  @FindBy(xpath = "//*[@class='desk-notif-card__login-title']/following-sibling::*[@role='button']")
  private WebElement signInButton;

  private MainPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public static MainPage open(WebDriver driver) {
    MainPage mainPage = new MainPage(driver);
    driver.get(System.getProperty(SystemProperties.DEFAULT_PRODUCT_URL.getSystemName()));
    return mainPage;
  }

  public LoginPage clickSingInButton() {
    signInButton.click();
    Set<String> windowHandles = driver.getWindowHandles();
    String currentTab = driver.getWindowHandle();
    windowHandles.remove(currentTab);
    String newTab = (String) windowHandles.toArray()[0];
    driver.close();
    driver.switchTo().window(newTab);
    return new LoginPage(driver);
  }

}
