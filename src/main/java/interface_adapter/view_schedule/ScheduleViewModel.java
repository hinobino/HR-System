package interface_adapter.view_schedule;

import interface_adapter.ViewModel;

import java.awt.*;

public class ScheduleViewModel extends ViewModel<ScheduleState> {

    public static final String VIEW_LABEL = "Schedule";
    public static final String DOWNLOAD_LABEL = "Download";
    public static final Color GRID_COLOR = Color.getHSBColor(0, 0, (float) 0.85);
    public static final Color HEADER_COLOR = Color.getHSBColor(0, 0, (float) 0.95);
    public static final Color HOUR_COLOR = Color.WHITE;
    public static final Color HALF_HOUR_COLOR = Color.getHSBColor(0, 0, (float) 0.95);
    public static final Color SHIFT_COLOR = new Color(186, 82, 114);
    public static final int TIME_TO_GRIDY_OFFSET = 17;

    public ScheduleViewModel() {
        super("schedule");
        setState(new ScheduleState());
    }
}
