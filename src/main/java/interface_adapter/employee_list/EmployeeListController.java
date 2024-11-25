package interface_adapter.employee_list;

import entity.Employee;
import use_case.employee_list.EmployeeListInputBoundary;
import view.EmployeeListView;

public class EmployeeListController {
    private final EmployeeListInputBoundary employeeListInteractor;

    public EmployeeListController(EmployeeListInputBoundary employeeListInteractor) {
        this.employeeListInteractor = employeeListInteractor;
    }

    /**
     * Executes the select employee use case
     */
    public void selectEmployee(Employee employee) {
        employeeListInteractor.selectEmployee(employee);
    }

    /**
     * Executes the switch to manager view use case.
     */
    public void switchToManagerView() {
        employeeListInteractor.switchToManagerView();
    }
}
