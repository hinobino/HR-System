package interface_adapter.manage_employee;

import interface_adapter.ViewModel;

public class ManageEmployeeViewModel extends ViewModel <ManageEmployeeState> {

    public static final String TITLE_LABEL = "Manage Employee";
    public static final String USERID_LABEL = "You are viewing the profile of ";
    public static final String STATUS_LABEL = "Status: ";
    public static final String PAY_LABEL = "Pay: ";
    public static final String HOURS_WORKED_LABEL = "Hours Worked: ";
    public static final String CHANGE_STATUS = "Change Status";
    public static final String CHANGE_PAY = "Change Pay";
    public static final String BACK_BUTTON = "Back";

    public ManageEmployeeViewModel() {
        super("manage employee");
        setState(new ManageEmployeeState());
    }
}
