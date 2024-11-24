package interface_adapter.schedule_shift;

import entity.Employee;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * State object for the ScheduleShift use case.
 */
public class ScheduleShiftState {

    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;
    private Employee employee;
    private Map<String, Employee> employees;

    private String dayError;
    private String startTimeError;
    private String endTimeError;
    private String employeeError;

    public LocalDate getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getDayError() {
        return dayError;
    }

    public String getStartTimeError() {
        return startTimeError;
    }

    public String getEndTimeError() {
        return endTimeError;
    }

    public String getEmployeeError() {
        return employeeError;
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setDayError(String dayError) {
        this.dayError = dayError;
    }

    public void setStartTimeError(String startTimeError) {
        this.startTimeError = startTimeError;
    }

    public void setEndTimeError(String endTimeError) {
        this.endTimeError = endTimeError;
    }

    public void setEmployeeError(String employeeError) {
        this.employeeError = employeeError;
    }

    public void setEmployees(Map<String, Employee> employees) {
        this.employees = employees;
    }
}
