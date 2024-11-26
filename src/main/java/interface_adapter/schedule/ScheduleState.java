package interface_adapter.schedule;

import entity.Shift;

import java.util.ArrayList;
import java.util.List;

public class ScheduleState {
    private String userID;
    private List<Shift> shifts;

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
}
