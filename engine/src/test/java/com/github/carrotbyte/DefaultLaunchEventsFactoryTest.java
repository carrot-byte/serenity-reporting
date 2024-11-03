package com.github.carrotbyte;

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
        StartLaunchRQ newLaunch = defaultLaunchEventsFactory.buildStartLaunch(new Date());
        Assertions.assertNotNull(newLaunch);
    }
}
