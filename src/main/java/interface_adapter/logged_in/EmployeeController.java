package interface_adapter.logged_in;

import use_case.logged_in.employee.EmployeeInputBoundary;
import use_case.logged_in.employee.EmployeeInputData;

/**
 * The controller for the logged in use case.
 */
public class EmployeeController {
    private final EmployeeInputBoundary employeeInputBoundary;

    public EmployeeController(EmployeeInputBoundary employeeInputBoundary) {
        this.employeeInputBoundary = employeeInputBoundary;
    }

    /**
     * Executes the open Schedule View Use Case.
     *
     * @param loggedInState the state of the current logged in view
     */
    public void openScheduleView(LoggedInState loggedInState) {
        final EmployeeInputData employeeInputData = new EmployeeInputData(loggedInState);
        employeeInputBoundary.openScheduleView(employeeInputData);
    }
}
