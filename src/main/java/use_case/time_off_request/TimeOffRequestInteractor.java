package use_case.time_off_request;

import data_access.TimeOffRequestDataAccessObject;
import entity.TimeOffRequest;

import java.time.LocalDate;
import java.util.UUID;

public class TimeOffRequestInteractor implements TimeOffRequestInputBoundary {
    private final TimeOffRequestDataAccessObject dataAccessObject;
    private final TimeOffRequestOutputBoundary outputBoundary;

    public TimeOffRequestInteractor(TimeOffRequestDataAccessObject dataAccessObject, TimeOffRequestOutputBoundary outputBoundary) {
        this.dataAccessObject = dataAccessObject;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void submitRequest(TimeOffRequestInputData inputData) {
        String requestId = UUID.randomUUID().toString();
        LocalDate requestDate = LocalDate.now();
        TimeOffRequest request = new TimeOffRequest(requestId, inputData.getEmployeeId(), requestDate, inputData.getStartDate(), inputData.getEndDate());
        dataAccessObject.saveRequest(request);
        outputBoundary.presentRequestStatus(new TimeOffRequestOutputData(request, null));
    }

    @Override
    public void approveRequest(String requestId) {
        TimeOffRequest request = dataAccessObject.getRequestById(requestId);
        if (request != null) {
            request.setApproved(true);
            outputBoundary.presentRequestStatus(new TimeOffRequestOutputData(request, null));
        }
    }

    @Override
    public void denyRequest(String requestId) {
        TimeOffRequest request = dataAccessObject.getRequestById(requestId);
        if (request != null) {
            dataAccessObject.saveRequest(request); // Update the request status
            outputBoundary.presentRequestStatus(new TimeOffRequestOutputData(request, null));
        }
    }
}