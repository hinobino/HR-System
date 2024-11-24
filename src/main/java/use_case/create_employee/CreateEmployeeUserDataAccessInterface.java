package use_case.create_employee;

import entity.User;

/**
 * DAO for the Create Employee Use Case.
 */
public interface CreateEmployeeUserDataAccessInterface {
    /**
     * Saves the user.
     * @param user the user to save
     */
    void save(User user);

    /**
     * Checks if the given userID exists.
     * @param userID the userID to look for
     * @return true if a user with the given userID exists; false otherwise
     */
    boolean existsByName(String userID);

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
}
