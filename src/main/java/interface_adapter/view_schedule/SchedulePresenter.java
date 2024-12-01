package interface_adapter.view_schedule;

import use_case.view_schedule.ScheduleOutputBoundary;
import use_case.view_schedule.ScheduleOutputData;
import view.ScheduleWeekView;

import java.awt.*;
import java.time.LocalDate;

public class SchedulePresenter implements ScheduleOutputBoundary {

    public SchedulePresenter() {

    }

    @Override
    public void showPreviousWeek(ScheduleOutputData scheduleOutputData) {
//        ScheduleViewModel scheduleViewModel = new ScheduleViewModel(); // get from output data
//        LocalDate date = LocalDate.now(); // get from output data, calculate minus week in interactor
//        ScheduleWeekView scheduleWeekView = new ScheduleWeekView(scheduleViewModel, date);

        String weekName = scheduleOutputData.getWeekName();
        Container weekContainer = scheduleOutputData.getWeekContainer();
        CardLayout weekLayout = (CardLayout) weekContainer.getLayout();

        weekLayout.show(weekContainer, weekName);
    }

    @Override
    public void showNextWeek(ScheduleOutputData scheduleOutputData) {
        String weekName = scheduleOutputData.getWeekName();
        Container weekContainer = scheduleOutputData.getWeekContainer();
        CardLayout weekLayout = (CardLayout) weekContainer.getLayout();
        weekLayout.show(weekContainer, weekName);
    }
}
