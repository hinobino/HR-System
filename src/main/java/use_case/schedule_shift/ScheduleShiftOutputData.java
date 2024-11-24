package use_case.schedule_shift;

import entity.Employee;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Output data for the ScheduleShift use case.
 */
public class ScheduleShiftOutputData {

    private final LocalDate day;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Employee employee;

    private final boolean useCaseFailed;

    public ScheduleShiftOutputData(LocalDate day, LocalTime startTime, LocalTime endTime, Employee employee,
                                   boolean useCaseFailed) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
        this.useCaseFailed = useCaseFailed;
    }


    public boolean isUseCaseFailed() {
        return useCaseFailed;
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
