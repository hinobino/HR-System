package entity;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     * @param userID the ID of the new user.
     * @param password the password of the new user.
     * @return the new user object.
     */
    User create(String userID, String password);
}
