package com.github.carrotbyte;

import com.github.carrotbyte.configuration.ProvidesTestOutcomes;
import net.thucydides.model.reports.TestOutcomes;

import java.nio.file.Path;
import java.util.stream.Stream;

public class PathBasedTestOutcomesProvider implements ProvidesTestOutcomes {

    private Path reportDirectory;

    public PathBasedTestOutcomesProvider(Path reportDirectory) {
        this.reportDirectory = reportDirectory;
    }

    @Override
    public Stream<TestOutcomes> getTestOutcomes() {
        // TODO: Read test outcomes for given path
        return Stream.empty();
    }
}
