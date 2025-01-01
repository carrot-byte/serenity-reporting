package com.github.carrotbyte.configuration;

import net.thucydides.model.domain.TestOutcome;

import java.util.stream.Stream;

public interface ProvidesTestOutcomes {
    Stream<TestOutcome> getTestOutcomes();
}
