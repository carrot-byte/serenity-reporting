package com.github.carrotbyte;

import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import com.github.carrotbyte.configuration.ExporterConfiguration;
import com.github.carrotbyte.factories.LaunchEventsFactory;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;

public class ReportExporter {

    private ExporterConfiguration configuration;

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
        // TODO: Use real start date
        StartLaunchRQ event = factory.buildStartLaunch(Date.from(Instant.now()));
        return reportPortal.newLaunch(event);
    }

    private void finishLaunch(Launch launch, LaunchEventsFactory factory) {
        // TODO: Use real end date here
        FinishExecutionRQ event = factory.buildFinishLaunch(Date.from(Instant.now()));
        launch.finish(event);
    }
}
