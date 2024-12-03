package interface_adapter.manager_time_off;

import entity.TimeOffRequest;

import java.util.List;

public class ManagerTimeOffRequestState {
    private List<TimeOffRequest> pendingRequests;

    public List<TimeOffRequest> getPendingRequests() {
        return pendingRequests;
    }

    public void setPendingRequests(List<TimeOffRequest> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    // Additional fields and methods can be added here to keep track of UI states
}
