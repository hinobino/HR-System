package interface_adapter.schedule_shift;

import entity.Employee;
import use_case.schedule_shift.ScheduleShiftInputBoundary;
import use_case.schedule_shift.ScheduleShiftInputData;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Controller for the ScheduleShift use case.
 */
public class ScheduleShiftController {

    private final ScheduleShiftInputBoundary scheduleShiftInteractor;

    public ScheduleShiftController(ScheduleShiftInputBoundary scheduleShiftInteractor) {
        this.scheduleShiftInteractor = scheduleShiftInteractor;
    }

    /**
     * Executes the ScheduleShift use case.
     * @param day the day the shift is scheduled.
     * @param startTime the starting time of the shift.
     * @param endTime the ending time of the shift.
     * @param employee the Employee assigned to the shift.
     */
    public void execute(LocalDate day, LocalTime startTime, LocalTime endTime, Employee employee) {
        final ScheduleShiftInputData scheduleShiftInputData = new ScheduleShiftInputData(day, startTime, endTime,
                employee);
        scheduleShiftInteractor.execute(scheduleShiftInputData);
    }

    /**
     * Executes the "return to manager view" use case.
     */
    public void returnToManagerView() {
        scheduleShiftInteractor.returnToManagerView();
    }

}
