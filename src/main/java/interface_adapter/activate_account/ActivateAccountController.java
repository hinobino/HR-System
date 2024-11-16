package interface_adapter.activate_account;

import use_case.activate_account.ActivateAccountInputBoundary;
import use_case.activate_account.ActivateAccountInputData;
import view.ActivateAccountView;

/**
 * Controller for the activate account usecase.
 */
public class ActivateAccountController {

    private final ActivateAccountInputBoundary activateAccountInteractor;

    public ActivateAccountController(ActivateAccountInputBoundary activateAccountUsecaseInteractor) {
        this.activateAccountInteractor = activateAccountUsecaseInteractor;
    }

    /**
     * Executes the Signup use case.
     *
     * @param userID              the userID to sign up.
     * @param password1           the password to use.
     * @param password2           the password, repeated.
     * @param view                the view to reset.
     */
    public void execute(String userID, String password1, String password2, ActivateAccountView view) {
        final ActivateAccountInputData activateAccountInputData = new ActivateAccountInputData(
                userID, password1, password2, view);

        activateAccountInteractor.execute(activateAccountInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        activateAccountInteractor.switchToLoginView();
    }

    public void switchToWelcomeView() { activateAccountInteractor.switchToWelcomeView(); }
}
