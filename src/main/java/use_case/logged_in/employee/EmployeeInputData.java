package use_case.logged_in.employee;

import interface_adapter.logged_in.LoggedInState;

public class EmployeeInputData {

    private final String userID;
    private final LoggedInState loggedInState;

    public EmployeeInputData(LoggedInState loggedInState) {
        this.userID = loggedInState.getUserID();
        this.loggedInState = loggedInState;
    }

    String getUserID() { return userID; }

    public LoggedInState getLoggedInState() {
        return loggedInState;
    }
}