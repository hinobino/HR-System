package use_case.manager_time_off;

import entity.TimeOffRequest;
import java.util.List;

public interface ManagerTimeOffRequestInputBoundary {
    void approveRequest(String requestId);
    void rejectRequest(String requestId);
    List<TimeOffRequest> viewPendingRequests();
}
