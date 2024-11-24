package interface_adapter.create_employee;

import entity.Manager;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.ManagerViewModel;
import use_case.create_employee.CreateEmployeeOutputBoundary;
import use_case.create_employee.CreateEmployeeOutputData;

public class CreateEmployeePresenter implements CreateEmployeeOutputBoundary {

    private final CreateEmployeeViewModel createEmployeeViewModel;
    public ViewManagerModel viewManagerModel;
    public ManagerViewModel managerViewModel;

    public CreateEmployeePresenter(ViewManagerModel viewManagerModel,
                                   CreateEmployeeViewModel createEmployeeViewModel,
                                   ManagerViewModel managerViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createEmployeeViewModel = createEmployeeViewModel;
        this.managerViewModel = managerViewModel;
    }

    @Override
    public void prepareSuccessView(CreateEmployeeOutputData response) {
        final CreateEmployeeState createEmployeeState = createEmployeeViewModel.getState();
        createEmployeeState.setNewUserID(response.getNewUserID());
        this.createEmployeeViewModel.setState(createEmployeeState);
        createEmployeeViewModel.firePropertyChanged();

        viewManagerModel.setState(managerViewModel.getViewName());
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

}
