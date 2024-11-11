package interface_adapter.welcome;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Welcome View.
 */
public class WelcomeViewModel extends ViewModel<WelcomeState> {

    public static final String TITLE_LABEL = "Welcome";

    public static final String LOGIN_BUTTON_LABEL = "Login";
    public static final String SIGNUP_BUTTON_LABEL = "Sign Up";

    public WelcomeViewModel() {
        super("welcome");
        setState(new WelcomeState());
    }

}
