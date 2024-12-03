package data_access;

import entity.Employee;
import entity.Manager;
import entity.Shift;
import entity.User;
import entity.Workday;
import use_case.activate_account.ActivateAccountUserDataAccessInterface;
import use_case.create_employee.CreateEmployeeUserDataAccessInterface;
import use_case.export_calendar.ExportCalendarUserDataAccessInterface;
import use_case.logged_in.employee.EmployeeUserDataAccessInterface;
import use_case.logged_in.manager.ManagerUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.manage_employee.ManageEmployeeUserDataAccessInterface;
import use_case.manage_shifts.ManageShiftsUserDataAccessInterface;
import use_case.schedule_shift.ScheduleShiftUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.view_schedule.ScheduleUserDataAccessInterface;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
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
        ManageEmployeeUserDataAccessInterface,
        ScheduleShiftUserDataAccessInterface,
        ManagerUserDataAccessInterface,
        EmployeeUserDataAccessInterface,
        ManageShiftsUserDataAccessInterface,
        ScheduleUserDataAccessInterface,
        ExportCalendarUserDataAccessInterface {

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

        if (!workdayExists(newShift.getDay())) {
            workdays.put(newShift.getDay(), workday);
        }
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
    public void setPayRate(String userID, Double newPayRate) {
        Employee employee = (Employee) users.get(userID);
        employee.setPayRate(newPayRate);
    }

    @Override
    public void activateUser(String userID, String password) {
        Employee employee = (Employee) users.get(userID);
        employee.setPassword(password);
        employee.setStatus("active");
        employee.setStartDate(LocalDate.now());
        employee.setEndDate(null);
    }

    @Override
    public void inactivateUser(String userID) {
        Employee employee = (Employee) users.get(userID);
        employee.setStatus("inactive");
        employee.setEndDate(LocalDate.now());
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

    @Override
    public Map<String, Employee> getEmployeesByManager(String managerID) {
        return ((Manager) users.get(managerID)).getEmployees();
    }

    public List<Shift> getShifts(String employeeID) {
        return ((Employee) users.get(employeeID)).getShifts();
    }

    public void saveWorkday(LocalDate date, Workday workday) {
        workdays.put(date, workday);
    }

    @Override
    public Employee getEmployee() {
        return ((Employee) users.get(currentUserID));
    }
}
