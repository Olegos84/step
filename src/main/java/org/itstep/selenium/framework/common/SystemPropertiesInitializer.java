package org.itstep.selenium.framework.common;

import org.testng.ISuite;
import org.testng.ITestContext;

public final class SystemPropertiesInitializer {

  private SystemPropertiesInitializer() {
  }

  public static void initSystemProperties(ITestContext testContext) {
    SystemProperties[] systemProperties = SystemProperties.values();
    ISuite suite = testContext.getSuite();
    String propertyNameInSystem;
    String defaultValue;
    for (SystemProperties property : systemProperties) {
      defaultValue = suite.getParameter(property.name());
      propertyNameInSystem = property.getSystemName();
      if (System.getProperty(propertyNameInSystem) == null) {
        System.setProperty(propertyNameInSystem, defaultValue);
      }
    }
  }

}
