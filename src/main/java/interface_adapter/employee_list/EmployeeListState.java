package interface_adapter.employee_list;

import entity.Employee;

import java.util.ArrayList;
import java.util.Map;

/**
 * The state class for the employee list usecase.
 */
public class EmployeeListState {

    private Map<String, Employee> employees;
    private String[][] employeeList;

    public void setEmployeeList(Map<String, Employee> data) {
        this.employees = data;
    }

    public Object[][] getEmployeeList() {
        this.employeeList = new String[employees.size()][];
        int i = 0;
        for (Map.Entry<String, Employee> userObj : employees.entrySet()) {
            ArrayList<String> row = new ArrayList<>();
            row.add(userObj.getKey());
            row.add(userObj.getValue().getStatus());
            employeeList[i] = row.toArray(new String[row.size()]);
        }
        return employeeList;
    }

    public Employee getEmployee(String id) {
        return employees.get(id);
    }
}
