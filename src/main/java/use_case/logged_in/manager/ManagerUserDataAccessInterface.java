package use_case.logged_in.manager;

import entity.Employee;
import entity.Shift;

import java.util.Map;

public interface ManagerUserDataAccessInterface {

    /**
     * Returns the userID of the curren user of the application.
     * @return the userID of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUserID();

    /**
     * Returns a list of all the registered employee objects.
     * @param currentManagerID the ID of the current manager whose employees we are retrieving.
     * @return a Map of type Employee, containing all registered employees.
     */
    Map<String, Employee> getEmployees(String currentManagerID);

}
