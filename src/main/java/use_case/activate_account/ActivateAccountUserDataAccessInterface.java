package use_case.activate_account;

import entity.User;

/**
 * DAO for the Activate Account Use Case.
 */
public interface ActivateAccountUserDataAccessInterface {

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
}
