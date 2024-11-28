package use_case.manage_shifts;

public interface ManageShiftsInputBoundary {

    /**
     * Executes the manage shifts use case.
     * @param manageShiftsInputData the input data.
     */
    void execute(ManageShiftsInputData manageShiftsInputData);

    /**
     * Executes the switch to manager view use case.
     */
    void switchToManagerView();

}
