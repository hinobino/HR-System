package use_case.manage_employee;

public interface ManageEmployeeOutputBoundary {

    void updateManageEmployeeView(ManageEmployeeOutputData update);

    void switchToEmployeeListView();
}
