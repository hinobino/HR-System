package interface_adapter.manage_employee;

import entity.Employee;

import java.time.LocalDate;

public class ManageEmployeeState {

    private Employee employee;
    private Boolean payError = false;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getUserId() {
        return employee.getUserID();
    }

    public String getEmploymentPeriod() {
        String employmentPeriod = "";
        if(employee.getStartDate() != null) {
            employmentPeriod += "Employee from " + employee.getStartDate().toString();
        }
        if(employee.getEndDate() != null) {
            employmentPeriod += " to " + employee.getEndDate().toString();
        }
        return employmentPeriod;
    }

    public String getStaus() {
        return employee.getStatus().toUpperCase();
    }

    public String getPay() {
        return Double.toString(employee.getPayRate());
    }

    public String getHoursWorked() {
        return Double.toString(employee.getHoursWorked());
    }

    public Boolean getPayError() {
        return payError;
    }

    public void togglePayError() {
        payError = !payError;
    }
}
