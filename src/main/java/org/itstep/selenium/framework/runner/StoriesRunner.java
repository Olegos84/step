package org.itstep.selenium.framework.runner;

import java.util.Collections;
import java.util.List;
import org.itstep.selenium.framework.step.LoginTestStep;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterControls;

public class StoriesRunner extends JUnitStories {

  @Override
  protected List<String> storyPaths() {
    return Collections.singletonList("stories/LoginTest.story");
  }

  @Override
  public Configuration configuration() {
    return new MostUsefulConfiguration().useParameterControls(new ParameterControls().useDelimiterNamedParameters(true))
        .useStoryLoader(new LoadFromClasspath())
        .usePendingStepStrategy(new FailingUponPendingStep())
        .useStoryReporterBuilder(
            new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.TXT));
  }

  @Override
  public InjectableStepsFactory stepsFactory() {
    return new InstanceStepsFactory(configuration(), new LoginTestStep());
  }
}
