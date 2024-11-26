package interface_adapter.manage_employee;

import entity.Employee;
import interface_adapter.ViewManagerModel;
import interface_adapter.employee_list.EmployeeListState;
import interface_adapter.employee_list.EmployeeListViewModel;
import use_case.manage_employee.ManageEmployeeOutputBoundary;
import use_case.manage_employee.ManageEmployeeOutputData;

import java.util.Map;

public class ManageEmployeePresenter implements ManageEmployeeOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ManageEmployeeViewModel manageEmployeeViewModel;
    private final EmployeeListViewModel employeeListViewModel;

    public ManageEmployeePresenter(
            ManageEmployeeViewModel manageEmployeeViewModel,
            EmployeeListViewModel employeeListViewModel,
            ViewManagerModel viewManagerModel) {
        this.manageEmployeeViewModel = manageEmployeeViewModel;
        this.employeeListViewModel = employeeListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void updateManageEmployeeView(ManageEmployeeOutputData update) {
        // Update the manage employee view
        final ManageEmployeeState manageEmployeeState = manageEmployeeViewModel.getState();
        manageEmployeeState.setEmployee(update.getEmployee());
        this.manageEmployeeViewModel.setState(manageEmployeeState);
        manageEmployeeViewModel.firePropertyChanged();

        viewManagerModel.setState(manageEmployeeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {
        final ManageEmployeeState manageEmployeeState = manageEmployeeViewModel.getState();
        manageEmployeeState.togglePayError();
        manageEmployeeViewModel.firePropertyChanged();
    }

    @Override
    public void switchToEmployeeListView(Map<String, Employee> employees) {
        // Update the Employee List View
        final EmployeeListState employeeListState = employeeListViewModel.getState();
        employeeListState.setEmployeeList(employees);
        employeeListViewModel.setState(employeeListState);
        employeeListViewModel.firePropertyChanged();

        viewManagerModel.setState(employeeListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
