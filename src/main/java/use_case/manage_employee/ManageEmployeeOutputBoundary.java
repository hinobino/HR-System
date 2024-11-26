package use_case.manage_employee;

import entity.Employee;

import java.util.Map;

public interface ManageEmployeeOutputBoundary {

    void updateManageEmployeeView(ManageEmployeeOutputData update);

    void prepareFailView();

    void switchToEmployeeListView(Map<String, Employee> employees);
}
