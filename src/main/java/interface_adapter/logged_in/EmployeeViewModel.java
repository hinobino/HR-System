package interface_adapter.logged_in;

import interface_adapter.ViewModel;

/**
 * The View Model for the Logged In Employee View.
 */
public class EmployeeViewModel extends ViewModel<LoggedInState> {

    public static final String TITLE_LABEL = "Employee View";
    public static final String WELCOME_LABEL = "Welcome, ";

    public static final String SCHEDULE_LABEL = "Your schedule";
    public static final String SHIFTS_LABEL = "Your shifts";
    public static final String REQUEST_LABEL = "Request leave";

    public EmployeeViewModel() {
        super("employee");
        setState(new LoggedInState());
    }

}
