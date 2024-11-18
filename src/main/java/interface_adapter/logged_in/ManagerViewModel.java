package interface_adapter.logged_in;

import interface_adapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */
public class ManagerViewModel extends ViewModel<LoggedInState> {

    public static final String TITLE_LABEL = "Manager View";
    public static final String WELCOME_LABEL = "Welcome, ";

    // TODO: figure out this schedule_label depending on how we wanna implement the display
    public static final String SCHEDULE_LABEL = "This week's schedule";
    public static final String SET_SHIFT_LABEL = "Schedule a shift";
    public static final String EMPLOYEES_LABEL = "Your employees";
    public static final String REQUESTS_LABEL = "Leave requests";
    public static final String CREATE_EMPLOYEE_LABEL = "Create Employee";

    public ManagerViewModel() {
        super("logged in");
        setState(new LoggedInState());
    }

}
