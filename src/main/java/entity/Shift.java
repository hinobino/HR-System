package entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The representation of a shift.
 */
public class Shift {

    private static long iDCounter = 0;

    private LocalDate day;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Employee employee;
    private String ID;

    /**
     * Constructor for a shift.
     * @param day the date the shift occurs.
     * @param startTime the start time of the shift.
     * @param endTime the end time of the shift.
     * @param employee the employee assigned to the shift.
     */
    public Shift(LocalDate day, LocalDateTime startTime, LocalDateTime endTime, Employee employee) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
        this.ID = String.valueOf(iDCounter++);
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getID() {
        return this.ID;
    }

    public float length() {
        return (float) Duration.between(startTime, endTime).toMinutes() / 60;
    }
}
