package interface_adapter.schedule_shift;

import interface_adapter.ViewModel;

/**
 * View model for the ScheduleShift use case.
 */
public class ScheduleShiftViewModel extends ViewModel<ScheduleShiftState> {

    public static final String TITLE_LABEL = "Schedule Shift";
    public static final String DAY_LABEL = "Date of Shift (DD/MM/YYYY)";
    public static final String START_TIME_LABEL = "Shift Start Time (24-hour format)";
    public static final String END_TIME_LABEL = "Shift End Time (24-hour format)";
    public static final String EMPLOYEE_LABEL = "Employee Assigned to Shift";

    public static final String SCHEDULE_SHIFT_BUTTON_LABEL = "Schedule Shift";
    public static final String BACK_WELCOME_LABEL = "Back";

    public ScheduleShiftViewModel() {
        super("schedule shift");
        setState(new ScheduleShiftState());
    }
}
