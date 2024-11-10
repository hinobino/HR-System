package main.java.interface_adapter.login;

/**
 * The state for the Login View Model.
 */
public class LoginState {
    private String userID = "";
    private String loginError;
    private String password = "";

    public String getUserID() {
        return userID;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
