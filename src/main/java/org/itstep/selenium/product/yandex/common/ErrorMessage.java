package org.itstep.selenium.product.yandex.common;

import lombok.Getter;

public enum ErrorMessage {
  INCORRECT_PASSWORD("Неверный пароль");

  @Getter
  private String message;

  ErrorMessage(String message) {
    this.message = message;
  }
}
