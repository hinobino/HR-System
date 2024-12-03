package use_case.manager_time_off;

import entity.TimeOffRequest;

import java.util.List;

/**
 * Output Data for ManagerTimeOffRequest Use Case.
 */
public class ManagerTimeOffRequestOutputData {
    private String requestId;
    private String statusMessage;
    private List<TimeOffRequest> pendingRequests;

    // Constructor for approval/denial results
    public ManagerTimeOffRequestOutputData(String requestId, String statusMessage) {
        this.requestId = requestId;
        this.statusMessage = statusMessage;
    }

    // Constructor for pending requests
    public ManagerTimeOffRequestOutputData(List<TimeOffRequest> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    // Getters
    public String getRequestId() {
        return requestId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public List<TimeOffRequest> getPendingRequests() {
        return pendingRequests;
    }
}
