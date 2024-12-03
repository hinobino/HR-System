package use_case.time_off_request;

import data_access.TimeOffRequestDataAccessObject;
import entity.TimeOffRequest;

import java.util.List;

public class TimeOffRequestInteractor implements TimeOffRequestInputBoundary {
    private final TimeOffRequestDataAccessObject dataAccessObject;
    private final TimeOffRequestOutputBoundary outputBoundary;

    public TimeOffRequestInteractor(TimeOffRequestDataAccessObject dataAccessObject, TimeOffRequestOutputBoundary outputBoundary) {
        this.dataAccessObject = dataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void submitRequest(TimeOffRequestInputData inputData) {
        // Implementation for submitting a time-off request
    }

    @Override
    public void approveRequest(String requestId) {
        TimeOffRequest request = dataAccessObject.getRequestById(requestId);
        if (request != null) {
            request.setApproved(true);
            dataAccessObject.updateRequest(request);
            outputBoundary.requestApproved(request);
        }
    }

    @Override
    public void denyRequest(String requestId) {
        TimeOffRequest request = dataAccessObject.getRequestById(requestId);
        if (request != null) {
            request.setApproved(false);
            dataAccessObject.updateRequest(request);
            outputBoundary.requestDenied(request);
        }
    }

    @Override
    public void createTimeOffRequest(String startDate, String endDate) {
        // Implementation for creating a time-off request
    }

    @Override
    public List<TimeOffRequest> getAllRequests() {
        // Assuming dataAccessObject has a method to retrieve all requests
        return dataAccessObject.getAllRequests();
    }
}
