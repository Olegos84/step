package org.itstep.selenium.framework.ui.element;

import org.apache.commons.lang3.NotImplementedException;
import org.itstep.selenium.framework.ui.browser.Browser;
import org.openqa.selenium.By;

public abstract class BaseElement {
  protected By by;

  public BaseElement(LocatorType locatorType, String locator) {
    switch (locatorType) {
      case XPATH:
        this.by = By.xpath(locator);
        break;
      case CSS:
        this.by = By.cssSelector(locator);
        break;
      default:
        throw new NotImplementedException("Not implemented for " + locatorType);
    }
  }

  public String getText() {
    return Browser.getBrowser().getText(by);
  }
}
