package entity;

/**
 * The representation of an employee.
 */
public class Employee extends User {

    private String status = "INACTIVE";

    /**
     * Constructor for an Employee object.
     * @param userid the id of the employee.
     * @param password the password of the employee.
     */
    public Employee(String userid, String password) {
        super(userid, password);
    }

    /**
     * Return the current status of the Employee.
     * @return the status of the Employee.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Change the status of the employee to ACTIVE or INACTIVE.
     * @param status the new status of the employee.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
