package use_case.logged_in.manager;

import entity.Employee;
import entity.WorkWeek;
import interface_adapter.logged_in.LoggedInState;

import java.util.List;
import java.util.Map;

public class ManagerOutputData {

    private final Map<String, Employee> employees;
    private List<WorkWeek> weeks;
    private LoggedInState loggedInState;

    public ManagerOutputData(Map<String, Employee> employees) {
        this.employees = employees;
    }

    public ManagerOutputData(LoggedInState loggedInState, Map<String, Employee> employees, List<WorkWeek> weeks) {
        this.loggedInState = loggedInState;
        this.employees = employees;
        this.weeks = weeks;
    }

    public String getUserID() { return loggedInState.getUserID(); }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public List<WorkWeek> getWeeks() { return weeks; }

    public LoggedInState getLoggedInState() { return loggedInState; }

}
