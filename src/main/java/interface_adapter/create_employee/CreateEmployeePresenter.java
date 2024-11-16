package interface_adapter.create_employee;

import interface_adapter.ViewManagerModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.create_employee.CreateEmployeeOutputBoundary;
import use_case.create_employee.CreateEmployeeOutputData;

public class CreateEmployeePresenter implements CreateEmployeeOutputBoundary {

    private final CreateEmployeeViewModel createEmployeeViewModel;
    public ViewManagerModel viewManagerModel;
    public WelcomeViewModel welcomeViewModel;

    public CreateEmployeePresenter(ViewManagerModel viewManagerModel,
                                   CreateEmployeeViewModel createEmployeeViewModel,
                                   WelcomeViewModel welcomeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createEmployeeViewModel = createEmployeeViewModel;
        this.welcomeViewModel = welcomeViewModel;
    }

    @Override
    public void prepareSuccessView(CreateEmployeeOutputData response) {
        final CreateEmployeeState createEmployeeState = createEmployeeViewModel.getState();
        createEmployeeState.setNewUserID(response.getNewUserID());
        this.createEmployeeViewModel.setState(createEmployeeState);
        createEmployeeViewModel.firePropertyChanged();

        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final CreateEmployeeState createEmployeeState = createEmployeeViewModel.getState();
        createEmployeeState.setNewUserIDError(error);
        createEmployeeViewModel.firePropertyChanged();
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
