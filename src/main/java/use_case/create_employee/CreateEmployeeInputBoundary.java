package use_case.create_employee;

/**
 * Input Boundary for actions which are related to creating a new employee.
 */
public interface CreateEmployeeInputBoundary {
    /**
     * Executes the signup use case.
     * @param createEmployeeInputData the input data
     */
    void execute(CreateEmployeeInputData createEmployeeInputData);

    /**
     * Executes the switch to welcome view use case.
     */
    void switchToWelcomeView();
}
