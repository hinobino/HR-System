package use_case.employee_list;

import entity.Employee;

import java.util.Map;

/**
 * DAO for the Create Employee Use Case.
 */
public interface EmployeeListUserDataAccessInterface {

    /**
     * Get a list of all the employees.
     * @return userIDs mapped to each employee object
     */
    Map<String, Employee> getEmployees();
}
