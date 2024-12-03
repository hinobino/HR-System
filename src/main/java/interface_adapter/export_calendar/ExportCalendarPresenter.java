package interface_adapter.export_calendar;

import entity.Shift;
import interface_adapter.ViewManagerModel;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.property.immutable.ImmutableCalScale;
import net.fortuna.ical4j.model.property.immutable.ImmutableVersion;
import use_case.export_calendar.ExportCalendarOutputBoundary;
import use_case.export_calendar.ExportCalendarOutputData;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;

import static net.fortuna.ical4j.model.property.immutable.ImmutableCalScale.GREGORIAN;

/**
 * Presenter for the ExportCalendar use case.
 */
public class ExportCalendarPresenter implements ExportCalendarOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ExportCalendarViewModel exportCalendarViewModel;

    public ExportCalendarPresenter(ViewManagerModel viewManagerModel, ExportCalendarViewModel exportCalendarViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.exportCalendarViewModel = exportCalendarViewModel;
    }

    /**
     * Prepares the success view for the ExportCalendar use case.
     *
     * @param outputData the output data.
     */
    @Override
    public void prepareSuccessView(ExportCalendarOutputData outputData) {
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Employee Shifts//iCal4j 3.0//EN"));
        calendar.getProperties().add(ImmutableVersion.VERSION_2_0);
        calendar.getProperties().add(ImmutableCalScale.GREGORIAN);

        // Add shifts as events to the calendar
        for (Shift shift : outputData.getShifts()) {
            Date startDate = Date.from(shift.getStartTime().atZone(ZoneId.systemDefault()).toInstant());
            Date endDate = Date.from(shift.getEndTime().atZone(ZoneId.systemDefault()).toInstant());

            // Create a VEvent for the shift
            VEvent shiftEvent = new VEvent((Temporal) startDate, (Temporal) endDate,
                    "Shift for " + outputData.getEmployee());
            shiftEvent.getProperties().add(new Description("Shift hours"));
            calendar.getComponents().add(shiftEvent);
        }
        // Write the calendar to a file
        try (FileOutputStream fout = new FileOutputStream("Employee Shifts")) {
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, fout);

        System.out.println("Calendar file created: " + "Employee Shifts");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
