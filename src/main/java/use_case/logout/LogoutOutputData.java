package use_case.logout;

/**
 * Output Data for the Logout Use Case.
 */
public class LogoutOutputData {

    private String userID;
    private boolean useCaseFailed;

    public LogoutOutputData(String userID, boolean useCaseFailed) {
        this.userID = userID;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUserID() {
        return userID;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
