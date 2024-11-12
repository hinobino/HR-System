package use_case.login;

import entity.User;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

//    private final String userID;
    // Passing in User object instead of the userID so that when we send this output data elsewhere,
    // we are able to see the type of the object, so we can see the role.
    private final User user;
    private final boolean useCaseFailed;

    public LoginOutputData(User user, boolean useCaseFailed) {
//        this.username = userID;
        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }

    public User getUser() { return user; }

    public String getUserID() {
        return user.getUserID();
    }

}
