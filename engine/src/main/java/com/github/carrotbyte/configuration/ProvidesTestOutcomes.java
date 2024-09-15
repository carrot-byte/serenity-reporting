package com.github.carrotbyte.configuration;

import net.thucydides.model.reports.TestOutcomes;

import java.util.stream.Stream;

public interface ProvidesTestOutcomes {
    Stream<TestOutcomes> getTestOutcomes();
}
