package interface_adapter.create_employee;

import interface_adapter.ViewManagerModel;
import interface_adapter.employee_list.EmployeeListState;
import interface_adapter.employee_list.EmployeeListViewModel;
import interface_adapter.logged_in.ManagerViewModel;
import use_case.create_employee.CreateEmployeeOutputBoundary;
import use_case.create_employee.CreateEmployeeOutputData;

public class CreateEmployeePresenter implements CreateEmployeeOutputBoundary {

    private final CreateEmployeeViewModel createEmployeeViewModel;
    public ViewManagerModel viewManagerModel;
    public ManagerViewModel managerViewModel;
    public EmployeeListViewModel employeeListViewModel;

    public CreateEmployeePresenter(ViewManagerModel viewManagerModel,
                                   CreateEmployeeViewModel createEmployeeViewModel,
                                   ManagerViewModel managerViewModel,
                                   EmployeeListViewModel employeeListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createEmployeeViewModel = createEmployeeViewModel;
        this.managerViewModel = managerViewModel;
        this.employeeListViewModel = employeeListViewModel;
    }

    @Override
    public void prepareSuccessView(CreateEmployeeOutputData response) {
        final CreateEmployeeState createEmployeeState = createEmployeeViewModel.getState();
        createEmployeeState.setNewUserID(response.getNewUserID());
        this.createEmployeeViewModel.setState(createEmployeeState);
        createEmployeeViewModel.firePropertyChanged();

        final EmployeeListState employeeListState = employeeListViewModel.getState();
        employeeListState.setEmployeeList(response.getEmployees());
        this.employeeListViewModel.setState(employeeListState);
        employeeListViewModel.firePropertyChanged();

        viewManagerModel.setState(employeeListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final CreateEmployeeState createEmployeeState = createEmployeeViewModel.getState();
        createEmployeeState.setNewUserIDError(error);
        createEmployeeViewModel.firePropertyChanged();
    }

    @Override
    public void switchToManagerView() {
        viewManagerModel.setState(managerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToEmployeeListView() {
        viewManagerModel.setState(employeeListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
