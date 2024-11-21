package use_case.logged_in.manager;

/**
 * The Input Boundary for the Manager Welcome Usecase.
 */
public interface ManagerInputBoundary {

    /**
     * Switches to the Login View.
     */
    void switchToCreateEmployeeView();

    /**
     * Switches to the Employee List View
     */
    void switchToEmployeeListView();

}
