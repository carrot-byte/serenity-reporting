package com.github.carrotbyte;

import com.epam.reportportal.service.Launch;
import com.github.carrotbyte.configuration.ExporterConfiguration;
import io.reactivex.Maybe;
import net.thucydides.model.domain.TestOutcome;

import java.util.List;

// TODO: Each suite exporter represent independent unit of work, so multithreading might be beneficial
public class SuiteExporter {

    // If test outcomes aren't loaded yet we can pass of files and delegate loading to suite exporter
    private final List<TestOutcome> testsInSuite;
    private final Launch launch;
    private final ExporterConfiguration configuration;

    public SuiteExporter(List<TestOutcome> testsInSuite, Launch launch, ExporterConfiguration configuration) {
        this.testsInSuite = testsInSuite;
        this.launch = launch;
        this.configuration = configuration;
    }

    public void export() {
        // TODO: Start a suite and save its id
        Maybe<String> suiteId = Maybe.empty();
        // Process all tests within a suite
        // TODO: DD tests should be converted to standalone like tests before processing
        testsInSuite.forEach(test -> exportTest(suiteId, test, configuration));
        // TODO: Finish suite for saved ID
    }

    private void exportTest(Maybe<String> parentId, TestOutcome outcome, ExporterConfiguration configuration) {
        // Delegate down to test exporter
        new TestExporter(launch, configuration, parentId, outcome).export();
    }
}
