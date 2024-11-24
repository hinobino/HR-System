package entity;

import java.util.Map;

/**
 * The representation of a workday.
 * A map of String to Shift, with the string being the userID of the employee associated with the shift.
 */
public class Workday {

    private final Map<String, Shift> shifts;

    public Workday(Map<String, Shift> shifts) {
        this.shifts = shifts;
    }

    /**
     * Get all shifts taking place in the Workday.
     * @return a map of strings to shifts.
     */
    public Map<String, Shift> getShifts() {
        return shifts;
    }

    /**
     * Get the shift a user of the given ID is working in this workday, if any.
     * @param userID the ID of the user associated with a shift.
     * @return the shift associated with a user of the given ID, if it exists (null otherwise).
     */
    public Shift getShift(String userID) {
        return shifts.get(userID);
    }

    /**
     * Check if a user of the given ID has a scheduled shift in the given workday.
     * @param userID the ID of the user.
     * @return true if the user has a shift scheduled for the given workday.
     */
    public boolean isWorking(String userID) {
        return shifts.containsKey(userID);
    }

    public boolean isWorking (Employee employee) {
        return shifts.containsKey(employee.getUserID());
    }

    public void addShift(Shift shift) {
        this.shifts.put(shift.getEmployee().getUserID(), shift);
    }

    public void removeShift(Shift shift) {
        this.shifts.remove(shift.getEmployee().getUserID());
    }

}
