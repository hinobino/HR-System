package use_case.view_schedule;

import view.ScheduleWeekView;

import java.awt.*;

public class ScheduleInteractor implements ScheduleInputBoundary {

    private final ScheduleUserDataAccessInterface userDataAccessObject;
    private final ScheduleOutputBoundary scheduleOutputBoundary;

    public ScheduleInteractor(ScheduleUserDataAccessInterface userDataAccessObject,
                              ScheduleOutputBoundary scheduleOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.scheduleOutputBoundary = scheduleOutputBoundary;
    }

    @Override
    public void showPreviousWeek(ScheduleInputData scheduleInputData) {
        Container weekContainer = scheduleInputData.getWeekContainer();
        String weekName = scheduleInputData.getWeekName();
        ScheduleOutputData scheduleOutputData = new ScheduleOutputData(weekContainer, weekName);
        scheduleOutputBoundary.showPreviousWeek(scheduleOutputData);
    }

    @Override
    public void showNextWeek(ScheduleInputData scheduleInputData) {
        Container weekContainer = scheduleInputData.getWeekContainer();
        String weekName = scheduleInputData.getWeekName();

        ScheduleOutputData scheduleOutputData = new ScheduleOutputData(weekContainer, weekName);
        scheduleOutputBoundary.showNextWeek(scheduleOutputData);
    }
}
