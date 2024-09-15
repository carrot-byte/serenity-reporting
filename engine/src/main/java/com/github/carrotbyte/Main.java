package com.github.carrotbyte;


import com.github.carrotbyte.factories.LaunchEventsFactory;
import com.github.carrotbyte.factories.SuiteEventsFactory;

public class Main {
    public static void main(String[] args) {
        // Explicit configuration building
        ExporterConfiguration configuration = ExporterConfiguration.withDefaults()
                .useLaunchEventsFactory(LaunchEventsFactory.PARAMETERS_BASED_IMPLEMENTATION)
                .useSuiteEventsFactory(SuiteEventsFactory.DEFAULT_IMPLEMENTATION);
        // This is exactly how plugins will build and start an export process
        ReportExporter.create(configuration).process();
    }
}
