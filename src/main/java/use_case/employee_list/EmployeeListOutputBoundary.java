package use_case.employee_list;

import java.util.ArrayList;

public interface EmployeeListOutputBoundary {
    /**
     * Creates the list of employees.
     */
    void createEmployeeList(Object[][] data);
}
