package use_case.login;

import view.LoginView;

/**
 * The Input Data for the Login Use Case.
 */
public class LoginInputData {

    private final String userID;
    private final String password;
    private final LoginView view;

    public LoginInputData(String userID, String password, LoginView view) {
        this.userID = userID;
        this.password = password;
        this.view = view;
    }

    String getUserID() {
        return userID;
    }

    String getPassword() {
        return password;
    }

    public LoginView getView() { return view; }
}
