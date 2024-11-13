package use_case.login;

import entity.User;

/**
 * DAO for the Login Use Case.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if the given userID exists.
     * @param userID the userID to look for
     * @return true if a user with the given userID exists; false otherwise
     */
    boolean existsByName(String userID);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Returns the user with the given userID.
     * @param userID the userID to look up
     * @return the user with the given userID
     */
    User get(String userID);

    /**
     * Returns the userID of the curren user of the application.
     * @return the userID of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUserID();

    /**
     * Sets the userID indicating who is the current user of the application.
     * @param userID the new current userID; null to indicate that no one is currently logged into the application.
     */
    void setCurrentUserID(String userID);

}
