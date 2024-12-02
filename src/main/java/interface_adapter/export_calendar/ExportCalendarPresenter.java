package interface_adapter.export_calendar;

import use_case.export_calendar.ExportCalendarOutputBoundary;
import use_case.export_calendar.ExportCalendarOutputData;

public class ExportCalendarPresenter implements ExportCalendarOutputBoundary {
    /**
     * Prepares the success view for the ExportCalendar use case.
     *
     * @param outputData the output data.
     */
    @Override
    public void prepareSuccessView(ExportCalendarOutputData outputData) {

    }
}
