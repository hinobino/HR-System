package entity;

/**
 * Factory for creating managers.
 */
public class ManagerFactory implements UserFactory {

    @Override
    public User create(String userID, String password) {
        return new Manager(userID, password);
    }
}
