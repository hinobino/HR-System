package data_access;

import entity.TimeOffRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeOffRequestDataAccessObject {
    private final Map<String, TimeOffRequest> requests = new HashMap<>();

    public void saveRequest(TimeOffRequest request) {
        requests.put(request.getRequestId(), request);
    }

    public TimeOffRequest getRequestById(String requestId) {
        return requests.get(requestId);
    }

    public List<TimeOffRequest> getRequestsByEmployeeId(String employeeId) {
        return requests.values().stream()
                .filter(request -> request.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }
}