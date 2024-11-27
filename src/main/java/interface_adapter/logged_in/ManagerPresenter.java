package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_employee.CreateEmployeeViewModel;
import interface_adapter.employee_list.EmployeeListState;
import interface_adapter.employee_list.EmployeeListViewModel;
import interface_adapter.manage_shifts.ManageShiftsState;
import interface_adapter.manage_shifts.ManageShiftsViewModel;
import interface_adapter.schedule_shift.ScheduleShiftState;
import interface_adapter.schedule_shift.ScheduleShiftViewModel;
import use_case.logged_in.manager.ManagerOutputBoundary;
import use_case.logged_in.manager.ManagerOutputData;
import view.ManagerView;

public class ManagerPresenter implements ManagerOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final CreateEmployeeViewModel createEmployeeViewModel;
    private ScheduleShiftViewModel scheduleShiftViewModel;
    private final EmployeeListViewModel employeeListViewModel;
    private final ManageShiftsViewModel manageShiftsViewModel;

    public ManagerPresenter(CreateEmployeeViewModel createEmployeeViewModel,
                EmployeeListViewModel employeeListViewModel, ScheduleShiftViewModel scheduleShiftViewModel,
                ViewManagerModel viewManagerModel,
                            ManageShiftsViewModel manageShiftsViewModel) {
        this.createEmployeeViewModel = createEmployeeViewModel;
        this.scheduleShiftViewModel = scheduleShiftViewModel;
        this.viewManagerModel = viewManagerModel;
        this.employeeListViewModel = employeeListViewModel;
        this.manageShiftsViewModel = manageShiftsViewModel;
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
    public void switchToManageShiftsView(ManagerOutputData outputData) {
        final ManageShiftsState manageShiftsState = manageShiftsViewModel.getState();
        manageShiftsState.setShifts(outputData.getEmployees());
        manageShiftsViewModel.setState(manageShiftsState);
        manageShiftsViewModel.firePropertyChanged();

        viewManagerModel.setState(manageShiftsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
