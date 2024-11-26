package interface_adapter.manage_employee;

import interface_adapter.ViewManagerModel;
import interface_adapter.employee_list.EmployeeListState;
import interface_adapter.employee_list.EmployeeListViewModel;
import interface_adapter.logged_in.EmployeeViewModel;
import use_case.employee_list.EmployeeListOutputBoundary;
import use_case.employee_list.EmployeeListOutputData;
import use_case.manage_employee.ManageEmployeeOutputBoundary;
import use_case.manage_employee.ManageEmployeeOutputData;

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
    public void switchToEmployeeListView() {
        // Update the Employee List View
        final EmployeeListState employeeListState = employeeListViewModel.getState();

        viewManagerModel.setState(employeeListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
