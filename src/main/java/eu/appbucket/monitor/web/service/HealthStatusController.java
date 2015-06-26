package eu.appbucket.monitor.web.service;
import eu.appbucket.monitor.web.core.domain.HealthStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO : add unit test
@RestController
public class HealthStatusController {

    private static final Logger logger = LoggerFactory.getLogger(HealthStatusController.class);

    @RequestMapping("/status")
    public HealthStatus getHealthStatus() {
        HealthStatus status = new HealthStatus();
        status.setStatus(HealthStatus.Status.OK);
        return status;
    }
}
