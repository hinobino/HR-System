package entity;

/**
 * The representation of a user.
 */
public abstract class User {
    protected String userID;
    protected final String password;

    /**
     * Constructor for a basic user object.
     * @param userID the ID of the user.
     * @param password the password of the user.
     */
    protected User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    /**
     * Returns the user's ID.
     * @return the ID of the user.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Returns the user's password.
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }
}
