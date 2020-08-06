package org.itstep.selenium.framework.assertion;

import org.itstep.selenium.framework.reporter.Reporter;
import org.itstep.selenium.framework.ui.browser.Browser;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

public class Assertion extends SoftAssert {

  public Assertion() {
    super();
  }

  @Override
  public void onAssertSuccess(IAssert<?> assertCommand) {
    super.onAssertSuccess(assertCommand);
    Reporter.getReporter()
        .reportPass(assertCommand.getMessage(),
            "Expected: " + assertCommand.getExpected(),
            "Actual: " + assertCommand.getActual());
  }

  @Override
  public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
    super.onAssertFailure(assertCommand, ex);
    Reporter.getReporter().reportError(assertCommand.getMessage(),
        " Expected : " + assertCommand.getExpected(),
        " Actual : " + assertCommand.getActual());
    Browser.getBrowser().screenshot();
  }
}
