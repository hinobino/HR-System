package use_case.view_schedule;

import entity.Shift;
import entity.WorkWeek;

import java.awt.*;
import java.util.List;

public class ScheduleInputData {

    private final Container weekContainer;
    private final WorkWeek workWeek;
    private final List<Shift> shifts;

    public ScheduleInputData(Container weekContainer, WorkWeek workWeek, List<Shift> shifts) {
        this.weekContainer = weekContainer;
        this.workWeek = workWeek;
        this.shifts = shifts;
    }

    public Container getWeekContainer() { return weekContainer; }

    public WorkWeek getWorkWeek() { return workWeek; }

    public List<Shift> getShifts() { return shifts; }
}
