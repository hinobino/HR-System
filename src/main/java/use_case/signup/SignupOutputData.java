package use_case.signup;

/**
 * Output Data for the Signup Use Case.
 */
public class SignupOutputData {

    private final String userID;

    private final boolean useCaseFailed;

    public SignupOutputData(String userID, boolean useCaseFailed) {
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
