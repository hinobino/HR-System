package use_case.view_schedule;

import java.awt.*;

public class ScheduleOutputData {

    private final Container weekContainer;
    private final String weekName;

    public ScheduleOutputData(Container weekContainer, String weekName) {
        this.weekContainer = weekContainer;
        this.weekName = weekName;
    }

    public Container getWeekContainer() { return weekContainer; }

    public String getWeekName() { return weekName; }
}
