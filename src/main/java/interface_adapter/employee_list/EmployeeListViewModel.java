package interface_adapter.employee_list;

import interface_adapter.ViewModel;

public class EmployeeListViewModel extends ViewModel<EmployeeListState> {

    public static final String TITLE_LABEL = "Employee List";
    public static final String MANAGE_EMPLOYEE_LABEL = "Manage Employee";
    public static final String CREATE_EMPLOYEE_LABEL = "Create Employee";
    public static final String BACK_BUTTON_LABEL = "Back";
    public static final String[] COLUMN_NAMES = {"User Id", "Status", ""};

    public EmployeeListViewModel() {
        super("employee list");
        setState(new EmployeeListState());
    }
}
