package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.view_schedule.ScheduleState;
import interface_adapter.view_schedule.ScheduleViewModel;
import use_case.logged_in.employee.EmployeeOutputData;
import use_case.logged_in.employee.EmployeeOutputBoundary;
import view.ScheduleView;

/**
 * The Presenter for the Employee Use Case.
 */
public class EmployeePresenter implements EmployeeOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private ScheduleViewModel scheduleViewModel;

    public EmployeePresenter(ViewManagerModel viewManagerModel,
                             ScheduleViewModel scheduleViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.scheduleViewModel = scheduleViewModel;
    }

    public void openScheduleView(EmployeeOutputData employeeOutputData) {
        String userID = employeeOutputData.getUserID();
        LoggedInState loggedInState = employeeOutputData.getLoggedInState();

        final ScheduleState scheduleState = scheduleViewModel.getState();
        scheduleState.setUserID(userID);
        scheduleState.setShifts(employeeOutputData.getShifts());
        scheduleState.setWeeks(employeeOutputData.getWeeks());
        scheduleState.setWeek(employeeOutputData.getWeeks().get(0));
        scheduleState.setParentState(loggedInState);

        this.scheduleViewModel.setState(scheduleState);
        this.scheduleViewModel.firePropertyChanged();

        ScheduleView scheduleView = new ScheduleView(scheduleViewModel);
        loggedInState.setScheduleView(scheduleView);
        scheduleView.setVisible(true);
    }
}
