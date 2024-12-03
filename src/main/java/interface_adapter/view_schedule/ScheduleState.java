package interface_adapter.view_schedule;

import entity.Shift;
import entity.WorkWeek;
import interface_adapter.logged_in.LoggedInState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleState {
    private String userID;
    private List<Shift> shifts;
    private LoggedInState loggedInState = new LoggedInState();
    private Container container;
    private WorkWeek workWeek;
    private List<WorkWeek> weeks = new ArrayList<>();
    private ScheduleController scheduleController;

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

    public void setWeeks(List<WorkWeek> weeks) { this.weeks = weeks; }

    public List<WorkWeek> getWeeks() { return weeks; }

    public void setScheduleController(ScheduleController controller) {
        this.scheduleController = controller; }

    public ScheduleController getScheduleController() { return scheduleController; }
}
