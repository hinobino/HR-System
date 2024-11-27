package use_case.logged_in.manager;

import entity.Employee;
import interface_adapter.logged_in.LoggedInState;

import java.util.Map;

public class ManagerOutputData {

    private final Map<String, Employee> employees;
    private LoggedInState loggedInState;

    public ManagerOutputData(Map<String, Employee> employees) {
        this.employees = employees;
    }

    public ManagerOutputData(LoggedInState loggedInState, Map<String, Employee> employees) {
        this.loggedInState = loggedInState;
        this.employees = employees;
    }

    public String getUserID() { return loggedInState.getUserID(); }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public LoggedInState getLoggedInState() { return loggedInState; }
}
