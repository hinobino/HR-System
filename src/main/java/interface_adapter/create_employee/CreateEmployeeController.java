package interface_adapter.create_employee;

import use_case.create_employee.CreateEmployeeInputData;
import use_case.create_employee.CreateEmployeeInputBoundary;

/**
 * The controller for the create employee use case.
 */
public class CreateEmployeeController {

    private final CreateEmployeeInputBoundary createEmployeeInteractor;

    public CreateEmployeeController(CreateEmployeeInputBoundary createEmployeeInteractor) {
        this.createEmployeeInteractor = createEmployeeInteractor;
    }

    /**
     * Executes the create employee use case.
     * @param newUserID the new User ID to create.
     */
    public void execute(String newUserID) {
        final CreateEmployeeInputData createEmployeeInputData = new CreateEmployeeInputData(newUserID);

        createEmployeeInteractor.execute(createEmployeeInputData);
    }

    /**
     * Executes the "switch to WelcomeView" Use Case.
     */
    public void switchToWelcomeView() {
        createEmployeeInteractor.switchToWelcomeView();
    }

}
