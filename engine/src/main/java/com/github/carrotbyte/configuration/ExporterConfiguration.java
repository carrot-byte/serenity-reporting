package com.github.carrotbyte.configuration;

import com.github.carrotbyte.factories.LaunchEventsFactory;
import com.github.carrotbyte.factories.SuiteEventsFactory;

public class ExporterConfiguration {

    // TODO: Define all other configuration properties
    private ProvidesListenerParameters providesListenerParameters;
    private ProvidesTestOutcomes providesTestOutcomes;
    private LaunchEventsFactory launchEventsFactory;
    private SuiteEventsFactory suiteEventsFactory;

    private ExporterConfiguration(
            ProvidesListenerParameters provider,
            ProvidesTestOutcomes providesTestOutcomes,
            LaunchEventsFactory launchEventsFactory,
            SuiteEventsFactory suiteEventsFactory
    ) {
        this.providesListenerParameters = provider;
        this.providesTestOutcomes = providesTestOutcomes;
        this.launchEventsFactory = launchEventsFactory;
        this.suiteEventsFactory = suiteEventsFactory;
    }

    public static ExporterConfiguration create(
            ProvidesListenerParameters providesListenerParameters,
            ProvidesTestOutcomes providesTestOutcomes
    ) {
        return new ExporterConfiguration(
                providesListenerParameters,
                providesTestOutcomes,
                LaunchEventsFactory.DEFAULT_IMPLEMENTATION,
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

    public ProvidesListenerParameters parametersProvider() {
        return providesListenerParameters;
    }

    public ProvidesTestOutcomes testOutcomesProvider() {
        return providesTestOutcomes;
    }
}
