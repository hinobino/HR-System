package data_access;

import entity.User;
import use_case.activate_account.ActivateAccountUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface, ActivateAccountUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUserID;

    @Override
    public boolean existsByName(String userID) {
        return users.containsKey(userID);
    }

    @Override
    public void save(User user) {
        users.put(user.getUserID(), user);
    }

    @Override
    public User get(String userID) {
        return users.get(userID);
    }

    @Override
    public void setCurrentUserID(String ID) {
        this.currentUserID = ID;
    }

    @Override
    public String getCurrentUserID() {
        return this.currentUserID;
    }
}
