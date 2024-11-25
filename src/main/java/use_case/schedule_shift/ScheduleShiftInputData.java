package use_case.schedule_shift;

import entity.Employee;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Input data for the ScheduleShift use case.
 */
public class ScheduleShiftInputData {

    private final LocalDate day;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Employee employee;

    public ScheduleShiftInputData(LocalDate day, LocalTime startTime, LocalTime endTime, Employee employee) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
    }

    public LocalDate getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Employee getEmployee() {
        return employee;
    }
}