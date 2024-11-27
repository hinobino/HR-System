package interface_adapter.create_employee;

import interface_adapter.ViewModel;

public class CreateEmployeeViewModel extends ViewModel<CreateEmployeeState> {

    public final String TITLE_LABEL = "Create Employee";
    public final String NEW_USERID_LABEL = "New User ID";
    public final String CREATE_EMPLOYEE_BUTTON_LABEL = "Create Employee";
    public static final String BACK_BUTTON_LABEL = "Back";

    public CreateEmployeeViewModel() {
        super("create employee");
        setState(new CreateEmployeeState());
    }

}
