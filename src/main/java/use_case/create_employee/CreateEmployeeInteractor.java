package use_case.create_employee;

import entity.Employee;
import entity.EmployeeFactory;
import entity.Manager;

/**
 * The Create Employee Interactor.
 */
public class CreateEmployeeInteractor implements CreateEmployeeInputBoundary {
    private final CreateEmployeeUserDataAccessInterface userDataAccessObject;
    private final CreateEmployeeOutputBoundary createEmployeePresenter;

    // This user case only creates employees
    private final EmployeeFactory employeeFactory;

    public CreateEmployeeInteractor(CreateEmployeeUserDataAccessInterface createEmployeeUserDataAccessInterface,
                                    CreateEmployeeOutputBoundary createEmployeeOutputBoundary,
                                    EmployeeFactory employeeFactory) {
        this.userDataAccessObject = createEmployeeUserDataAccessInterface;
        this.createEmployeePresenter = createEmployeeOutputBoundary;
        this.employeeFactory = employeeFactory;
    }

    @Override
    public void execute(CreateEmployeeInputData createEmployeeInputData) {
        if (userDataAccessObject.existsByName(createEmployeeInputData.getNewUserID())) {
            createEmployeePresenter.prepareFailView("User ID already exists.");
        }
        else if ("".equals(createEmployeeInputData.getNewUserID())) {
            createEmployeePresenter.prepareFailView("Please enter a valid User ID.");
        }
        else {
            // Resets the CreateEmployeeView for future use.
            createEmployeeInputData.getView().resetView();

            // Creates a new Employee with the given user ID.
            Employee inactiveEmployee = employeeFactory.create(createEmployeeInputData.getNewUserID(), "");
            userDataAccessObject.save(inactiveEmployee);

            // Add the new employee to the list of the manager's employees
            Manager currentManager = (Manager) userDataAccessObject.get(userDataAccessObject.getCurrentUserID());
            currentManager.addEmployee(inactiveEmployee);

            final CreateEmployeeOutputData createEmployeeOutputData = new CreateEmployeeOutputData(
                    createEmployeeInputData.getNewUserID(), currentManager.getEmployees(),
                    false);
            createEmployeePresenter.prepareSuccessView(createEmployeeOutputData);
        }
    }

    @Override
    public void switchToManagerView() { createEmployeePresenter.switchToManagerView(); }

    @Override
    public void switchToEmployeeListView() { createEmployeePresenter.switchToEmployeeListView(); }
}
