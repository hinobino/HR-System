package data_access;

import entity.TimeOffRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeOffRequestDataAccessObject {
    private final Map<String, TimeOffRequest> requests = new HashMap<>();
    private final List<TimeOffRequest> requestDatabase = new ArrayList<>();

    // Save a new time-off request
    public void saveRequest(TimeOffRequest request) {
        requestDatabase.add(request);
    }

    // Get a time-off request by ID
    public TimeOffRequest getRequestById(String requestId) {
        return requests.get(requestId);
    }

    // Get all requests by employee ID
    public List<TimeOffRequest> getRequestsByEmployeeId(String employeeId) {
        return requests.values().stream()
                .filter(request -> request.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    public List<TimeOffRequest> getAllRequests() {
        return new ArrayList<>(requestDatabase);
    }

    // Update the status of a time-off request
    public void updateRequest(TimeOffRequest request) {
        requests.put(request.getRequestId(), request);
    }

    // Remove a time-off request by ID
    public void removeRequest(String requestId) {
        requests.remove(requestId);
    }

    // Retrieve all pending time-off requests
    public List<TimeOffRequest> getPendingRequests() {
        return requests.values().stream()
                .filter(request -> !request.isApproved())
                .collect(Collectors.toList());
    }
}
