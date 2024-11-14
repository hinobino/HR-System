package interface_adapter.welcome;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Welcome View.
 */
public class WelcomeViewModel extends ViewModel<WelcomeState> {

    public static final String TITLE_LABEL = "Welcome";

    public static final String LOGIN_BUTTON_LABEL = "Log In";
    public static final String MANAGER_SIGNUP_BUTTON_LABEL = "Sign Up As Manager";
    public static final String ACTIVATE_EMPLOYEE_BUTTON_LABEL = "Activate Employee Account";

    public WelcomeViewModel() {
        super("welcome");
        setState(new WelcomeState());
    }

}
