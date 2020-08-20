package org.itstep.selenium.framework.runner;

import com.beust.jcommander.Parameter;

public class CommandLineParameters {

  @Parameter(names = {"-h", "-help"})
  private boolean help;

  @Parameter(names = {"-b", "-browser"}, description = "A browser that should be used for tests")
  private String browserName;

  @Parameter(names = {"-url"}, description = "Test application url")
  private String url;

  @Parameter(names = {"-w", "-wait"}, description = "Implicit wait in seconds")
  private String implicitWait;

  public String getUrl() {
    return url;
  }

  public String getImplicitWait() {
    return implicitWait;
  }

  public boolean isHelp() {
    return help;
  }

  public String getBrowserName() {
    return browserName;
  }
}
