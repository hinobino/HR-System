package use_case.time_off_request;

public interface TimeOffRequestInputBoundary {
    void submitRequest(TimeOffRequestInputData inputData);
    void approveRequest(String requestId);
    void denyRequest(String requestId);
}