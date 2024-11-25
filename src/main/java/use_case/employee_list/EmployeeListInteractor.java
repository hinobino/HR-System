package use_case.employee_list;

public class EmployeeListInteractor implements EmployeeListInputBoundary {
    private final EmployeeListOutputBoundary employeeListPresenter;

    public EmployeeListInteractor(EmployeeListUserDataAccessInterface userDataAccessObject,
                                  EmployeeListOutputBoundary employeeListPresenter) {
        this.employeeListPresenter = employeeListPresenter;
    }

    @Override
    public void switchToManagerView() {
        employeeListPresenter.switchToManagerView();
    }
}
