package org.itstep.selenium.framework.runner;

import com.beust.jcommander.JCommander;
import java.io.File;
import java.util.Collections;
import org.itstep.selenium.framework.common.SystemProperties;
import org.testng.TestNG;

public class Runner {

  public static void main(String[] args) {
    parseCli(args);
    runTestNG();
  }

  private static void runTestNG() {
    TestNG testNG = new TestNG();
    String fileName = "testNG.xml";
    String filePath = new File("external_resources/suite/" + fileName).getAbsolutePath();
    testNG.setTestSuites(Collections.singletonList(filePath));
    testNG.run();
  }

  private static void parseCli(String[] args) {
    CommandLineParameters commandLineParameters = new CommandLineParameters();
    JCommander jCommander = JCommander.newBuilder()
        .addObject(commandLineParameters)
        .build();
    try {
      jCommander.parse(args);
    } catch (Exception e) {
      jCommander.usage();
      System.exit(1);
    }
    if (commandLineParameters.isHelp()) {
      jCommander.usage();
      System.exit(0);
    }
    if (commandLineParameters.getBrowserName() != null) {
      System.setProperty(SystemProperties.BROWSER_NAME.getSystemName(), commandLineParameters.getBrowserName());
    }
    if (commandLineParameters.getUrl() != null) {
      System.setProperty(SystemProperties.DEFAULT_PRODUCT_URL.getSystemName(), commandLineParameters.getUrl());
    }
    if (commandLineParameters.getImplicitWait() != null) {
      System.setProperty(SystemProperties.IMPLICIT_WAIT.getSystemName(), commandLineParameters.getImplicitWait());
    }
  }
}
