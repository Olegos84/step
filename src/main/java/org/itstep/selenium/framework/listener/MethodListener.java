package org.itstep.selenium.framework.listener;

import org.itstep.selenium.framework.reporter.Reporter;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class MethodListener implements IInvokedMethodListener {

  @Override
  public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    if (method.isTestMethod()) {
      Reporter.startTest(method.getTestMethod().getMethodName(), method.getTestMethod().getDescription());
    }
  }

  @Override
  public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    Reporter.endTest();
  }
}
