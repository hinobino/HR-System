package use_case.logged_in.employee;

import entity.Shift;

import java.util.List;

public class EmployeeOutputData {
    private final String userID;
    private final List<Shift> shifts;

    public EmployeeOutputData(String userID, List<Shift> shifts) {
        this.userID = userID;
        this.shifts = shifts;
    }

    public String getUserID() {
        return userID;
    }

    public List<Shift> getShifts() {
        return shifts;
    }
}
