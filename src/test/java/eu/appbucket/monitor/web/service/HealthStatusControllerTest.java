package eu.appbucket.monitor.web.service;

import eu.appbucket.monitor.web.core.domain.HealthStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HealthStatusControllerTest {

    private HealthStatusController controller;

    @Before
    public void setup() {
        controller = new HealthStatusController();
    }

    @Test
    public void testGetHealthStatus() {
        HealthStatus expectedHealthStatus = new HealthStatus();
        expectedHealthStatus.setStatus(HealthStatus.Status.OK);
        HealthStatus actualHealthStatus = controller.getHealthStatus();
        Assert.assertEquals(expectedHealthStatus.getStatus(), actualHealthStatus.getStatus());
    }
}
