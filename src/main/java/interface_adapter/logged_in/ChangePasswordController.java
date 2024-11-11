package main.java.interface_adapter.logged_in;

import main.java.use_case.change_password.ChangePasswordInputBoundary;
import main.java.use_case.change_password.ChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param password the new password
     * @param userID the user whose password to change
     */
    public void execute(String password, String userID) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(userID, password);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}
