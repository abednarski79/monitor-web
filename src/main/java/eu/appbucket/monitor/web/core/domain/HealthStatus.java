package eu.appbucket.monitor.web.core.domain;

public class HealthStatus {

    public enum Status {OK};

    private Status status;

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
