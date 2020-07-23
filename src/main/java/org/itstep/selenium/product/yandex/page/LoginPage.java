package org.itstep.selenium.product.yandex.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

  private static final String ERROR_MESSAGE_CLASS_LOCATOR = "passp-form-field__error";
  @FindBy(id = "passp-field-login")
  private WebElement loginInput;
  @FindBy(id = "passp-field-passwd")
  private WebElement passwordInput;
  @FindBy(xpath = "//*[@type='submit']")
  private WebElement signInButton;

  public LoginPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public LoginPage typeLogin(String login) {
    loginInput.sendKeys(login);
    return new LoginPage(driver);
  }

  public LoginPage typePassword(String password) {
    passwordInput.sendKeys(password);
    return new LoginPage(driver);
  }

  public LoginPage clickSingInButton() {
    signInButton.click();
    return new LoginPage(driver);
  }

  public String getErrorMessageText() {
    return driver.findElement(By.className(ERROR_MESSAGE_CLASS_LOCATOR)).getText();
  }
}
