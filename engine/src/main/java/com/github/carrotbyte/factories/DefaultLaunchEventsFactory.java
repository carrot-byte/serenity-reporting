package com.github.carrotbyte.factories;

import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;

import java.util.Date;

public class DefaultLaunchEventsFactory implements LaunchEventsFactory {

    @Override
    public StartLaunchRQ buildStartLaunch(Date startTime) {
        StartLaunchRQ startLaunchRQ = new StartLaunchRQ();
        startLaunchRQ.setStartTime(startTime);
        return startLaunchRQ;
    }

    @Override
    public FinishExecutionRQ buildFinishLaunch(Date endTime) {
        FinishExecutionRQ finishExecutionRQ = new FinishExecutionRQ();
        finishExecutionRQ.setEndTime(endTime);
        return finishExecutionRQ;
    }
}
