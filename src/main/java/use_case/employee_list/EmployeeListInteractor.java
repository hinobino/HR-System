package use_case.employee_list;

import entity.Employee;

public class EmployeeListInteractor implements EmployeeListInputBoundary {
    private final EmployeeListOutputBoundary employeeListPresenter;

    public EmployeeListInteractor(EmployeeListUserDataAccessInterface userDataAccessObject,
                                  EmployeeListOutputBoundary employeeListPresenter) {
        this.employeeListPresenter = employeeListPresenter;

    }

    @Override
    public void selectEmployee(Employee employee) {
        EmployeeListOutputData outputData = new EmployeeListOutputData(employee);
        employeeListPresenter.selectEmployee(outputData);
    }

    @Override
    public void switchToManagerView() {
        employeeListPresenter.switchToManagerView();
    }
}
