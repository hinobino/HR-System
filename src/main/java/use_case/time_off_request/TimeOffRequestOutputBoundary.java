package use_case.time_off_request;

import entity.TimeOffRequest;

import java.util.List;

public interface TimeOffRequestOutputBoundary {
    void presentRequestStatus(TimeOffRequestOutputData outputData);
    void presentAllRequests(List<TimeOffRequest> requests);

    void requestApproved(TimeOffRequest request);
    void requestDenied(TimeOffRequest request);
    void requestSubmitted(TimeOffRequest request);

}
