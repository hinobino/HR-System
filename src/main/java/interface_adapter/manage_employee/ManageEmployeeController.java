package interface_adapter.manage_employee;

import entity.Employee;
import use_case.manage_employee.ManageEmployeeInputBoundary;
import use_case.manage_employee.ManageEmployeeInputData;

public class ManageEmployeeController {

    private final ManageEmployeeInputBoundary manageEmployeeInteractor;

    public ManageEmployeeController(ManageEmployeeInputBoundary manageEmployeeInteractor) {
        this.manageEmployeeInteractor = manageEmployeeInteractor;
    }

    public void changeStatus(Employee employee) {
        final ManageEmployeeInputData manageEmployeeInputData = new ManageEmployeeInputData(employee);
        manageEmployeeInteractor.changeStatus(manageEmployeeInputData);
    }

    public void switchToEmployeeListView() {
        manageEmployeeInteractor.switchToEmployeeListView();
    }
}
