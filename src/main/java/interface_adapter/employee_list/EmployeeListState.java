package interface_adapter.employee_list;

import entity.Employee;

import java.util.ArrayList;

/**
 * The state class for the employee list usecase.
 */
public class EmployeeListState {

    private Object[][] employeeList;

    public EmployeeListState() {
        employeeList = new Object[1][2];
    }

    public void setEmployeeList(Object[][] data) {
        this.employeeList = data;
    }

    public Object[][] getEmployeeList() {
        return employeeList;
    }
}
