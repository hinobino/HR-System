package data_access;

import entity.Employee;
import entity.User;
import use_case.activate_account.ActivateAccountUserDataAccessInterface;
import use_case.create_employee.CreateEmployeeUserDataAccessInterface;
import use_case.employee_list.EmployeeListUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface, ActivateAccountUserDataAccessInterface, CreateEmployeeUserDataAccessInterface,
        EmployeeListUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUserID;

    @Override
    public boolean existsByName(String userID) {
        return users.containsKey(userID);
    }

    @Override
    public void save(User user) {
        users.put(user.getUserID(), user);
    }

    @Override
    public User get(String userID) {
        return users.get(userID);
    }

    @Override
    public void setCurrentUserID(String iD) {
        this.currentUserID = iD;
    }

    @Override
    public String getCurrentUserID() {
        return this.currentUserID;
    }

    /* TODO: given a userID and a password, find the Employee with the createdID, set their password, and set their
    status to "ACTIVE" */
    // @Override
    public void activateUser() {

    }

    @Override
    public Map<String, Employee> getEmployees() {
        Map<String, Employee> employees = new HashMap<>();

        for (Map.Entry<String, User> userObj : users.entrySet()) {
            if (userObj.getValue() instanceof Employee) {
                employees.put(userObj.getKey(), (Employee) userObj.getValue());
            }
        }

        return employees;
    }

}
