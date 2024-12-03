package use_case.view_schedule;

import entity.Shift;
import entity.WorkWeek;
import entity.WorkWeekFactory;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class ScheduleInteractor implements ScheduleInputBoundary {

    private final ScheduleUserDataAccessInterface userDataAccessObject;
    private final ScheduleOutputBoundary scheduleOutputBoundary;
    private final WorkWeekFactory workWeekFactory;

    public ScheduleInteractor(ScheduleUserDataAccessInterface userDataAccessObject,
                              ScheduleOutputBoundary scheduleOutputBoundary,
                              WorkWeekFactory workWeekFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.scheduleOutputBoundary = scheduleOutputBoundary;
        this.workWeekFactory = workWeekFactory;
    }

    @Override
    public void showStartWeek(ScheduleInputData scheduleInputData) {
        Container weekContainer = scheduleInputData.getWeekContainer();
        WorkWeek startWeek = scheduleInputData.getWorkWeek();
        List<Shift> shifts = scheduleInputData.getShifts();

        ScheduleOutputData scheduleOutputData =
                new ScheduleOutputData(weekContainer, startWeek, shifts);
        scheduleOutputBoundary.showStartWeek(scheduleOutputData);
    }

    @Override
    public void showPreviousWeek(ScheduleInputData scheduleInputData) {
        Container weekContainer = scheduleInputData.getWeekContainer();
        WorkWeek workWeek = scheduleInputData.getWorkWeek();
        List<Shift> shifts = scheduleInputData.getShifts();

        WorkWeek previousWeek = workWeekFactory.create(
                workWeek.getStartOfWeek().plusWeeks(-1), shifts);

        ScheduleOutputData scheduleOutputData =
                new ScheduleOutputData(weekContainer, previousWeek, shifts);
        scheduleOutputBoundary.showPreviousWeek(scheduleOutputData);
    }

    @Override
    public void showNextWeek(ScheduleInputData scheduleInputData) {
        Container weekContainer = scheduleInputData.getWeekContainer();
        WorkWeek workWeek = scheduleInputData.getWorkWeek();
        List<Shift> shifts = scheduleInputData.getShifts();

        WorkWeek nextWeek = workWeekFactory.create(
                workWeek.getStartOfWeek().plusWeeks(1), shifts);

        ScheduleOutputData scheduleOutputData =
                new ScheduleOutputData(weekContainer, nextWeek, shifts);
        scheduleOutputBoundary.showNextWeek(scheduleOutputData);
    }
}
