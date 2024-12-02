package interface_adapter.manage_shifts;

import entity.Employee;
import entity.Shift;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ManageShiftsState {

    private List<Shift> shifts = new ArrayList<>();

    public void setShifts(Map<String, Employee> employees) {
        for (Employee e : employees.values()) {
            shifts.addAll(e.getShifts());
        }
    }

    /**
     * From the list of shifts, find one by its ID.
     * @param ID the ID of the desired shift.
     * @return the Shift with the given ID or null if one does not exist.
     */
    public Shift getShiftByID(String ID) {
        for (Shift s : shifts) {
            if (s.getID().equals(ID)) {
                return s;
            }
        }

        return null;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

}
