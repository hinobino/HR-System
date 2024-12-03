package interface_adapter.export_calendar;

import entity.Employee;

public class ExportCalendarState {
    private Employee employee;
    private ExportCalendarController exportCalendarController;

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public void setExportCalendarController(ExportCalendarController exportCalendarController) {
        this.exportCalendarController = exportCalendarController;
    }
    public ExportCalendarController getExportCalendarController() {return exportCalendarController;}
}
