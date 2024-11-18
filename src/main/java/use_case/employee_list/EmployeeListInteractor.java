package use_case.employee_list;

import entity.Employee;
import entity.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class EmployeeListInteractor implements EmployeeListInputBoundary {
    private final EmployeeListUserDataAccessInterface userDataAccessObject;
    private final EmployeeListOutputBoundary employeeListPresenter;
    private Map<String, Employee> employees;

    public EmployeeListInteractor(EmployeeListUserDataAccessInterface userDataAccessObject,
                                  EmployeeListOutputBoundary employeeListPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.employeeListPresenter = employeeListPresenter;
    }

    public void createEmployeeList() {
        this.employees = userDataAccessObject.getEmployees();
        String[][] employeeList = new String[employees.size()][];
        int i = 0;
        for (Map.Entry<String, Employee> userObj : employees.entrySet()) {
            ArrayList<String> row = new ArrayList<>();
            row.add(userObj.getKey());
            row.add(userObj.getValue().getStatus());
            employeeList[i] = row.toArray(new String[row.size()]);
        }
        employeeListPresenter.createEmployeeList(employeeList);
    }
}
