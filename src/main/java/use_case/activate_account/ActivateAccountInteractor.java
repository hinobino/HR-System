package use_case.activate_account;

import entity.*;
import use_case.signup.SignupInputData;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The ActivateAccount Interactor.
 */
public class ActivateAccountInteractor implements ActivateAccountInputBoundary {

    private final ActivateAccountUserDataAccessInterface userDataAccessObject;
    private final ActivateAccountOutputBoundary userPresenter;

    // This user case only creates managers
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
        if (userDataAccessObject.existsByName(activateAccountInputData.getUserID())) {
            userPresenter.prepareFailView("User already exists.");
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

        else {
            User user = employeeFactory.create(activateAccountInputData.getUserID(),
                    activateAccountInputData.getPassword());
            userDataAccessObject.save(user);

            final ActivateAccountOutputData activateAccountOutputData = new ActivateAccountOutputData(user.getUserID(),
                    false);
            userPresenter.prepareSuccessView(activateAccountOutputData);
        }
    }

    @Override
    public void switchToLoginView() { userPresenter.switchToLoginView(); }

    @Override
    public void switchToWelcomeView() { userPresenter.switchToWelcomeView(); }

}
