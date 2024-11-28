package use_case.logged_in.manager;

import use_case.logged_in.employee.EmployeeOutputData;

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
        ManagerOutputData outputData = new ManagerOutputData(
                userDataAccessObject.getEmployees(userDataAccessObject.getCurrentUserID())
        );
        managerOutputBoundary.switchToEmployeeListView(outputData);
    }

    public void switchToScheduleShiftView() {
        ManagerOutputData outputData = new ManagerOutputData(
                userDataAccessObject.getEmployees(userDataAccessObject.getCurrentUserID())
        );
        managerOutputBoundary.switchToScheduleShiftView(outputData);
    }

    @Override
    public void switchToManageShiftsView() {
        ManagerOutputData outputData = new ManagerOutputData(
                userDataAccessObject.getEmployees(userDataAccessObject.getCurrentUserID())
        );
        managerOutputBoundary.switchToManageShiftsView(outputData);
    }

    public void openScheduleView(ManagerInputData managerInputData) {
        ManagerOutputData managerOutputData = new ManagerOutputData(managerInputData
                .getLoggedInState(), userDataAccessObject.getEmployees(userDataAccessObject
                .getCurrentUserID()));

        managerOutputBoundary.openScheduleView(managerOutputData);
    }
}
