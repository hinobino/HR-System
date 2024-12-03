package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Factory for creating shifts.
 */
public class ShiftFactory {

    /**
     * Creates a new Shift.
     * @param day the day the shift occurs.
     * @param startTime the start time of the shift.
     * @param endTime the end time of the shift.
     * @param employee the employee assigned to the shift.
     * @return a new shift object.
     */
    public Shift create(LocalDate day, LocalDateTime startTime, LocalDateTime endTime, Employee employee) {
        return new Shift(day, startTime, endTime, employee);
    }

}
