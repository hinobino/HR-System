package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.schedule.ScheduleState;
import interface_adapter.schedule.ScheduleViewModel;
import use_case.logged_in.employee.EmployeeOutputData;
import use_case.logged_in.employee.EmployeeOutputBoundary;
import view.ScheduleView;

public class EmployeePresenter implements EmployeeOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private ScheduleViewModel scheduleViewModel;

    public EmployeePresenter(ViewManagerModel viewManagerModel,
                             ScheduleViewModel scheduleViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.scheduleViewModel = scheduleViewModel;
    }

    public void switchToScheduleView(EmployeeOutputData employeeOutputData) {
        String userID = employeeOutputData.getUserID();
        final ScheduleState scheduleState = scheduleViewModel.getState();
        scheduleState.setUserID(userID);
        scheduleState.setShifts(employeeOutputData.getShifts());

        this.scheduleViewModel.setState(scheduleState);
        this.scheduleViewModel.firePropertyChanged();

        ScheduleView scheduleView = new ScheduleView(scheduleViewModel);
        scheduleView.setVisible(true);
    }
}
