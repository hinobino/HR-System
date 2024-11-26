package use_case.manage_employee;

import entity.User;

/**
 * DAO for the Activate Account Use Case.
 */
public interface ManageEmployeeUserDataAccessInterface {
    /**
     * Returns the user with the given userID.
     * @param userID the userID to look up
     * @return the user with the given userID
     */
    User get(String userID);

    void setPayRate(String userID, Double newPayRate);

    void activateUser(String userID, String password);

    /**
     * Inactivates the employee
     */
    void inactivateUser(String userID);
}
