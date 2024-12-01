package interface_adapter.view_schedule;

import use_case.view_schedule.ScheduleInputBoundary;
import use_case.view_schedule.ScheduleInputData;

import java.awt.*;

public class ScheduleController {

    private final ScheduleInputBoundary scheduleInputBoundary;

    public ScheduleController(ScheduleInputBoundary scheduleInputBoundary) {
        this.scheduleInputBoundary = scheduleInputBoundary;
    }
    public void showPreviousWeek(Container weekContainer, String weekName) {
        final ScheduleInputData scheduleInputData = new ScheduleInputData(weekContainer, weekName);
        scheduleInputBoundary.showPreviousWeek(scheduleInputData);
    }

    public void showNextWeek(Container weekContainer, String weekName) {
        final ScheduleInputData scheduleInputData = new ScheduleInputData(weekContainer, weekName);
        scheduleInputBoundary.showNextWeek(scheduleInputData);
    }
}
