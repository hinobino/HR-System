package interface_adapter.logged_in;

import entity.User;

/**
 * The State information representing the logged-in user.
 */
public class LoggedInState {
    private User user;
    // TODO: These next two attributes can probably just be accessed through the User object.
    //  We are just missing a setUserID and setPassword under the User class, which we would need
    //  if we want to use the User object to implement these same methods in this class.
    // This situation compares how we also added User object attibute to LoginOutputData,
    // but there we had to access it through the User object because that is all that is being
    // passed into the Class constructor...
    private String userID = "";
    private String password = "";
    private String passwordError;

    public LoggedInState(LoggedInState copy) {
        user = copy.user;
        userID = copy.userID;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    public LoggedInState() {}

    public String getUserID() {
        return user.getUserID();
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setUser(User user) { this.user = user; }

    // TODO: this only changes the class userID, not the user object's userID
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
