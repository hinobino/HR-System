package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_employee.CreateEmployeeViewModel;
import use_case.logged_in.manager.ManagerOutputBoundary;

public class ManagerPresenter implements ManagerOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final CreateEmployeeViewModel createEmployeeViewModel;

    public ManagerPresenter(CreateEmployeeViewModel createEmployeeViewModel, ViewManagerModel viewManagerModel) {
        this.createEmployeeViewModel = createEmployeeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void switchToCreateEmployeeView() {
        viewManagerModel.setState(createEmployeeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
