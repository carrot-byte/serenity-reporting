package com.github.carrotbyte;

import com.epam.reportportal.service.Launch;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.github.carrotbyte.factories.SuiteEventsFactory;
import io.reactivex.Maybe;
import net.thucydides.model.domain.TestOutcome;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// TODO: Each suite exporter represent independent unit of work, so multithreading might be beneficial
public class SuiteExporter {

    // If test outcomes aren't loaded yet we can pass of files and delegate loading to suite exporter
    private final List<TestOutcome> tests;
    private final Launch launch;
    private final ExporterConfiguration configuration;

    public SuiteExporter(List<TestOutcome> tests, Launch launch, ExporterConfiguration configuration) {
        this.tests = tests;
        this.launch = launch;
        this.configuration = configuration;
    }

    public void export() {
        List<TestOutcome> normalized = tests.stream().flatMap(test -> normalize(test).stream()).collect(Collectors.toList());
        SuiteEventsFactory factory = configuration.suiteEventsFactory();
        // Start suite
        StartTestItemRQ startSuiteEvent = factory.buildStartSuite(normalized);
        Maybe<String> suiteId = launch.startTestItem(startSuiteEvent);
        // Process all tests within a suite
        normalized.forEach(test -> exportTest(suiteId, test, configuration));
        // Finish suite
        FinishTestItemRQ finishSuiteEvent = factory.buildFinishSuite(normalized);
        launch.finishTestItem(suiteId, finishSuiteEvent);
    }

    private List<TestOutcome> normalize(TestOutcome outcome) {
        if (!outcome.isDataDriven()) {
            return Collections.singletonList(outcome);
        }
        // TODO: Implement split
        return Collections.singletonList(outcome);
    }

    private void exportTest(Maybe<String> parentId, TestOutcome outcome, ExporterConfiguration configuration) {
        // Delegate down to test, step exporters
    }
}
