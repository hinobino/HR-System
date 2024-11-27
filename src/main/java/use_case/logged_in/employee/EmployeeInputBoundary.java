package use_case.logged_in.employee;

/**
 * The Input Boundary for the Manager Welcome Usecase.
 */
public interface EmployeeInputBoundary {

    /**
     * Switches to the Login View.
     */
    void openScheduleView(EmployeeInputData employeeInputData);
}
