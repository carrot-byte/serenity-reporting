package com.github.carrotbyte.factories;

import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import net.thucydides.model.domain.TestOutcome;

import java.util.List;

public interface SuiteEventsFactory {
    StartTestItemRQ buildStartSuite(List<TestOutcome> outcomes);

    FinishTestItemRQ buildFinishSuite(List<TestOutcome> outcomes);

    SuiteEventsFactory DEFAULT_IMPLEMENTATION = new DefaultSuiteEventsFactory();
}
