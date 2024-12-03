package use_case.manager_time_off;

import data_access.TimeOffRequestDataAccessObject;
import entity.TimeOffRequest;

import java.util.List;

public class ManagerTimeOffRequestInteractor {
    private final TimeOffRequestDataAccessObject dataAccessObject;
    private final ManagerTimeOffRequestOutputBoundary outputBoundary;

    public ManagerTimeOffRequestInteractor(TimeOffRequestDataAccessObject dataAccessObject, ManagerTimeOffRequestOutputBoundary outputBoundary) {
        this.dataAccessObject = dataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    // Approve a time-off request by request ID
    public void approveRequest(String requestId) {
        TimeOffRequest request = dataAccessObject.getRequestById(requestId);
        if (request != null) {
            request.setApproved(true); // Use the new setApproved method
            dataAccessObject.updateRequest(request);
            outputBoundary.requestApproved(request);
        }
    }

    // Deny a time-off request by request ID
    public void denyRequest(String requestId) {
        TimeOffRequest request = dataAccessObject.getRequestById(requestId);
        if (request != null) {
            request.setApproved(false); // Use the new setApproved method
            dataAccessObject.updateRequest(request);
            outputBoundary.requestDenied(request);
        }
    }

    // Load all requests for the manager
    public void loadAllRequestsForManager() {
        List<TimeOffRequest> requests = dataAccessObject.getAllRequests();
        outputBoundary.presentAllRequests(requests);
    }
}
