package use_case.manage_employee;

import entity.Employee;

/*
 * The Manage Employee Interactor
 */
public class ManageEmployeeInteractor implements ManageEmployeeInputBoundary {

    private final ManageEmployeeUserDataAccessInterface userDataAccessInterface;
    private final ManageEmployeeOutputBoundary manageEmployeeOutputBoundary;

    public ManageEmployeeInteractor(ManageEmployeeUserDataAccessInterface manageEmployeeUserDataAccessInterface,ManageEmployeeOutputBoundary manageEmployeeOutputBoundary) {
        this.userDataAccessInterface = manageEmployeeUserDataAccessInterface;
        this.manageEmployeeOutputBoundary = manageEmployeeOutputBoundary;
    }

    @Override
    public void changeStatus(ManageEmployeeInputData manageEmployeeInputData) {
        if("active".equals(manageEmployeeInputData.getStatus())) {
            userDataAccessInterface.inactivateUser(manageEmployeeInputData.getUserId());
        }
        else if("inactive".equals(manageEmployeeInputData.getStatus())) {
            userDataAccessInterface.activateUser(
                    manageEmployeeInputData.getUserId(),
                    manageEmployeeInputData.getPassword());
        }
        Employee updatedEmployee =
                (Employee) userDataAccessInterface.get(
                        manageEmployeeInputData.getUserId());
        final ManageEmployeeOutputData update =
                new ManageEmployeeOutputData(updatedEmployee);
        manageEmployeeOutputBoundary.updateManageEmployeeView(update);
    }

    @Override
    public void changePay(ManageEmployeeInputData manageEmployeeInputData) {
        try {
            userDataAccessInterface.setPayRate(
                    manageEmployeeInputData.getUserId(),
                    Double.parseDouble(manageEmployeeInputData.getNewPayRate())
            );
            Employee updatedEmployee =
                    (Employee) userDataAccessInterface.get(
                            manageEmployeeInputData.getUserId());
            final ManageEmployeeOutputData update =
                    new ManageEmployeeOutputData(updatedEmployee);
            manageEmployeeOutputBoundary.updateManageEmployeeView(update);
        }
        catch (NumberFormatException | NullPointerException e) {
            manageEmployeeOutputBoundary.prepareFailView();
        }
    }

    @Override
    public void switchToEmployeeListView() {
        manageEmployeeOutputBoundary.switchToEmployeeListView(userDataAccessInterface.getEmployees());
    }
}
