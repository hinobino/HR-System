package interface_adapter.time_off;

import interface_adapter.TimeOffRequestViewModel;


import entity.TimeOffRequest;
import use_case.time_off_request.TimeOffRequestOutputBoundary;
import use_case.time_off_request.TimeOffRequestOutputData;

import java.util.List;

public class TimeOffRequestPresenter implements TimeOffRequestOutputBoundary {
    private final TimeOffRequestViewModel viewModel;

    public TimeOffRequestPresenter(TimeOffRequestViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentRequestStatus(TimeOffRequestOutputData outputData) {
        TimeOffRequest request = outputData.getTimeOffRequest();
        viewModel.updateRequest(request);
    }

    @Override
    public void presentAllRequests(TimeOffRequestOutputData outputData) {
        List<TimeOffRequest> requests = outputData.getRequestList();
        viewModel.setRequestList(requests);
    }
}
