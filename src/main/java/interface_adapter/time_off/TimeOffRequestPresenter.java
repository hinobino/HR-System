package interface_adapter.time_off;

import entity.TimeOffRequest;
import interface_adapter.time_off.TimeOffRequestViewModel; // Correct import path
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
    public void presentAllRequests(TimeOffRequestOutputData outputData) {
        // Retrieves the list of all TimeOffRequests and updates the view model.
        List<TimeOffRequest> requests = outputData.getRequestList();
        viewModel.setRequestList(requests);
    }
}
