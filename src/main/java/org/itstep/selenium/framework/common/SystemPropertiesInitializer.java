package org.itstep.selenium.framework.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;

public final class SystemPropertiesInitializer {

  private SystemPropertiesInitializer() {
  }

  public static void initSystemProperties(ISuite suite) {
    Logger logger = LogManager.getLogger();
    logger.debug("Initialization of system properties");
    SystemProperties[] systemProperties = SystemProperties.values();
    String propertyNameInSystem;
    String defaultValue;
    for (SystemProperties property : systemProperties) {
      defaultValue = suite.getParameter(property.name());
      propertyNameInSystem = property.getSystemName();
      if (System.getProperty(propertyNameInSystem) == null) {
        logger.warn("Used default value: " + propertyNameInSystem);
        System.setProperty(propertyNameInSystem, defaultValue);
      }
    }
    logger.debug("All properties are initialized");
  }

}
