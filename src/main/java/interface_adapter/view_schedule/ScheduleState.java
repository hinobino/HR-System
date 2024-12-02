package interface_adapter.view_schedule;

import entity.Employee;
import entity.Shift;
import entity.WorkWeek;
import interface_adapter.logged_in.LoggedInState;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class ScheduleState {
    private String userID;
    private List<Shift> shifts;
    private Map<Employee, List<Shift>> allEmployeeShifts;
    private LoggedInState loggedInState = new LoggedInState();
    private Container container;
    private WorkWeek workWeek;

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

    public void setWeekContainer(Container cardContainer) { this.container = cardContainer; }

    public Container getWeekContainer() { return container; }

    public void setWeek(WorkWeek week) { this.workWeek = week; }

    public WorkWeek getWeek() { return workWeek; }

}
