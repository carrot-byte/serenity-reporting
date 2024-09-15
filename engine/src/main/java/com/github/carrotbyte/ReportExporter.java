package com.github.carrotbyte;

import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import com.github.carrotbyte.factories.LaunchEventsFactory;

import java.time.Instant;
import java.util.Date;

public class ReportExporter {

    private ExporterConfiguration configuration;

    private ReportExporter(ExporterConfiguration configuration) {
        this.configuration = configuration;
    }

    public static ReportExporter create(ExporterConfiguration configuration) {
        return new ReportExporter(configuration);
    }

    public void process() {
        // At this point we need to have all test outcomes metadata, or load them all into memory
        Launch launch = createLaunch(configuration);
        //
        finishLaunch(launch, configuration);
    }

    private Launch createLaunch(ExporterConfiguration configuration) {
        ListenerParameters parameters = new ListenerParameters();
        // TODO: Those values should come from configuration
        parameters.setBaseUrl("http://localhost:8080");
        parameters.setApiKey("key");
        parameters.setProjectName("project");
        ReportPortal reportPortal = ReportPortal.builder().withParameters(parameters).build();
        LaunchEventsFactory factory = configuration.launchEventsFactory();
        // TODO: Use real start date
        StartLaunchRQ event = factory.buildStartLaunch(Date.from(Instant.now()));
        return reportPortal.newLaunch(event);
    }

    private void finishLaunch(Launch launch, ExporterConfiguration configuration) {
        LaunchEventsFactory factory = configuration.launchEventsFactory();
        FinishExecutionRQ event = factory.buildFinishLaunch(Date.from(Instant.now()));
        launch.finish(event);
    }
}
