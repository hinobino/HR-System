package interface_adapter.employee_list;

import interface_adapter.ViewModel;

public class EmployeeListViewModel extends ViewModel<EmployeeListState> {

    public static final String TITLE_LABEL = "Employee List";

    public EmployeeListViewModel() {
        super("employee list");
        setState(new EmployeeListState());
    }
}
