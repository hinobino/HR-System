package use_case.logout;

import interface_adapter.logged_in.LoggedInState;

/**
 * Output Data for the Logout Use Case.
 */
public class LogoutOutputData {

    private String userID;
    private LoggedInState loggedInState;
    private boolean useCaseFailed;

    public LogoutOutputData(LoggedInState loggedInState, boolean useCaseFailed) {
        this.userID = loggedInState.getUserID();
        this.loggedInState = loggedInState;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUserID() {
        return userID;
    }

    public LoggedInState getLoggedInState() { return loggedInState; }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
