package interface_adapter.time_off;

import use_case.time_off_request.TimeOffRequestInputBoundary;
import use_case.time_off_request.TimeOffRequestInputData;

import java.time.LocalDate;

public class TimeOffRequestController {
    private final TimeOffRequestInputBoundary inputBoundary;

    public TimeOffRequestController(TimeOffRequestInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void submitRequest(String employeeId, LocalDate startDate, LocalDate endDate) {
        TimeOffRequestInputData inputData = new TimeOffRequestInputData(employeeId, startDate, endDate);
        inputBoundary.submitRequest(inputData);
    }

    public void approveRequest(String requestId) {
        inputBoundary.approveRequest(requestId);
    }

    public void denyRequest(String requestId) {
        inputBoundary.denyRequest(requestId);
    }
}