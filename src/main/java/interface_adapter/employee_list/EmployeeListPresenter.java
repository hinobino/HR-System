package interface_adapter.employee_list;

import entity.Employee;
import interface_adapter.ViewManagerModel;
import interface_adapter.manage_employee.ManageEmployeeState;
import interface_adapter.manage_employee.ManageEmployeeViewModel;
import use_case.employee_list.EmployeeListOutputBoundary;
import interface_adapter.logged_in.ManagerViewModel;
import use_case.employee_list.EmployeeListOutputData;

import java.util.ArrayList;

public class EmployeeListPresenter implements EmployeeListOutputBoundary {

    private final ManageEmployeeViewModel manageEmployeeViewModel;
    private final ManagerViewModel managerViewModel;
    private final ViewManagerModel viewManagerModel;

    public EmployeeListPresenter(ManageEmployeeViewModel manageEmployeeViewModel, ManagerViewModel managerViewModel, ViewManagerModel viewManagerModel) {
        this.manageEmployeeViewModel = manageEmployeeViewModel;
        this.managerViewModel = managerViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void selectEmployee(EmployeeListOutputData employeeListOutputData) {
        final ManageEmployeeState manageEmployeeState = manageEmployeeViewModel.getState();
        manageEmployeeState.setEmployee(employeeListOutputData.getEmployee());
        manageEmployeeViewModel.setState(manageEmployeeState);
        manageEmployeeViewModel.firePropertyChanged();

        viewManagerModel.setState(manageEmployeeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToManagerView() {
        viewManagerModel.setState(managerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
