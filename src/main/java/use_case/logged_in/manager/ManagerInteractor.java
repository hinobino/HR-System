package use_case.logged_in.manager;

/**
 * The Manager Interactor.
 */
public class ManagerInteractor implements ManagerInputBoundary {

    private final ManagerUserDataAccessInterface userDataAccessObject;
    private final ManagerOutputBoundary managerOutputBoundary;

    public ManagerInteractor(ManagerUserDataAccessInterface userDataAccessObject,
            ManagerOutputBoundary managerOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.managerOutputBoundary = managerOutputBoundary;
    }

    @Override
    public void switchToCreateEmployeeView() {
        managerOutputBoundary.switchToCreateEmployeeView();
    }

    @Override
    public void switchToEmployeeListView() {
        managerOutputBoundary.switchToEmployeeListView();
    }

    public void switchToScheduleShiftView() {
        ManagerOutputData outputData = new ManagerOutputData(
                userDataAccessObject.getEmployees(userDataAccessObject.getCurrentUserID())
        );
        managerOutputBoundary.switchToScheduleShiftView(outputData);
    }
}
