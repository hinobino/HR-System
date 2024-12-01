package interface_adapter.time_off;
import entity.TimeOffRequest;
import java.beans.PropertyChangeListener;
import java.util.List;

public class TimeOffRequestViewModel extends ViewModel<List<TimeOffRequest>> {
    private List<TimeOffRequest> requestList;

    public List<TimeOffRequest> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<TimeOffRequest> requestList) {
        List<TimeOffRequest> oldRequestList = this.requestList;
        this.requestList = requestList;
        support.firePropertyChange("requestList", oldRequestList, requestList);
    }

    public void updateRequest(TimeOffRequest request) {
        // Add logic to update request
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
}
