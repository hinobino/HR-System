package use_case.signup;

import view.SignupView;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String userID;
    private final String password;
    private final String repeatPassword;
    private final SignupView view;

    public SignupInputData(String userID, String password, String repeatPassword, SignupView view) {
        this.userID = userID;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.view = view;
    }

    String getUserID() {
        return userID;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public SignupView getView() { return view; }
}
