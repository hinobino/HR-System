package interface_adapter.view_schedule;

import entity.WorkWeek;
import use_case.view_schedule.ScheduleOutputBoundary;
import use_case.view_schedule.ScheduleOutputData;

import java.awt.*;

public class SchedulePresenter implements ScheduleOutputBoundary {

    private final ScheduleViewModel scheduleViewModel;

    public SchedulePresenter(ScheduleViewModel scheduleViewModel) {
        this.scheduleViewModel = scheduleViewModel;
    }

    @Override
    public void showPreviousWeek(ScheduleOutputData scheduleOutputData) {
        WorkWeek workWeek = scheduleOutputData.getWorkWeek();
        Container weekContainer = scheduleOutputData.getWeekContainer();
        CardLayout weekLayout = (CardLayout) weekContainer.getLayout();

        ScheduleState scheduleState = scheduleViewModel.getState();
        scheduleState.setWeek(workWeek);
        scheduleViewModel.setState(scheduleState);
        scheduleViewModel.firePropertyChanged("week");

        weekLayout.show(weekContainer, workWeek.toString());
    }

    @Override
    public void showNextWeek(ScheduleOutputData scheduleOutputData) {
        WorkWeek workWeek = scheduleOutputData.getWorkWeek();
        Container weekContainer = scheduleOutputData.getWeekContainer();
        CardLayout weekLayout = (CardLayout) weekContainer.getLayout();

        ScheduleState scheduleState = scheduleViewModel.getState();
        scheduleState.setWeek(workWeek);
        scheduleViewModel.setState(scheduleState);
        scheduleViewModel.firePropertyChanged("week");

        weekLayout.show(weekContainer, workWeek.toString());
    }
}
