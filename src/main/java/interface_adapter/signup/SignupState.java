package interface_adapter.signup;

/**
 * The state class for the SignupViewModel
 */
public class SignupState {

    private String userID = "";
    private String userIDError;
    private String password = "";
    private String passwordError;

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

    @Override
    public String toString() {
        return "SignupState{" +
                "userID='" + userID + "'" +
                ", password='" + password + "'" +
                "}";
    }

}
