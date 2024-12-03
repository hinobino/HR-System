package interface_adapter.export_calendar;

import entity.Employee;
import use_case.export_calendar.ExportCalendarInputBoundary;
import use_case.export_calendar.ExportCalendarInputData;
import use_case.login.LoginInputData;
import view.LoginView;

public class ExportCalendarController {
    private final ExportCalendarInputBoundary exportCalendarInteractor;

    public ExportCalendarController(ExportCalendarInputBoundary exportCalendarInteractor) {
        this.exportCalendarInteractor = exportCalendarInteractor;
    }

    /**
     * Executes the ExportCalendar Use Case.
     *
     * @param employee the employee
     */
    public void execute(Employee employee) {
        final ExportCalendarInputData exportCalendarInputData = new ExportCalendarInputData(employee);

        exportCalendarInteractor.execute(exportCalendarInputData);
    }
}
