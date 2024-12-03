package use_case.time_off_request;

import entity.TimeOffRequest;

import java.util.List;

public interface TimeOffRequestDataAccessInterface {

    /**
     * Save a time-off request.
     * @param request The time-off request to save.
     */
    void saveRequest(TimeOffRequest request);

    /**
     * Retrieve a time-off request by its unique ID.
     * @param requestId The ID of the request.
     * @return The TimeOffRequest associated with the given request ID.
     */
    TimeOffRequest getRequestById(String requestId);

    /**
     * Update an existing time-off request.
     * @param request The request object containing updated data.
     */
    void updateRequest(TimeOffRequest request);

    /**
     * Remove a time-off request by its ID.
     * @param requestId The ID of the request to remove.
     */
    void removeRequest(String requestId);

    /**
     * Retrieve all pending time-off requests.
     * @return List of all pending time-off requests.
     */
    List<TimeOffRequest> getPendingRequests();

    /**
     * Retrieve time-off requests for a specific employee by their employee ID.
     * @param employeeId The employee ID to search for requests.
     * @return A list of TimeOffRequests associated with the given employee ID.
     */
    List<TimeOffRequest> getRequestsByEmployeeId(String employeeId);
}
