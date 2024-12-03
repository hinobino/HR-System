package use_case.time_off_request;

import entity.TimeOffRequest;

import java.util.List;

public interface TimeOffRequestInputBoundary {
    void submitRequest(TimeOffRequestInputData inputData);

    void approveRequest(String requestId);

    void denyRequest(String requestId);

    void createTimeOffRequest(String startDate, String endDate);

    List<TimeOffRequest> getAllRequests();  // Added method to retrieve all requests
}
