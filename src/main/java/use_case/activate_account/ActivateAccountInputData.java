package use_case.activate_account;

import view.ActivateAccountView;

/**
 * The Input Data for the Activate Account Use Case.
 */
public class ActivateAccountInputData {

    private final String userID;
    private final String password;
    private final String repeatPassword;
    private final ActivateAccountView view;

    public ActivateAccountInputData(String userID, String password, String repeatPassword, ActivateAccountView view) {
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

    public ActivateAccountView getView() { return view; }
}
