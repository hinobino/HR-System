package entity;

import java.util.HashMap;
import java.util.Map;

public class Manager extends User {

    private final Map<String, Employee> employees;

    public Manager(String userID, String password) {
        super(userID, password);
        employees = new HashMap<>();
    }

    public Employee getEmployee(String userID) {
        return employees.get(userID);
    }

    public void addEmployee(Employee employee) {
        employees.put(employee.getUserID(), employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee.getUserID());
    }

    public void removeEmployee(String userID) {
        employees.remove(userID);
    }

}
