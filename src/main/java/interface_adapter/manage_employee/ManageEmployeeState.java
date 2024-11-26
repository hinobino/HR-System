package interface_adapter.manage_employee;

import entity.Employee;

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
