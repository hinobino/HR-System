package interface_adapter.signup;

import interface_adapter.ViewModel;

/**
 * View Model for the Signup View.
 */
public class SignupViewModel extends ViewModel<SignupState> {

    public static final String TITLE_LABEL = "Signup View";
    public static final String USERID_LABEL = "Enter User ID";
    public static final String PASSWORD_LABEL = "Choose Password";

    public static final String SIGNUP_BUTTON_LABEL = "Sign Up";
    public static final String TO_WELCOME_BUTTON_LABEL = "Back to Welcome Page";

    public SignupViewModel() {
        // this creates a view model with "sign up" as the title
        super("sign up");

        // this makes sure the state uses the SignupState we created
        setState(new SignupState());
    }

}
