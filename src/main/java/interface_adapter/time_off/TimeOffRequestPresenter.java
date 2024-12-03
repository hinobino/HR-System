package interface_adapter.time_off;

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
        // Retrieves a single TimeOffRequest and updates the view model.
        TimeOffRequest request = outputData.getTimeOffRequest();
        viewModel.updateRequest(request);
    }

    @Override
    public void presentAllRequests(List<TimeOffRequest> requests) {
        viewModel.updateRequestList(requests);
    }

    @Override
    public void requestSubmitted(TimeOffRequest request) {
        viewModel.updateRequest(request);
    }

    @Override
    public void requestApproved(TimeOffRequest request) {
        viewModel.updateRequest(request);
    }

    @Override
    public void requestDenied(TimeOffRequest request) {
        viewModel.updateRequest(request);
    }
}
