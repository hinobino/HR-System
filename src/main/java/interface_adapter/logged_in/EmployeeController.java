package interface_adapter.logged_in;

import use_case.logged_in.employee.EmployeeInputBoundary;
import use_case.logged_in.employee.EmployeeInputData;

public class EmployeeController {
    private final EmployeeInputBoundary employeeInputBoundary;

    public EmployeeController(EmployeeInputBoundary employeeInputBoundary) {
        this.employeeInputBoundary = employeeInputBoundary;
    }

    public void switchToScheduleView(LoggedInState loggedInState) {
        final EmployeeInputData employeeInputData = new EmployeeInputData(loggedInState);
        employeeInputBoundary.switchToScheduleView(employeeInputData);
    }
}
