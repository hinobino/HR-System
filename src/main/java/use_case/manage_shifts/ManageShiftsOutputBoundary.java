package use_case.manage_shifts;

public interface ManageShiftsOutputBoundary {

    /**
     * Prepare success view for delete shift use case.
     */
    void prepareSuccessView();

    /**
     * Switches to the manager view.
     */
    void switchToManagerView();

}
