package use_case.activate_account;

/**
 * The Input Data for the Activate Account Use Case.
 */
public class ActivateAccountInputData {

    private final String userID;
    private final String password;
    private final String repeatPassword;

    public ActivateAccountInputData(String userID, String password, String repeatPassword) {
        this.userID = userID;
        this.password = password;
        this.repeatPassword = repeatPassword;
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
}
