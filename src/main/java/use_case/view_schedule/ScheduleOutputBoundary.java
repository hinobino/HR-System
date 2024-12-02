package use_case.view_schedule;

public interface ScheduleOutputBoundary {
    void showPreviousWeek(ScheduleOutputData outputData);

    void showNextWeek(ScheduleOutputData scheduleOutputData);
}
