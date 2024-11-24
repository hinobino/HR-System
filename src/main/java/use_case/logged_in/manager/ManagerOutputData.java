package use_case.logged_in.manager;

import entity.Employee;

import java.util.Map;

public class ManagerOutputData {

    private final Map<String, Employee> employees;

    public ManagerOutputData(Map<String, Employee> employees) {
        this.employees = employees;
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }
}
