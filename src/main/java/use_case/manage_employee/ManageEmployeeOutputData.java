package use_case.manage_employee;

import entity.Employee;

public class ManageEmployeeOutputData {

    private Employee employee;

    public ManageEmployeeOutputData(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}