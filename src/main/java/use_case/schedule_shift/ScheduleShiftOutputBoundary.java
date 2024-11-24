package use_case.schedule_shift;

/**
 * Output boundary for the schedule shift use case.
 */
public interface ScheduleShiftOutputBoundary {

    /**
     * Prepares the success view for the ScheduleShift use case.
     * @param outputData the output data.
     */
    void prepareSuccessView(ScheduleShiftOutputData outputData);

    /**
     * Prepare the fail view for the ScheduleShift use case.
     * @param errorMessage the error message generated.
     */
    void prepareFailView(String errorMessage);

    /**
     * Executes the returnToManagerView use case.
     */
    void returnToManagerView();

}
