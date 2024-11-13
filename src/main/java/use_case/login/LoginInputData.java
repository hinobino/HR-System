package use_case.login;

/**
 * The Input Data for the Login Use Case.
 */
public class LoginInputData {

    private final String userID;
    private final String password;

    public LoginInputData(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    String getUserID() {
        return userID;
    }

    String getPassword() {
        return password;
    }

}
