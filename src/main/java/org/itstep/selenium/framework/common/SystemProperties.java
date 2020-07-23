package org.itstep.selenium.framework.common;

import lombok.Getter;

public enum SystemProperties {
  DEFAULT_PRODUCT_URL("test.selenium.baseUrl"),
  DRIVERS_PATH("test.selenium.driversPath"),
  BROWSER_NAME("test.selenium.browserName"),
  IMPLICIT_WAIT("test.selenium.implicitWait");

  @Getter
  private String systemName;

  SystemProperties(String systemName) {
    this.systemName = systemName;
  }
}
