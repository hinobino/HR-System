package use_case.activate_account;

/**
 * Output Data for the Activate Account Use Case.
 */
public class ActivateAccountOutputData {

    private final String userID;

    private final boolean useCaseFailed;

    public ActivateAccountOutputData(String userID, boolean useCaseFailed) {
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
