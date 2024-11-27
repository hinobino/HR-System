package interface_adapter.logged_in;

import entity.Employee;
import entity.Shift;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_employee.CreateEmployeeViewModel;
import interface_adapter.employee_list.EmployeeListState;
import interface_adapter.employee_list.EmployeeListViewModel;
import interface_adapter.schedule.ScheduleState;
import interface_adapter.schedule.ScheduleViewModel;
import interface_adapter.schedule_shift.ScheduleShiftState;
import interface_adapter.schedule_shift.ScheduleShiftViewModel;
import use_case.logged_in.manager.ManagerOutputBoundary;
import use_case.logged_in.manager.ManagerOutputData;
import view.ManagerView;
import view.ScheduleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerPresenter implements ManagerOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final CreateEmployeeViewModel createEmployeeViewModel;
    private ScheduleShiftViewModel scheduleShiftViewModel;
    private final EmployeeListViewModel employeeListViewModel;
    private ScheduleViewModel scheduleViewModel;

    public ManagerPresenter(CreateEmployeeViewModel createEmployeeViewModel,
                EmployeeListViewModel employeeListViewModel, ScheduleShiftViewModel scheduleShiftViewModel,
                ScheduleViewModel scheduleViewModel, ViewManagerModel viewManagerModel) {
        this.createEmployeeViewModel = createEmployeeViewModel;
        this.scheduleShiftViewModel = scheduleShiftViewModel;
        this.viewManagerModel = viewManagerModel;
        this.employeeListViewModel = employeeListViewModel;
        this.scheduleViewModel = scheduleViewModel;
    }

    public void switchToCreateEmployeeView() {
        viewManagerModel.setState(createEmployeeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToEmployeeListView(ManagerOutputData managerOutputData) {
        final EmployeeListState employeeListState = employeeListViewModel.getState();
        employeeListState.setEmployeeList(managerOutputData.getEmployees());
        employeeListViewModel.setState(employeeListState);
        employeeListViewModel.firePropertyChanged();

        viewManagerModel.setState(employeeListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToScheduleShiftView(ManagerOutputData managerOutputData) {
        final ScheduleShiftState scheduleShiftState = scheduleShiftViewModel.getState();
        scheduleShiftState.setEmployees(managerOutputData.getEmployees());
        scheduleShiftViewModel.setState(scheduleShiftState);
        scheduleShiftViewModel.firePropertyChanged();

        viewManagerModel.setState(scheduleShiftViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void openScheduleView(ManagerOutputData managerOutputData) {
        String userID = managerOutputData.getUserID();
        LoggedInState loggedInState = managerOutputData.getLoggedInState();

        final ScheduleState scheduleState = scheduleViewModel.getState();
        scheduleState.setUserID(userID);

        List<Shift> employeeShifts = new ArrayList<>();
        for (Employee employee : managerOutputData.getEmployees().values()) {
            employeeShifts.addAll(employee.getShifts());
        }
        scheduleState.setShifts(employeeShifts);
        scheduleState.setParentState(loggedInState);

        this.scheduleViewModel.setState(scheduleState);
        this.scheduleViewModel.firePropertyChanged();

        ScheduleView scheduleView = new ScheduleView(scheduleViewModel);
        loggedInState.setScheduleView(scheduleView);
        scheduleView.setVisible(true);

    }
}
