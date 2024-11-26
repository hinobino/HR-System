package use_case.manage_employee;

/**
 * The Input Boundary for the Manager Welcome Usecase.
 */
public interface ManageEmployeeInputBoundary {

    /**
     * Change the Employee's status
     */
    void changeStatus(ManageEmployeeInputData manageEmployeeInputData);

    /**
     * Switches to the Employee List View.
     */
    void switchToEmployeeListView();
}
