package com.github.carrotbyte.configuration;

import net.thucydides.model.reports.TestOutcomeStream;

public interface ProvidesTestOutcomes {
    TestOutcomeStream getTestOutcomes();
}
