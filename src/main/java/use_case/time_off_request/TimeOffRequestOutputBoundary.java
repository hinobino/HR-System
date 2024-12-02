package use_case.time_off_request;


public interface TimeOffRequestOutputBoundary {
    void presentRequestStatus(TimeOffRequestOutputData outputData);
    void presentAllRequests(TimeOffRequestOutputData outputData);
}
