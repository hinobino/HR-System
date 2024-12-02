package use_case.view_schedule;

import entity.Shift;
import entity.WorkWeek;

import java.awt.*;
import java.util.List;

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
        WorkWeek workWeek = scheduleInputData.getWorkWeek();
        List<Shift> shifts = scheduleInputData.getShifts();

        WorkWeek previousWeek = new WorkWeek(workWeek.getStartOfWeek().plusWeeks(-1), shifts);

        ScheduleOutputData scheduleOutputData =
                new ScheduleOutputData(weekContainer, previousWeek, shifts);
        scheduleOutputBoundary.showPreviousWeek(scheduleOutputData);
    }

    @Override
    public void showNextWeek(ScheduleInputData scheduleInputData) {
        Container weekContainer = scheduleInputData.getWeekContainer();
        WorkWeek workWeek = scheduleInputData.getWorkWeek();
        List<Shift> shifts = scheduleInputData.getShifts();

        WorkWeek nextWeek = new WorkWeek(workWeek.getStartOfWeek().plusWeeks(1), shifts);

        ScheduleOutputData scheduleOutputData =
                new ScheduleOutputData(weekContainer, nextWeek, shifts);
        scheduleOutputBoundary.showNextWeek(scheduleOutputData);
    }
}
