package use_case.view_schedule;

public interface ScheduleOutputBoundary {

    void showStartWeek(ScheduleOutputData scheduleOutputData);

    void showPreviousWeek(ScheduleOutputData outputData);

    void showNextWeek(ScheduleOutputData scheduleOutputData);

}
