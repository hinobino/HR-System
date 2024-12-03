package interface_adapter.view_schedule;

import entity.Shift;
import entity.WorkWeek;
import use_case.view_schedule.ScheduleInputBoundary;
import use_case.view_schedule.ScheduleInputData;

import java.awt.*;
import java.util.List;

public class ScheduleController {

    private final ScheduleInputBoundary scheduleInputBoundary;

    public ScheduleController(ScheduleInputBoundary scheduleInputBoundary) {
        this.scheduleInputBoundary = scheduleInputBoundary;
    }

    public void showStartWeek(Container weekContainer, WorkWeek workWeek, List<Shift> shifts) {
        final ScheduleInputData scheduleInputData =
                new ScheduleInputData(weekContainer, workWeek, shifts);
        scheduleInputBoundary.showStartWeek(scheduleInputData);
    }

    public void showPreviousWeek(Container weekContainer, WorkWeek workWeek, List<Shift> shifts) {
        final ScheduleInputData scheduleInputData =
                new ScheduleInputData(weekContainer, workWeek, shifts);
        scheduleInputBoundary.showPreviousWeek(scheduleInputData);
    }

    public void showNextWeek(Container weekContainer, WorkWeek workWeek, List<Shift> shifts) {
        final ScheduleInputData scheduleInputData =
                new ScheduleInputData(weekContainer, workWeek, shifts);
        scheduleInputBoundary.showNextWeek(scheduleInputData);
    }
}
