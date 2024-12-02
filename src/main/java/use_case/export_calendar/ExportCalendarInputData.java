package use_case.export_calendar;

import entity.Employee;

/**
 * Input data for the ExportCalendar use case.
 */
public class ExportCalendarInputData {
    private final Employee employee;

    public ExportCalendarInputData(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
