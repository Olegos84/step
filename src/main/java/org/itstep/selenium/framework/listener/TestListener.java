package org.itstep.selenium.framework.listener;

import org.apache.logging.log4j.LogManager;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

  @Override
  public void onStart(ITestContext testContext) {
    super.onStart(testContext);
    LogManager.getLogger().info("[TEST STARTED] " + testContext.getName());
  }

  @Override
  public void onFinish(ITestContext testContext) {
    super.onFinish(testContext);
    LogManager.getLogger().info("[TEST FINISHED] " + testContext.getName());
  }

  @Override
  public void onTestFailure(ITestResult tr) {
    LogManager.getLogger().warn("[TEST FAILURES]" + tr.getThrowable().getMessage());
  }

  @Override
  public void onTestSuccess(ITestResult tr) {
    super.onTestSuccess(tr);
    LogManager.getLogger().info("[TEST SUCCESS]");
  }
}
