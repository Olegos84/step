package org.itstep.selenium.framework.listener;

import org.apache.logging.log4j.LogManager;
import org.itstep.selenium.framework.common.SystemPropertiesInitializer;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

  public void onStart(ISuite suite) {
    SystemPropertiesInitializer.initSystemProperties(suite);
    LogManager.getLogger().info("[SUITE STARTED] " + suite.getName());
  }

  public void onFinish(ISuite suite) {
    LogManager.getLogger().info("[SUITE FINISHED] " + suite.getName());
  }
}
