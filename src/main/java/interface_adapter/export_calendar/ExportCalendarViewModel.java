package interface_adapter.export_calendar;

import interface_adapter.ViewModel;

public class ExportCalendarViewModel extends ViewModel<ExportCalendarState> {
    /**
     * A ViewModel Constructor.
     *
     */
    public ExportCalendarViewModel() {
        super("schedule");
        setState(new ExportCalendarState());
    }
}
