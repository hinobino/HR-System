package use_case.manage_employee;

import entity.Employee;

/**
 * The Input Data for the Manage Employee Use Case.
 */
public class ManageEmployeeInputData {

    private final Employee employee;

    public ManageEmployeeInputData(Employee employee) {
        this.employee = employee;
    }

    public String getUserId() {
        return employee.getUserID();
    }

    public String getPassword() {
        return employee.getPassword();
    }

    public String getStatus() {
        return employee.getStatus();
    }
}
