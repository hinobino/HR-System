package use_case.logged_in.employee;

import data_access.InMemoryUserDataAccessObject;

public class EmployeeInteractor implements EmployeeInputBoundary {
    private final EmployeeUserDataAccessInterface userDataAccessObject;
    private final EmployeeOutputBoundary employeeOutputBoundary;

    public EmployeeInteractor(InMemoryUserDataAccessObject userDataAccessObject, EmployeeOutputBoundary employeeOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.employeeOutputBoundary = employeeOutputBoundary;
    }

    @Override
    public void openScheduleView(EmployeeInputData employeeInputData) {
        EmployeeOutputData employeeOutputData = new EmployeeOutputData(employeeInputData
                .getLoggedInState(), userDataAccessObject.getShifts(userDataAccessObject
                .getCurrentUserID()));
        employeeOutputBoundary.openScheduleView(employeeOutputData);
    }
}
