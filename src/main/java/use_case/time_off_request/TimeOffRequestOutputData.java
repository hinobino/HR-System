package use_case.time_off_request;

import entity.TimeOffRequest;

public class TimeOffRequestOutputData {
    private final String status;
    private final TimeOffRequest timeOffRequest;

    public TimeOffRequestOutputData(String status, TimeOffRequest timeOffRequest) {
        this.status = status;
        this.timeOffRequest = timeOffRequest;
    }

    public String getStatus() {
        return status;
    }

    public TimeOffRequest getTimeOffRequest() {
        return timeOffRequest;
    }
}
