package use_case.export_calendar;

public interface ExportCalendarOutputBoundary {
    /**
     * Prepares the success view for the ExportCalendar use case.
     * @param outputData the output data.
     */
    void prepareSuccessView(ExportCalendarOutputData outputData);
}
