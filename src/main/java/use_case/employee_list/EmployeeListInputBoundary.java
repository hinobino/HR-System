package use_case.employee_list;

import entity.Employee;

public interface EmployeeListInputBoundary {

    /**
     * Executes the select employee use case.
     */
    void selectEmployee(Employee employee);

    /**
     * Executes the switch to create employee view use case.
     */
    void switchToCreateEmployeeView();

    /**
     * Executes the switch to manger view use case.
     */
    void switchToManagerView();

}
