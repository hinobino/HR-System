package interface_adapter.logout;

import interface_adapter.logged_in.LoggedInState;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

/**
 * The controller for the Logout Use Case.
 */
public class LogoutController {

    private LogoutInputBoundary logoutUseCaseInteractor;

    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }

    /**
     * Executes the Logout Use Case.
     * @param userID the userID of the user logging in
     */
    public void execute(LoggedInState loggedInState) {
        // 1. instantiate the `LogoutInputData`, which should contain the username.
        // 2. tell the Interactor to execute.
        final LogoutInputData inputData = new LogoutInputData(loggedInState);
        logoutUseCaseInteractor.execute(inputData);
    }
}
