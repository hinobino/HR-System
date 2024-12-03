package interface_adapter.time_off;

import entity.TimeOffRequest;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.util.List;

public class TimeOffRequestViewModel extends ViewModel<List<TimeOffRequest>> {
    private List<TimeOffRequest> requestList;

    public TimeOffRequestViewModel() {
        super("TimeOffRequestViewModel");
    }

    public List<TimeOffRequest> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<TimeOffRequest> requestList) {
        List<TimeOffRequest> oldRequestList = this.requestList;
        this.requestList = requestList;
        this.firePropertyChanged("requestList", oldRequestList, requestList);
    }

    public void updateRequest(TimeOffRequest request) {
        if (requestList != null) {
            for (int i = 0; i < requestList.size(); i++) {
                if (requestList.get(i).getRequestId().equals(request.getRequestId())) {
                    TimeOffRequest oldRequest = requestList.get(i);
                    requestList.set(i, request);
                    this.firePropertyChanged("requestList", oldRequest, request);
                    break;
                }
            }
        }
    }

    public void updateRequestList(List<TimeOffRequest> requests) {
        this.requestList = requests;
        this.firePropertyChanged("requestList", null, requests);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        super.addPropertyChangeListener(pcl);
    }
}
