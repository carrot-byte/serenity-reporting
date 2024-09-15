package com.github.carrotbyte.factories;

import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;

import java.util.Date;

public interface LaunchEventsFactory {
    StartLaunchRQ buildStartLaunch(Date startTime);

    FinishExecutionRQ buildFinishLaunch(Date endTime);

    LaunchEventsFactory PARAMETERS_BASED_IMPLEMENTATION = new ParametersBasedLaunchEventsFactory();
}
