package interface_adapter.logged_in;

import interface_adapter.ViewModel;

/**
 * The View Model for the Logged In Employee View.
 */
public class EmployeeViewModel extends ViewModel<LoggedInState> {

    public EmployeeViewModel() {
        super("employee");
        setState(new LoggedInState());
    }

}
