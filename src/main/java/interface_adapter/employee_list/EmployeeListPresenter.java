package interface_adapter.employee_list;

import interface_adapter.ViewManagerModel;
import use_case.employee_list.EmployeeListOutputBoundary;
import interface_adapter.logged_in.ManagerViewModel;

import java.util.ArrayList;

public class EmployeeListPresenter implements EmployeeListOutputBoundary {

    private final EmployeeListViewModel employeeListViewModel;
    private final ManagerViewModel managerViewModel;
    private final ViewManagerModel viewManagerModel;

    public EmployeeListPresenter(EmployeeListViewModel employeeListViewModel, ManagerViewModel managerViewModel, ViewManagerModel viewManagerModel) {
        this.employeeListViewModel = employeeListViewModel;
        this.managerViewModel = managerViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void switchToManagerView() {
        viewManagerModel.setState(managerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
