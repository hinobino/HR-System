package interface_adapter.manage_employee;

import use_case.manage_employee.ManageEmployeeInputBoundary;

public class ManageEmployeeController {

    private final ManageEmployeeInputBoundary manageEmployeeInteractor;

    public ManageEmployeeController(ManageEmployeeInputBoundary manageEmployeeInteractor) {
        this.manageEmployeeInteractor = manageEmployeeInteractor;
    }

    public void switchToEmployeeListView() {
        manageEmployeeInteractor.switchToEmployeeListView();
    }
}
