package interface_adapter.logged_in;

import entity.User;
import view.ScheduleView;

/**
 * The State information representing the logged-in user.
 */
public class LoggedInState {
    private User user;
    private String userID = "";
    private String password = "";
    private String passwordError;
    private ScheduleView scheduleView = null;

    public LoggedInState(LoggedInState copy) {
        user = copy.user;
        userID = copy.userID;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    public LoggedInState() {}

    public User getUser() { return user; }

    public String getUserID() {
        return user.getUserID();
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public ScheduleView getScheduleView() { return scheduleView; }

    public void setUser(User user) { this.user = user; }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setScheduleView(ScheduleView scheduleView) { this.scheduleView = scheduleView; }
}
