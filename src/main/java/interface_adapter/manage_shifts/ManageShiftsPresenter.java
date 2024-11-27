package interface_adapter.manage_shifts;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.ManagerViewModel;
import use_case.manage_shifts.ManageShiftsOutputBoundary;

public class ManageShiftsPresenter implements ManageShiftsOutputBoundary {

    private final ManagerViewModel managerViewModel;
    private final ViewManagerModel viewManagerModel;

    public ManageShiftsPresenter(ManagerViewModel managerViewModel, ViewManagerModel viewManagerModel) {
        this.managerViewModel = managerViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        switchToManagerView();
    }

    @Override
    public void switchToManagerView() {
        viewManagerModel.setState(managerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
