package use_case.activate_account;

import entity.*;

import java.util.Objects;

/**
 * The ActivateAccount Interactor.
 */
public class ActivateAccountInteractor implements ActivateAccountInputBoundary {

    private final ActivateAccountUserDataAccessInterface userDataAccessObject;
    private final ActivateAccountOutputBoundary userPresenter;

    // This user case only creates employees
    private final EmployeeFactory employeeFactory;

    public ActivateAccountInteractor(ActivateAccountUserDataAccessInterface activateAccountUserDataAccessInterface,
                            ActivateAccountOutputBoundary activateAccountOutputBoundary,
                                     EmployeeFactory employeeFactory) {
        this.userDataAccessObject = activateAccountUserDataAccessInterface;
        this.userPresenter = activateAccountOutputBoundary;
        this.employeeFactory = employeeFactory;
    }

    @Override
    public void execute(ActivateAccountInputData activateAccountInputData) {
        if (!userDataAccessObject.existsByName(activateAccountInputData.getUserID())) {
            userPresenter.prepareFailView("User does not exist.");
        }
        else if (!activateAccountInputData.getPassword().equals(activateAccountInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else if ("".equals(activateAccountInputData.getUserID())) {
            userPresenter.prepareFailView("Please enter a valid User ID.");
        }
        else if ("".equals(activateAccountInputData.getPassword())) {
            userPresenter.prepareFailView("Please enter a valid password.");
        }
        else if (userDataAccessObject.existsByName(activateAccountInputData.getUserID())) {
            // The given user ID exists, so we can use it to search for associated User object.
            // NOTE: We know the object is an Employee because if the user ID exists, that means a
            // manager has created it, and when a manager "creates a user ID," they create an
            // Employee object with that User ID.
            Employee employee = (Employee) userDataAccessObject.get(
                    activateAccountInputData.getUserID());

            // getStatus is an Employee method (we don't need it under User methods), so since
            // get() returns a User object, when it grabs an Employee/Manager, it casts the object
            // as a User, so to call this method, we cast it back to an Employee.
            if ("active".equals(employee.getStatus())) {
                userPresenter.prepareFailView("User has already been activated.");
            }
            // Employee exists but has not been activated yet.
            else { // ACTIVATION SUCCESSFUL
                // Since we are preparing to switch views, reset ActivateAccountView for future use.
                activateAccountInputData.getView().resetView();

                // Normally (if we matched how SignupInteractor is implemented), we would
                // instantiate the employee here, but we already did that earlier in this else if.

                // Activate the account. This sets the given password and changes status to active.
                userDataAccessObject.activateUser(activateAccountInputData.getUserID(),
                        activateAccountInputData.getPassword());

                final ActivateAccountOutputData activateAccountOutputData =
                        new ActivateAccountOutputData(employee.getUserID(), false);
                userPresenter.prepareSuccessView(activateAccountOutputData);
            }
        }
    }

    @Override
    public void switchToLoginView() { userPresenter.switchToLoginView(); }

    @Override
    public void switchToWelcomeView() { userPresenter.switchToWelcomeView(); }

}
