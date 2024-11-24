package use_case.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {
    private final String userID;

    public LogoutInputData(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return userID;
    }

}
