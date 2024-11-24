package data_access;

import entity.Employee;
import entity.Shift;
import entity.User;
import entity.Workday;
import use_case.activate_account.ActivateAccountUserDataAccessInterface;
import use_case.create_employee.CreateEmployeeUserDataAccessInterface;
import use_case.employee_list.EmployeeListUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.schedule_shift.ScheduleShiftUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ActivateAccountUserDataAccessInterface,
        CreateEmployeeUserDataAccessInterface,
        LogoutUserDataAccessInterface, 
        EmployeeListUserDataAccessInterface, 
        ScheduleShiftUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();
    private final Map<LocalDate, Workday> workdays = new HashMap<>();

    private String currentUserID;

    @Override
    public boolean existsByName(String userID) {
        return users.containsKey(userID);
    }

    @Override
    public boolean workdayExists(LocalDate day) {
        return workdays.containsKey(day);
    }

    @Override
    public Workday getWorkdayByDate(LocalDate day) {
        return workdays.get(day);
    }

    @Override
    public void addShiftToWorkday(Shift newShift, Workday workday) {
        workday.addShift(newShift);
    }

    @Override
    public void save(User user) {
        users.put(user.getUserID(), user);
    }

    @Override
    public void save(Shift shift) {
        Employee employee = shift.getEmployee();
        employee.addShift(shift);
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

     @Override
    public void activateUser(String userID, String password) {
        Employee employee = (Employee) users.get(userID);
        employee.setPassword(password);
        employee.setStatus("active");

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
