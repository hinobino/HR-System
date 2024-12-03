package use_case.manager_time_off;

import entity.TimeOffRequest;

import java.util.List;

public interface ManagerTimeOffRequestDataAccessInterface {

    /**
     * Approve a time-off request by ID.
     * @param requestId The ID of the request to approve.
     */
    void approveRequest(String requestId);

    /**
     * Deny a time-off request by ID.
     * @param requestId The ID of the request to deny.
     */
    void denyRequest(String requestId);

    /**
     * Get a list of all time-off requests.
     * @return A list of all time-off requests.
     */
    List<TimeOffRequest> getAllRequests();

    /**
     * Get time-off requests that are pending approval.
     * @return A list of pending time-off requests.
     */
    List<TimeOffRequest> getPendingRequests();
}
