package use_case.export_calendar;

import entity.Employee;
import entity.Shift;

import java.util.List;

/**
 * DAI for the ExportCalendar use case.
 */
public class ExportCalendarOutputData {
    private final Employee employee;

    public ExportCalendarOutputData(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {return employee;}

    public List<Shift> getShifts() {return employee.getShifts();}
}
