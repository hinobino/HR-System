package use_case.export_calendar;

/**
 * Input Boundary for actions which are related to exporting a calendar.
 */
public interface ExportCalendarInputBoundary {
    /**
     * Executes the export_calendar use case.
     * @param exportCalendarInputData the input data
     */
    void execute(ExportCalendarInputData exportCalendarInputData);
}
