package com.github.carrotbyte.factories;

import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;

import java.util.Date;

public class DefaultLaunchEventsFactory implements LaunchEventsFactory {

    @Override
    public StartLaunchRQ buildStartLaunch(Date startTime) {
        return new StartLaunchRQ();
    }

    @Override
    public FinishExecutionRQ buildFinishLaunch(Date endTime) {
        return new FinishExecutionRQ();
    }
}
