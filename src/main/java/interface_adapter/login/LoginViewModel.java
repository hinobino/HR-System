package interface_adapter.login;

import interface_adapter.ViewModel;

import java.awt.*;

/**
 * The View Model for the Login View.
 */
public class LoginViewModel extends ViewModel<LoginState> {

    public static final String TITLE_LABEL = "Login";
    public static final String USERID_LABEL = "User ID";
    public static final String PASSWORD_LABEL = "Password";

    public static final String LOGIN_BUTTON_LABEL = "Log In";
    public static final String TO_WELCOME_BUTTON_LABEL = "Back to Welcome Page";

    public static final Color ERROR_LABEL_COLOR = new Color(230, 0, 0);

    public LoginViewModel() {
        super("log in");
        setState(new LoginState());
    }

}
