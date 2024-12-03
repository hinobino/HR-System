package use_case.logged_in.employee;

import entity.Shift;
import entity.WorkWeek;
import interface_adapter.logged_in.LoggedInState;

import java.util.List;

public class EmployeeOutputData {
    private final String userID;
    private final List<Shift> shifts;
    private final List<WorkWeek> weeks;
    private final LoggedInState loggedInState;

    public EmployeeOutputData(LoggedInState loggedInState, List<Shift> shifts, List<WorkWeek> weeks) {
        this.userID = loggedInState.getUserID();
        this.shifts = shifts;
        this.weeks = weeks;
        this.loggedInState = loggedInState;
    }

    public String getUserID() {
        return userID;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public List<WorkWeek> getWeeks() { return weeks; }

    public LoggedInState getLoggedInState() { return loggedInState; }
}
