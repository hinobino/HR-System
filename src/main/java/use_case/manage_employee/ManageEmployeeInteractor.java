package use_case.manage_employee;

import entity.Employee;

/*
 * The Manage Employee Interactor
 */
public class ManageEmployeeInteractor implements ManageEmployeeInputBoundary {

    private final ManageEmployeeOutputBoundary manageEmployeeOutputBoundary;

    public ManageEmployeeInteractor(ManageEmployeeOutputBoundary manageEmployeeOutputBoundary) {
        this.manageEmployeeOutputBoundary = manageEmployeeOutputBoundary;
    }

    @Override
    public void switchToEmployeeListView() {
        manageEmployeeOutputBoundary.switchToEmployeeListView();
    }
}
