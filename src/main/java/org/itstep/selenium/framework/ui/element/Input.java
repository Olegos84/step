package org.itstep.selenium.framework.ui.element;

import org.itstep.selenium.framework.ui.browser.Browser;

public class Input extends BaseElement {

  public Input(LocatorType locatorType, String locator) {
    super(locatorType, locator);
  }

  public void type(String text) {
    Browser.getBrowser().type(by, text);
  }
}
