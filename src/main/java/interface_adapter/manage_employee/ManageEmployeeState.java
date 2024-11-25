package interface_adapter.manage_employee;

import entity.Employee;

public class ManageEmployeeState {

    private Employee employee;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getUserId() {
        return employee.getUserID();
    }
}
