package interface_adapter.welcome;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Welcome View.
 */
public class WelcomeViewModel extends ViewModel<WelcomeState> {

    public static final String TITLE_LABEL = "Welcome";

    public static final String EMPLOYEE_LOGIN_BUTTON_LABEL = "Log In as Employee";
    public static final String EMPLOYEE_SIGNUP_BUTTON_LABEL = "Sign Up as Employee";
    public static final String MANAGER_LOGIN_BUTTON_LABEL = "Log In as Manager";
    public static final String MANAGER_SIGNUP_BUTTON_LABEL = "Sign Up as Manager";

    public WelcomeViewModel() {
        super("welcome");
        setState(new WelcomeState());
    }

}
