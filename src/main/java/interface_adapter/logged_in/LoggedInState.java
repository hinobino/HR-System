package main.java.interface_adapter.logged_in;

/**
 * The State information representing the logged-in user.
 */
public class LoggedInState {
    private String userID = "";
    private String password = "";
    private String passwordError;

    public LoggedInState(LoggedInState copy) {
        userID = copy.userID;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    public LoggedInState() {}

    public String getUserID() {
        return userID;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

}
