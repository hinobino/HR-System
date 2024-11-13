package use_case.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String userID;
    private final String password;
    private final String repeatPassword;

    public SignupInputData(String userID, String password, String repeatPassword) {
        this.userID = userID;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    String getUserID() {
        return userID;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
