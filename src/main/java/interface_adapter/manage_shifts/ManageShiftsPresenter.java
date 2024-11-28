package interface_adapter.manage_shifts;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.ManagerViewModel;
import use_case.manage_shifts.ManageShiftsOutputBoundary;

public class ManageShiftsPresenter implements ManageShiftsOutputBoundary {

    private final ManagerViewModel managerViewModel;
    private final ManageShiftsViewModel manageShiftsViewModel;
    private final ViewManagerModel viewManagerModel;

    public ManageShiftsPresenter(ManagerViewModel managerViewModel, ManageShiftsViewModel manageShiftsViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.managerViewModel = managerViewModel;
        this.manageShiftsViewModel = manageShiftsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView() {
        manageShiftsViewModel.setState(new ManageShiftsState());
        switchToManagerView();
    }

    @Override
    public void switchToManagerView() {
        viewManagerModel.setState(managerViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
