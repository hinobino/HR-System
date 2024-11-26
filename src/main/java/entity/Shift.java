package entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The representation of a shift.
 */
public class Shift {

    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;
    private Employee employee;

    /**
     * Constructor for a shift.
     * @param day the date the shift occurs.
     * @param startTime the start time of the shift.
     * @param endTime the end time of the shift.
     * @param employee the employee assigned to the shift.
     */
    public Shift(LocalDate day, LocalTime startTime, LocalTime endTime, Employee employee) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public float length() {
        return (float) Duration.between(startTime, endTime).toMinutes() / 60;
    }
}
