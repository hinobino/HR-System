package interface_adapter.manage_employee;

import interface_adapter.ViewManagerModel;
import interface_adapter.employee_list.EmployeeListViewModel;
import interface_adapter.logged_in.EmployeeViewModel;
import use_case.manage_employee.ManageEmployeeOutputBoundary;

public class ManageEmployeePresenter implements ManageEmployeeOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final EmployeeListViewModel employeeListViewModel;

    public ManageEmployeePresenter(EmployeeListViewModel employeeListViewModel, ViewManagerModel viewManagerModel) {
        this.employeeListViewModel = employeeListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToEmployeeListView() {
        viewManagerModel.setState(employeeListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
