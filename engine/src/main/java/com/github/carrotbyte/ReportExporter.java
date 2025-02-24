package com.github.carrotbyte;

import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import com.github.carrotbyte.configuration.ExporterConfiguration;
import com.github.carrotbyte.factories.LaunchEventsFactory;
import net.thucydides.model.domain.TestOutcome;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Stream;

public class ReportExporter {

    private final ExporterConfiguration configuration;

    private ReportExporter(ExporterConfiguration configuration) {
        this.configuration = configuration;
    }

    public static ReportExporter create(ExporterConfiguration configuration) {
        return new ReportExporter(configuration);
    }

    public void export() {
        // At this point we need to have all test outcomes metadata, or load them all into memory
        // configuration.testOutcomesProvider().getTestOutcomes()
        ListenerParameters parameters = configuration.parametersProvider().getParameters();
        Launch launch = createLaunch(parameters, configuration.launchEventsFactory());
        // Delegate suites exports
        new SuiteExporter(Collections.emptyList(), launch, configuration).export();
        finishLaunch(launch, configuration.launchEventsFactory());
    }

    private Launch createLaunch(ListenerParameters listenerParameters, LaunchEventsFactory factory) {
        ReportPortal reportPortal = ReportPortal.builder().withParameters(listenerParameters).build();
        Stream<TestOutcome> testOutcomesStream = configuration.testOutcomesProvider().getTestOutcomes();
        Date startDate = testOutcomesStream.map(TestOutcome::getStartTime)
                .min(Comparator.naturalOrder())
                .map(date -> Date.from(date.toInstant()))
                .orElseThrow(() -> new IllegalArgumentException("There are no test outcomes."));
        StartLaunchRQ startLaunchRQ = factory.buildStartLaunch(startDate);
        //TODO: where do we take name?
        startLaunchRQ.setName("Test");
        return reportPortal.newLaunch(startLaunchRQ);
    }

    private void finishLaunch(Launch launch, LaunchEventsFactory factory) {
        Stream<TestOutcome> testOutcomesStream = configuration.testOutcomesProvider().getTestOutcomes();
        Date finishDate = testOutcomesStream.map(
                        testOutcome -> testOutcome.getStartTime().toInstant()
                                .plusMillis(testOutcome.getDuration()))
                .max(Comparator.naturalOrder())
                .map(Date::from).orElseThrow(() -> new IllegalArgumentException("There are no test outcomes."));
        FinishExecutionRQ event = factory.buildFinishLaunch(finishDate);
        launch.finish(event);
    }
}
