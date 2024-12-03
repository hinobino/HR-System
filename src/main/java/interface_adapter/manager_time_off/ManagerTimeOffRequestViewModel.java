package interface_adapter.manager_time_off;

import entity.TimeOffRequest;

import java.beans.PropertyChangeSupport;
import java.util.List;

public class ManagerTimeOffRequestViewModel {
    private List<TimeOffRequest> requestList;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public List<TimeOffRequest> getRequestList() {
        return requestList;
    }

    public void updateRequestList(List<TimeOffRequest> newRequests) {
        List<TimeOffRequest> oldRequests = this.requestList;
        this.requestList = newRequests;
        support.firePropertyChange("requestList", oldRequests, newRequests);
    }

    public void setApprovalStatus(String requestId, String status) {
        for (TimeOffRequest request : requestList) {
            if (request.getRequestId().equals(requestId)) {
                String oldStatus = request.getStatus();
                request.setStatus(status);
                support.firePropertyChange("requestStatus", oldStatus, status);
            }
        }
    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
