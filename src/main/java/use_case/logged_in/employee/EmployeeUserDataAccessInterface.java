package use_case.logged_in.employee;

import entity.Shift;

import java.util.List;

public interface EmployeeUserDataAccessInterface {

    /**
     * Returns the userID of the curren user of the application.
     * @return the userID of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUserID();

    /**
     * Returns a list of all the registered shift objects.
     * @param currentEmployeeID the ID of the current manager whose employees we are retrieving.
     * @return a Map of type Employee, containing all registered employees.
     */
    List<Shift> getShifts(String currentEmployeeID);

}


