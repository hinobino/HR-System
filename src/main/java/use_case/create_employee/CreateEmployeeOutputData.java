package use_case.create_employee;

import entity.Employee;

import java.util.Map;

/**
 * Output data for the Create Employee Use case.
 */
public class CreateEmployeeOutputData {

    private final String newUserID;

    private final Map<String, Employee> employees;

    private final boolean useCaseFailed;

    public CreateEmployeeOutputData(String userID, Map<String, Employee> employees,
                                    boolean useCaseFailed) {
        this.newUserID = userID;
        this.employees = employees;
        this.useCaseFailed = useCaseFailed;
    }

    public String getNewUserID() {
        return newUserID;
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
