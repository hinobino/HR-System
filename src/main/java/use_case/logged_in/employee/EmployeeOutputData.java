package use_case.logged_in.employee;

import entity.Shift;
import interface_adapter.logged_in.LoggedInState;

import java.util.List;

public class EmployeeOutputData {
    private final String userID;
    private final List<Shift> shifts;
    private final LoggedInState loggedInState;

    public EmployeeOutputData(LoggedInState loggedInState, List<Shift> shifts) {
        this.userID = loggedInState.getUserID();
        this.shifts = shifts;
        this.loggedInState = loggedInState;
    }

    public String getUserID() {
        return userID;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public LoggedInState getLoggedInState() { return loggedInState; }
}
