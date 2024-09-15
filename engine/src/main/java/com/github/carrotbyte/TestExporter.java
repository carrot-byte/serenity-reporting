package com.github.carrotbyte;

import com.epam.reportportal.service.Launch;
import com.github.carrotbyte.configuration.ExporterConfiguration;
import io.reactivex.Maybe;
import net.thucydides.model.domain.TestOutcome;
import net.thucydides.model.domain.TestStep;

import java.util.List;

public class TestExporter {

    private Launch launch;
    private ExporterConfiguration configuration;
    private Maybe<String> parentId;
    private TestOutcome outcome;

    public TestExporter(Launch launch, ExporterConfiguration configuration, Maybe<String> parentId, TestOutcome outcome) {
        this.launch = launch;
        this.configuration = configuration;
        this.parentId = parentId;
        this.outcome = outcome;
    }

    public void export() {
        // Start a test and save its id
        // launch.startTestItem(parentId, event);
        Maybe<String> testId = Maybe.empty();
        // Process all steps within test
        exportSteps(testId, outcome.getTestSteps(), launch, configuration);
        // Finish test
        // launch.finishTestItem(testId, event);
    }

    private void exportSteps(Maybe<String> testId, List<TestStep> steps, Launch launch, ExporterConfiguration configuration) {
        // Delegate to step exporter?
    }
}
