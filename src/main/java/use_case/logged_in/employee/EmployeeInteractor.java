package use_case.logged_in.employee;

import data_access.InMemoryUserDataAccessObject;

public class EmployeeInteractor implements EmployeeInputBoundary {
    private final InMemoryUserDataAccessObject userDataAccessObject;
    private final EmployeeOutputBoundary employeeOutputBoundary;

    public EmployeeInteractor(InMemoryUserDataAccessObject userDataAccessObject, EmployeeOutputBoundary employeeOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.employeeOutputBoundary = employeeOutputBoundary;
    }

    @Override
    public void switchToScheduleView(EmployeeInputData employeeInputData) {
        final EmployeeOutputData employeeOutputData = new EmployeeOutputData(employeeInputData
                .getUserID(), userDataAccessObject.getShifts(employeeInputData.getUserID()));
        employeeOutputBoundary.switchToScheduleView(employeeOutputData);
    }
}
