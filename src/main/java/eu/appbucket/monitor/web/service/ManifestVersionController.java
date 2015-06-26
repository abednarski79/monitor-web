package eu.appbucket.monitor.web.service;

import eu.appbucket.monitor.web.core.domain.ManifestDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// TODO : add unit test

@RestController
public class ManifestVersionController {

    private static final Logger logger = LoggerFactory.getLogger(ManifestVersionController.class);
    private ManifestDetails manifestDetails;

    public ManifestVersionController() throws  Exception {
        manifestDetails = loadManifestDetails();
    }

    private ManifestDetails loadManifestDetails() {
        ManifestDetails manifestDetails = new ManifestDetails();
        InputStream manifestData = ManifestVersionController.class.getResourceAsStream("/META-INF/MANIFEST.MF");
        Properties manifestProperties = new Properties();
        try {
            manifestProperties.load(manifestData);
        } catch (IOException e) {
            logger.error("Can't load manifest file.", e);
        }
        manifestDetails.setApplicationVersion(manifestProperties.getProperty("Manifest-Version"));
        return manifestDetails;
    }

    @RequestMapping("/manifest")
    public ManifestDetails getManifestDetails() {
        return manifestDetails;
    }

}
