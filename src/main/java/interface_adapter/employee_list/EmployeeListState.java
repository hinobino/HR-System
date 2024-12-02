package interface_adapter.employee_list;

import entity.Employee;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * The state class for the employee list usecase.
 */
public class EmployeeListState {

    private Map<String, Employee> employees;
    private Object[][] employeeList;

    public void setEmployeeList(Map<String, Employee> data) {
        this.employees = data;
    }

    public Object[][] getEmployeeList() {
        this.employeeList = new Object[employees.size()][];
        int i = 0;
        for (Map.Entry<String, Employee> userObj : employees.entrySet()) {
            ArrayList<Object> row = new ArrayList<>();
            row.add(userObj.getKey());
            row.add(userObj.getValue().getStatus().toUpperCase());
            row.add(new JButton(EmployeeListViewModel.MANAGE_EMPLOYEE_LABEL));
            employeeList[i] = row.toArray(new Object[row.size()]);
            i++;
        }
        return employeeList;
    }

    public Employee getEmployee(String id) {
        return employees.get(id);
    }
}
