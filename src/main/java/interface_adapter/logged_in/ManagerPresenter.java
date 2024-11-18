package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_employee.CreateEmployeeViewModel;
import interface_adapter.employee_list.EmployeeListViewModel;
import use_case.logged_in.manager.ManagerOutputBoundary;

public class ManagerPresenter implements ManagerOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final CreateEmployeeViewModel createEmployeeViewModel;
    private final EmployeeListViewModel employeeListViewModel;

    public ManagerPresenter(CreateEmployeeViewModel createEmployeeViewModel, EmployeeListViewModel employeeListViewModel, ViewManagerModel viewManagerModel) {
        this.createEmployeeViewModel = createEmployeeViewModel;
        this.viewManagerModel = viewManagerModel;
        this.employeeListViewModel = employeeListViewModel;
    }

    public void switchToCreateEmployeeView() {
        viewManagerModel.setState(createEmployeeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToEmployeeListView() {
        viewManagerModel.setState(employeeListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
