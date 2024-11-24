package interface_adapter.schedule_shift;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.ManagerViewModel;
import use_case.schedule_shift.ScheduleShiftOutputBoundary;
import use_case.schedule_shift.ScheduleShiftOutputData;

/**
 * Presenter for the ScheduleShift use case.
 */
public class ScheduleShiftPresenter implements ScheduleShiftOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ScheduleShiftViewModel scheduleShiftViewModel;
    private final ManagerViewModel managerViewModel;

    public ScheduleShiftPresenter(ViewManagerModel viewManagerModel, ScheduleShiftViewModel scheduleShiftViewModel,
                                  ManagerViewModel managerViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.scheduleShiftViewModel = scheduleShiftViewModel;
        this.managerViewModel = managerViewModel;
    }

    @Override
    public void prepareSuccessView(ScheduleShiftOutputData outputData) {
        // on success, switch to the manager view
        final ScheduleShiftState scheduleShiftState = scheduleShiftViewModel.getState();
        scheduleShiftState.setDay(outputData.getDay());
        scheduleShiftState.setStartTime(outputData.getStartTime());
        scheduleShiftState.setEndTime(outputData.getEndTime());
        scheduleShiftState.setEmployee(outputData.getEmployee());
        scheduleShiftViewModel.setState(scheduleShiftState);
        scheduleShiftViewModel.firePropertyChanged();

        returnToManagerView();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ScheduleShiftState scheduleShiftState = scheduleShiftViewModel.getState();
        scheduleShiftState.setDayError(errorMessage);
        scheduleShiftViewModel.firePropertyChanged();
    }

    @Override
    public void returnToManagerView() {
        viewManagerModel.setState(managerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
