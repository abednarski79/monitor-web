package eu.appbucket.monitor.web.service;

import eu.appbucket.monitor.web.core.domain.ManifestDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManifestVersionControllerTest {

    private ManifestVersionController controller;
    private ManifestDetails expectedManifestDetails;

    @Before
    public void setup() {
        expectedManifestDetails = new ManifestDetails();
        expectedManifestDetails.setApplicationVersion("1.0");
        controller = new ManifestVersionController() {
            @Override
            protected ManifestDetails loadManifestDetails() {
                return expectedManifestDetails;
            }
        };
    }

    @Test
    public void testGetManifestDetails() {
        ManifestDetails actualManifestDetails = controller.getManifestDetails();
        Assert.assertEquals(expectedManifestDetails.getApplicationVersion(),
                actualManifestDetails.getApplicationVersion());
    }
}
