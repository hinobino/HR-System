package interface_adapter.create_employee;

import use_case.create_employee.CreateEmployeeInputData;
import use_case.create_employee.CreateEmployeeInputBoundary;
import view.CreateEmployeeView;

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
     *
     * @param newUserID          the new User ID to create.
     * @param view               the view to reset.
     */
    public void execute(String newUserID, CreateEmployeeView view) {
        final CreateEmployeeInputData createEmployeeInputData =
                new CreateEmployeeInputData(newUserID, view);

        createEmployeeInteractor.execute(createEmployeeInputData);
    }

    /**
     * Executes the "switch to WelcomeView" Use Case.
     */
    public void switchToManagerView() {
        createEmployeeInteractor.switchToManagerView();
    }

    /**
     * Executes the switch to Manage Employee View Use Case.
     */
    public void switchToEmployeeListView() { createEmployeeInteractor.switchToEmployeeListView(); }

}
