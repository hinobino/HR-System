package entity;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     * @param userid the id of the new user.
     * @param password the password of the new user.
     * @return the new user object.
     */
    User create(String userid, String password);
}
