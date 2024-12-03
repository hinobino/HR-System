package interface_adapter.time_off;

import entity.TimeOffRequest;
import use_case.time_off_request.TimeOffRequestInputBoundary;
import use_case.time_off_request.TimeOffRequestOutputBoundary;

import java.time.LocalDate;
import java.util.List;

public class TimeOffRequestController {
    private final TimeOffRequestInputBoundary inputBoundary;
    private final TimeOffRequestOutputBoundary outputBoundary;

    public TimeOffRequestController(TimeOffRequestInputBoundary inputBoundary, TimeOffRequestOutputBoundary outputBoundary) {
        this.inputBoundary = inputBoundary;
        this.outputBoundary = outputBoundary;
    }

    // Submit a time-off request
    public void submitRequest(String startDate, String endDate) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            inputBoundary.createTimeOffRequest(startDate, endDate);
        } catch (Exception e) {
            System.out.println("Error parsing dates: " + e.getMessage());
        }
    }

    // Approve a time-off request by request ID
    public void approveRequest(String requestId) {
        inputBoundary.approveRequest(requestId);
    }

    // Reject a time-off request by request ID
    public void rejectRequest(String requestId) {
        inputBoundary.denyRequest(requestId);
    }

    // Load all requests for the manager
    public void loadAllRequestsForManager() {
        List<TimeOffRequest> requests = inputBoundary.getAllRequests();
        outputBoundary.presentAllRequests(requests);
    }
}
