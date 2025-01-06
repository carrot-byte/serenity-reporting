package com.github.carrotbyte;


import com.epam.reportportal.listeners.ListenerParameters;
import com.github.carrotbyte.configuration.ExporterConfiguration;
import com.github.carrotbyte.configuration.ProvidesTestOutcomes;
import com.github.carrotbyte.factories.LaunchEventsFactory;
import com.github.carrotbyte.factories.SuiteEventsFactory;

import java.net.URI;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        // Explicit configuration building
        ExporterConfiguration configuration = ExporterConfiguration
                .create(Main::createListenerParameters, createTestOutcomesProvider())
                .useLaunchEventsFactory(LaunchEventsFactory.DEFAULT_IMPLEMENTATION)
                .useSuiteEventsFactory(SuiteEventsFactory.DEFAULT_IMPLEMENTATION);
        // This is exactly how plugins will build and start an export process
        ReportExporter.create(configuration).export();
    }

    private static ListenerParameters createListenerParameters() {
        // Caller side must define a way of listener parameters creation
        // E.g. plugin will fill it from its own configuration
        ListenerParameters parameters = new ListenerParameters();
        parameters.setEnable(true);
        parameters.setBaseUrl("http://localhost:8080");
        parameters.setApiKey("api-key");
        parameters.setProjectName("project-name");
        return parameters;
    }

    private static ProvidesTestOutcomes createTestOutcomesProvider() {
        Path path = Path.of(URI.create("file://output/serenity"));
        return new PathBasedTestOutcomesProvider(path);
    }
}
