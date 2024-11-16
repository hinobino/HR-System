package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import view.LoginView;

/**
 * The controller for the Login use case.
 */
public class LoginController {

    private final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     *
     * @param userID    the username of the user logging in
     * @param password  the password of the user logging in
     * @param view      the view to reset
     */
    public void execute(String userID, String password, LoginView view) {
        final LoginInputData loginInputData = new LoginInputData(userID, password, view);

        loginUseCaseInteractor.execute(loginInputData);
    }

    /**
     * Executes the "switch to WelcomeView" Use Case.
     */
    public void switchToWelcomeView() {
        loginUseCaseInteractor.switchToWelcomeView();
    }

}
