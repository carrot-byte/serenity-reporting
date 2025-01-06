package com.github.carrotbyte;

import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import com.github.carrotbyte.factories.DefaultLaunchEventsFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DefaultLaunchEventsFactoryTest {
    private DefaultLaunchEventsFactory defaultLaunchEventsFactory;

    @BeforeEach
    public void setup() {
        defaultLaunchEventsFactory = new DefaultLaunchEventsFactory();
    }

    @Test
    public void testBuildStartLaunch() {
        Date currentDate = new Date();
        StartLaunchRQ launch = defaultLaunchEventsFactory.buildStartLaunch(currentDate);
        Assertions.assertEquals(launch.getStartTime(), currentDate);
    }

    @Test
    public void testBuildFinishLaunch() {
        Date currentDate = new Date();
        FinishExecutionRQ launch = defaultLaunchEventsFactory.buildFinishLaunch(currentDate);
        Assertions.assertEquals(launch.getEndTime(), currentDate);
    }
}
