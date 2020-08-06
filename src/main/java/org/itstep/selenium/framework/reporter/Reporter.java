package org.itstep.selenium.framework.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.itstep.selenium.framework.common.SystemProperties;

public class Reporter {

  private ExtentReports extentReports;
  private ExtentTest test;
  private Integer stepNumber;
  private static ThreadLocal<Reporter> instance = new ThreadLocal<>();
  private static final String HIDDEN_BLOCK_HTML =
      "<br><a style=\"cursor:pointer\" onclick=\"$(this).next('xmp').toggle()\"> Click to see hidden text </a>"
          + "<xmp style=\"display:none\">"
          + "%s </xmp><br>";

  private Reporter() {
    Logger logger = LogManager.getLogger();
    logger.info("New Reporter is starting...");
    extentReports = new ExtentReports();
    LocalDateTime now = LocalDateTime.now();
    String path = new StringBuilder(System.getProperty(SystemProperties.REPORT_PATH.getSystemName()))
        .append(now.toLocalDate().format(DateTimeFormatter.ofPattern("uuuu-MM-dd")))
        .append("/")
        .append("report-")
        .append(Thread.currentThread().getName())
        .append(".html")
        .toString();
    ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
    extentReports.attachReporter(extentSparkReporter);
    extentReports.setSystemInfo("App URL", System.getProperty(SystemProperties.DEFAULT_PRODUCT_URL.getSystemName()));
    extentReports.setSystemInfo("Browser", System.getProperty(SystemProperties.BROWSER_NAME.getSystemName()));
    extentReports.setSystemInfo("OS", System.getenv("OS"));
    extentReports.setSystemInfo("Architecture", System.getenv("PROCESSOR_ARCHITECTURE"));
    extentReports.setSystemInfo("Number of processors", System.getenv("NUMBER_OF_PROCESSORS"));
    logger.info("Reporter is started");
  }

  public synchronized static Reporter getReporter() {
    if (instance.get() == null) {
      instance.set(new Reporter());
    }
    return instance.get();
  }

  public void reportStep(String message, String... hiddenMessages) {
    reportWithHiddenBlock(Status.INFO, String.format("<b>STEP %d: %s</b>", stepNumber++, message), hiddenMessages);
    LogManager.getLogger().debug(message + Arrays.toString(hiddenMessages));
  }

  public void reportInfo(String message, String... hidedMessages) {
    reportWithHiddenBlock(Status.INFO, message, hidedMessages);
    LogManager.getLogger().debug(message + Arrays.toString(hidedMessages));
  }

  public void reportError(String mainMessage, String... messages) {
    reportWithHiddenBlock(Status.FAIL, mainMessage, messages);
    LogManager.getLogger().error(mainMessage + Arrays.toString(messages));
  }

  public void reportPass(String mainMessage, String... messages) {
    reportWithHiddenBlock(Status.PASS, mainMessage, messages);
    LogManager.getLogger().debug(mainMessage + Arrays.toString(messages));
  }

  public void reportError(Exception exception) {
    reportWithHiddenBlock(Status.FAIL, exception.getMessage(),
        Arrays.toString(exception.getStackTrace()).replace(",", ",\r\n\t"));
    LogManager.getLogger().error(exception);
  }

  public static void close() {
    if (instance.get() != null) {
      ExtentReports extentReports = instance.get().extentReports;
      extentReports.flush();
    }
    instance.set(null);
  }

  public static void startTest(String name, String description) {
    Reporter reporter = getReporter();
    reporter.test = reporter.extentReports.createTest(name, description);
    reporter.stepNumber = 1;
  }

  public static void endTest() {
    Reporter reporter = getReporter();
    reporter.extentReports.flush();
    reporter.test = null;
    reporter.stepNumber = 1;
  }

  private void reportWithHiddenBlock(Status status, String mainMessage, String... messages) {
    String html = new StringBuilder()
        .append(mainMessage)
        .append(buildHiddenBlock(messages))
        .toString();
    test.log(status, html);
  }

  private String buildHiddenBlock(String... strings) {
    StringBuilder builder = new StringBuilder();
    if (strings.length > 0) {
      for (String str : strings) {
        builder.append(str)
            .append("\r\n");
      }
      return String.format(HIDDEN_BLOCK_HTML, builder.toString());
    }
    return StringUtils.EMPTY;
  }

  public void reportImage(File file) {
    String htmlPattern = "<p><a href='%s' target='_blank'><img src='%s'"
        + "  width='350' height='190'></a></p>";
    String path = "../../../../" + file.getPath();
    test.info(String.format(htmlPattern, path, path));
  }
}
