package use_case.schedule_shift;

/**
 * Input boundary for the ScheduleShift use case.
 */
public interface ScheduleShiftInputBoundary {
    /**
     * Executes the ScheduleShift use case.
     * @param scheduleShiftInputData the input data.
     */
    void execute(ScheduleShiftInputData scheduleShiftInputData);

    /**
     * Executes the returnToManagerView use case.
     */
    void returnToManagerView();
}
