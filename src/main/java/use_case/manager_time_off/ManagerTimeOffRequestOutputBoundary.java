package use_case.manager_time_off;

import entity.TimeOffRequest;
import java.util.List;

public interface ManagerTimeOffRequestOutputBoundary {
    void presentAllRequests(List<TimeOffRequest> requests);
    void requestApproved(TimeOffRequest request);
    void requestDenied(TimeOffRequest request);
    void requestSubmitted(TimeOffRequest request);
}
