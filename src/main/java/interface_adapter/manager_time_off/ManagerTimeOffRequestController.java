package interface_adapter.manager_time_off;

import use_case.manager_time_off.ManagerTimeOffRequestInteractor;

public class ManagerTimeOffRequestController {
    private final ManagerTimeOffRequestInteractor interactor;

    // Constructor
    public ManagerTimeOffRequestController(ManagerTimeOffRequestInteractor interactor) {
        this.interactor = interactor;
    }

    // Approve request
    public void approveRequest(String requestId) {
        interactor.approveRequest(requestId);
    }

    // Reject request
    public void rejectRequest(String requestId) {
        interactor.denyRequest(requestId);
    }

    // Load all requests for the manager
    public void loadAllRequestsForManager() {
        interactor.loadAllRequestsForManager();
    }
}
