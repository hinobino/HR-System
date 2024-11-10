package main.java.interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class LoginViewModel extends ViewModel<LoginState> {

    public LoginViewModel() {
        super("login");
        setState(new LoginState());
    }

}
