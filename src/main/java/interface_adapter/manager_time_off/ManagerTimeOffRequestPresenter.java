package interface_adapter.manager_time_off;

import entity.TimeOffRequest;

import java.util.List;

public class ManagerTimeOffRequestPresenter {
    private final ManagerTimeOffRequestViewModel viewModel;

    public ManagerTimeOffRequestPresenter(ManagerTimeOffRequestViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void presentRequests(List<TimeOffRequest> requests) {
        viewModel.updateRequestList(requests);
    }

    public void presentApprovalResult(String requestId, boolean approved) {
        String statusMessage = approved ? "Approved" : "Rejected";
        viewModel.setApprovalStatus(requestId, statusMessage);
    }
}
