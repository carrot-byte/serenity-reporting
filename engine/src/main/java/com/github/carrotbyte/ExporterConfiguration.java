package com.github.carrotbyte;

import com.github.carrotbyte.factories.LaunchEventsFactory;
import com.github.carrotbyte.factories.SuiteEventsFactory;

public class ExporterConfiguration {

    // TODO: Define all other configuration properties
    private LaunchEventsFactory launchEventsFactory;
    private SuiteEventsFactory suiteEventsFactory;

    private ExporterConfiguration(LaunchEventsFactory launchEventsFactory, SuiteEventsFactory suiteEventsFactory) {
        this.launchEventsFactory = launchEventsFactory;
        this.suiteEventsFactory = suiteEventsFactory;
    }

    public static ExporterConfiguration withDefaults() {
        return new ExporterConfiguration(
                LaunchEventsFactory.PARAMETERS_BASED_IMPLEMENTATION,
                SuiteEventsFactory.DEFAULT_IMPLEMENTATION
        );
    }

    public ExporterConfiguration useLaunchEventsFactory(LaunchEventsFactory launchEventsFactory) {
        this.launchEventsFactory = launchEventsFactory;
        return this;
    }

    public LaunchEventsFactory launchEventsFactory() {
        return launchEventsFactory;
    }

    public ExporterConfiguration useSuiteEventsFactory(SuiteEventsFactory suiteEventsFactory) {
        this.suiteEventsFactory = suiteEventsFactory;
        return this;
    }

    public SuiteEventsFactory suiteEventsFactory() {
        return suiteEventsFactory;
    }
}
