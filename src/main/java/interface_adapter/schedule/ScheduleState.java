package interface_adapter.schedule;

import entity.Shift;
import interface_adapter.logged_in.LoggedInState;

import java.util.ArrayList;
import java.util.List;

public class ScheduleState {
    private String userID;
    private List<Shift> shifts;
    private LoggedInState loggedInState;

    public ScheduleState() {}

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
    public List<Shift> getShifts() {
        return shifts;
    }

    public void setParentState(LoggedInState loggedInState) {
        this.loggedInState = loggedInState;
    }

    public LoggedInState getParentState() {
        return loggedInState;
    }
}
