package entity;

/**
 * The representation of a user.
 */
public abstract class User {
    private final String userid;
    private final String password;

    /**
     * Constructor for a basic user object.
     * @param userid the ID of the user.
     * @param password the password of the user.
     */
    protected User(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    /**
     * Returns the user's ID.
     * @return the ID of the user.
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Returns the user's password.
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }
}
