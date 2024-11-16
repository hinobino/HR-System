package use_case.logged_in.manager;

/**
 * The Manager Interactor.
 */
public class ManagerInteractor implements ManagerInputBoundary {

    private final ManagerOutputBoundary managerOutputBoundary;

    public ManagerInteractor(ManagerOutputBoundary managerOutputBoundary) {
        this.managerOutputBoundary = managerOutputBoundary;
    }

    @Override
    public void switchToCreateEmployeeView() {
        managerOutputBoundary.switchToCreateEmployeeView();
    }
}
