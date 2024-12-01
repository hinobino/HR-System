package use_case.time_off_request;

import entity.TimeOffRequest;

import java.util.List;

public class TimeOffRequestOutputData {
    private final TimeOffRequest timeOffRequest;
    private final List<TimeOffRequest> requestList;

    public TimeOffRequestOutputData(TimeOffRequest timeOffRequest, List<TimeOffRequest> requestList) {
        this.timeOffRequest = timeOffRequest;
        this.requestList = requestList;
    }

    public TimeOffRequest getTimeOffRequest() {
        return timeOffRequest;
    }

    public List<TimeOffRequest> getRequestList() {
        return requestList;
    }
}
