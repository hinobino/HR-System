package interface_adapter.time_off;

import entity.TimeOffRequest;

import java.util.List;

public class TimeOffRequestState {
    private final List<TimeOffRequest> requestList;

    public TimeOffRequestState(List<TimeOffRequest> requestList) {
        this.requestList = requestList;
    }

    public List<TimeOffRequest> getRequestList() {
        return requestList;
    }
}