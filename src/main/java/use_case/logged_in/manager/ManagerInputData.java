package use_case.logged_in.manager;

import interface_adapter.logged_in.LoggedInState;

public class ManagerInputData {
    private final String userID;
    private final LoggedInState loggedInState;

    public ManagerInputData(LoggedInState loggedInState) {
        this.userID = loggedInState.getUserID();
        this.loggedInState = loggedInState;
    }

    String getUserID() { return userID; }

    public LoggedInState getLoggedInState() {
        return loggedInState;
    }
}
