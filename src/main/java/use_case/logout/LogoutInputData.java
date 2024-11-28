package use_case.logout;

import interface_adapter.logged_in.LoggedInState;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {
    private final String userID;
    private final LoggedInState loggedInState;

    public LogoutInputData(LoggedInState loggedInState) {
        this.userID = loggedInState.getUserID();
        this.loggedInState = loggedInState;
    }

    public String getUserID() {
        return userID;
    }

    public LoggedInState getLoggedInState() { return loggedInState; }

}
