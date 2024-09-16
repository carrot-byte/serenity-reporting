package com.github.carrotbyte.factories;

import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import net.thucydides.model.domain.TestOutcome;

import java.util.Comparator;
import java.util.List;

public class DefaultSuiteEventsFactory implements SuiteEventsFactory {

    @Override
    public StartTestItemRQ buildStartSuite(List<TestOutcome> outcomes) {
        TestOutcome test = outcomes.stream().min(Comparator.comparing(TestOutcome::getStartTime)).orElseThrow();
        StartTestItemRQ event = new StartTestItemRQ();
        event.setName(test.getScenarioId());
//        event.setStartTime(test.getStartTime());
        event.setType("SUITE");
        return event;
    }

    @Override
    public FinishTestItemRQ buildFinishSuite(List<TestOutcome> outcomes) {
        TestOutcome test = outcomes.stream().max(Comparator.comparing(TestOutcome::getEndTime)).orElseThrow();
        FinishTestItemRQ event = new FinishTestItemRQ();
//        event.setEndTime(test.getEndTime());
        return event;
    }
}
