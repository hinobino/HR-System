package use_case.view_schedule;

import java.awt.*;

public class ScheduleInputData {

    private final Container weekContainer;
    private final String weekName;

    public ScheduleInputData(Container weekContainer, String weekName) {
        this.weekContainer = weekContainer;
        this.weekName = weekName;
    }

    public Container getWeekContainer() { return weekContainer; }

    public String getWeekName() { return weekName; }
}
