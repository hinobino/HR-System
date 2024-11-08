package entity;

/**
 * The representation of an employee.
 */
public class Employee extends User {

    private String status = "INACTIVE";

    /**
     * Constructor for an Employee object.
     * @param userID the id of the employee.
     * @param password the password of the employee.
     */
    public Employee(String userID, String password) {
        super(userID, password);
    }

    /**
     * Return the current status of the Employee.
     * @return the status of the Employee.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Return the ID of the user.
     * @return the ID of the user.
     */
    @Override
    public String getUserID() {
        return userID;
    }

    /**
     * Return the password of the user.
     * @return the password of the user.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Change the status of the employee to ACTIVE or INACTIVE.
     * @param status the new status of the employee.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Change the userID of the employee.
     * @param userID the new ID of the user.
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
}
