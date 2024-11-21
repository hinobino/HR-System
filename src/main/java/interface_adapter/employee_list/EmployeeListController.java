package interface_adapter.employee_list;

import use_case.employee_list.EmployeeListInputBoundary;
import view.EmployeeListView;

public class EmployeeListController {
    private final EmployeeListInputBoundary employeeListInteractor;

    public EmployeeListController(EmployeeListInputBoundary employeeListInteractor) {
        this.employeeListInteractor = employeeListInteractor;
    }

    /**
     * Executes the create employee list use case.
     */
    public void createEmployeeList() {
        employeeListInteractor.createEmployeeList();
    }

    /**
     * Executes the switch to manager view use case.
     */
    public void switchToManagerView() {
        employeeListInteractor.switchToManagerView();
    }
}
