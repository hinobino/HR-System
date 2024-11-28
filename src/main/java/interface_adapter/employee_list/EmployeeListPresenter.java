package interface_adapter.employee_list;

import entity.Employee;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_employee.CreateEmployeeViewModel;
import interface_adapter.manage_employee.ManageEmployeeState;
import interface_adapter.manage_employee.ManageEmployeeViewModel;
import use_case.employee_list.EmployeeListOutputBoundary;
import interface_adapter.logged_in.ManagerViewModel;
import use_case.employee_list.EmployeeListOutputData;

import java.util.ArrayList;

public class EmployeeListPresenter implements EmployeeListOutputBoundary {

    private final ManageEmployeeViewModel manageEmployeeViewModel;
    private final CreateEmployeeViewModel createEmployeeViewModel;
    private final ManagerViewModel managerViewModel;
    private final ViewManagerModel viewManagerModel;

    public EmployeeListPresenter(ManageEmployeeViewModel manageEmployeeViewModel,
                                 CreateEmployeeViewModel createEmployeeViewModel,
                                 ManagerViewModel managerViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.manageEmployeeViewModel = manageEmployeeViewModel;
        this.createEmployeeViewModel = createEmployeeViewModel;
        this.managerViewModel = managerViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void selectEmployee(EmployeeListOutputData employeeListOutputData) {
        final ManageEmployeeState manageEmployeeState = manageEmployeeViewModel.getState();
        manageEmployeeState.setEmployee(employeeListOutputData.getEmployee());
        manageEmployeeViewModel.setState(manageEmployeeState);
        manageEmployeeViewModel.firePropertyChanged();

        viewManagerModel.setState(manageEmployeeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToCreateEmployeeView() {
        viewManagerModel.setState(createEmployeeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToManagerView() {
        viewManagerModel.setState(managerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
