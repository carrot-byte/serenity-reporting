package com.github.carrotbyte;

import com.github.carrotbyte.configuration.ProvidesTestOutcomes;
import net.thucydides.model.reports.TestOutcomeStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathBasedTestOutcomesProvider implements ProvidesTestOutcomes {
    private final static Logger LOGGER = LoggerFactory.getLogger(PathBasedTestOutcomesProvider.class);

    private final Path reportDirectory;

    public PathBasedTestOutcomesProvider(Path reportDirectory) {
        this.reportDirectory = reportDirectory;
    }

    @Override
    public TestOutcomeStream getTestOutcomes() {
        if (!Files.isDirectory(reportDirectory)) {
            LOGGER.error("Report directory is not a folder!");
            throw new IllegalArgumentException();
        }
        try {
            return TestOutcomeStream.testOutcomesInDirectory(reportDirectory);
        } catch (IOException e) {
            LOGGER.error("Failed to read report directory folder or files!");
            throw new IllegalArgumentException(e);
        }
    }
}
