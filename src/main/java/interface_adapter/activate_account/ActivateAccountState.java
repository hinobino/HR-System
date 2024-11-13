package interface_adapter.activate_account;

/**
 * The state class for ActivateAccountViewModel.
 */
public class ActivateAccountState {

    private String userID = "";
    private String userIDError;
    private String password = "";
    private String passwordError;
    private String repeatPassword = "";
    private String repeatPasswordError;

    public String getUserID() {
        return userID;
    }

    public String getUserIDError() {
        return userIDError;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    @Override
    public String toString() {
        return "SignupState{" +
                "userID='" + userID + "'" +
                ", password='" + password + "'" +
                ", repeatPassword='" + repeatPassword + "'" +
                "}";
    }

}
