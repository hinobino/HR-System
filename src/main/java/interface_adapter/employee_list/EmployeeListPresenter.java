package interface_adapter.employee_list;

import use_case.employee_list.EmployeeListOutputBoundary;

import java.util.ArrayList;

public class EmployeeListPresenter implements EmployeeListOutputBoundary {

    private final EmployeeListViewModel employeeListViewModel;

    public EmployeeListPresenter(EmployeeListViewModel employeeListViewModel) {
        this.employeeListViewModel = employeeListViewModel;
    }

    public void createEmployeeList(Object[][] employeeList) {
        final EmployeeListState employeeListState = employeeListViewModel.getState();
        employeeListState.setEmployeeList(employeeList);
        this.employeeListViewModel.setState(employeeListState);
        this.employeeListViewModel.firePropertyChanged();
    }
}
