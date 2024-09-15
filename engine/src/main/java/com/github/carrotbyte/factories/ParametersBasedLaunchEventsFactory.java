package com.github.carrotbyte.factories;

import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.launch.Mode;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;

import java.util.Collections;
import java.util.Date;

public class ParametersBasedLaunchEventsFactory implements LaunchEventsFactory {

    @Override
    public StartLaunchRQ buildStartLaunch(Date startTime) {
        // TODO: Event should be created based on data from caller. E. g. plugin configuration
        StartLaunchRQ event = new StartLaunchRQ();
        event.setName("My fancy launch");
        event.setStartTime(startTime);
        event.setMode(Mode.DEFAULT);
        event.setAttributes(Collections.emptySet());
        event.setDescription("Some description");
        // TODO: Think about launch re-run use cases within plugin lifecycle
        event.setRerun(false);
        event.setRerunOf(null);
        return event;
    }

    @Override
    public FinishExecutionRQ buildFinishLaunch(Date endTime) {
        FinishExecutionRQ event = new FinishExecutionRQ();
        event.setEndTime(endTime);
        return event;
    }
}
