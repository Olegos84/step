package org.itstep.selenium.product.yandex.service;

import org.itstep.selenium.framework.reporter.Reporter;
import org.itstep.selenium.product.yandex.dto.User;
import org.itstep.selenium.product.yandex.page.LoginPage;
import org.itstep.selenium.product.yandex.page.MainPage;

public class LoginService {

  private Reporter reporter;

  public LoginService(Reporter reporter) {
    this.reporter = reporter;
  }

  public LoginPage loginWithIncorrectUser(User user) {
    reporter.reportStep("Open login page");
    MainPage mainPage = MainPage.open();
    reporter.reportStep("Try to login", "login: " + user.getUsername(), "password: " +
        user.getPassword());
    return mainPage.clickSingInButton()
        .typeLogin(user.getUsername())
        .clickSingInButton()
        .typePassword(user.getPassword())
        .clickSingInButton();
  }
}
