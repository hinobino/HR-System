package interface_adapter.manage_employee;

import interface_adapter.ViewModel;

public class ManageEmployeeViewModel extends ViewModel <ManageEmployeeState> {

    public static final String TITLE_LABEL = "Manage Employee";
    public static final String USERID_LABEL = "Manage ";
    public static final String PAY_LABEL = "Pay ";
    public static final String EMPLOYEE_LABEL = "Employee ";

    public ManageEmployeeViewModel() {
        super("manage employee");
        setState(new ManageEmployeeState());
    }
}
