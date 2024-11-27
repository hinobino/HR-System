package use_case.employee_list;

import java.util.ArrayList;

public interface EmployeeListOutputBoundary {

    void selectEmployee(EmployeeListOutputData employeeListOutputData);

    void switchToCreateEmployeeView();

    void switchToManagerView();
}
