package org.itstep.selenium.framework.ui.element;

import org.itstep.selenium.framework.ui.browser.Browser;

public class Button extends BaseElement{

  public Button(LocatorType locatorType, String locator) {
    super(locatorType, locator);
  }

  public void click() {
    Browser.getBrowser().click(by);
  }
}
