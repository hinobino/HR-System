package use_case.logout;

import entity.User;

/**
 * DAO for the Logout Use Case.
 */
public interface LogoutUserDataAccessInterface {

    /**
     * Returns the username of the curren user of the application.
     * @return the username of the current user
     */
    String getCurrentUserID();

    /**
     * Returns the employee with the given userID.
     * @param userID the userID
     * @return the Employee with that userID
     */
    User get(String userID);

    /**
     * Sets the username indicating who is the current user of the application.
     * @param username the new current username
     */
    void setCurrentUserID(String username);
}
