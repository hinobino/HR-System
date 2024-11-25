package use_case.employee_list;

import entity.Employee;

public class EmployeeListOutputData {

    private final Employee employee;

    public EmployeeListOutputData(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
