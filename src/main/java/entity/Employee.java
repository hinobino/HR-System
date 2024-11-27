package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The representation of an employee.
 */
public class Employee extends User {

    private String status = "inactive";
    private List<Shift> shifts;
    private int hoursWorked = 0;
    private double payRate = 17.20;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Constructor for an Employee object.
     * @param userID the id of the employee.
     * @param password the password of the employee.
     */
    public Employee(String userID, String password) {
        super(userID, password);
        shifts = new ArrayList<>();
    }

    /**
     * Return the current status of the Employee.
     * @return the status of the Employee.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Return shifts the Employee has.
     * @return a list of the employee's shifts.
     */
    public List<Shift> getShifts() {
        return shifts;
    }

    /**
     * Return the number of hours the employee worked.
     * @return the number of hours worked.
     */
    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Returns the employees pay rate.
     * @return the pay rate.
     */
    public double getPayRate() {
        return payRate;
    }

    /**
     * Return the date the employee started working.
     * @return the start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Return the date the employee stopped working.
     * @return the end date.
     */
    public LocalDate getEndDate() {
        return endDate;
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

    /**
     * Change the password of the employee.
     * @param password the new password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Add a given Shift to the employee's list.
     * @param shift the Shift to add.
     */
    public void addShift(Shift shift) {
        this.shifts.add(shift);
    }

    /**
     * Remove a given Shift from the employee.
     * @param shift the Shift to remove.
     */
    public void removeShift(Shift shift) {
        this.shifts.remove(shift);
    }

    /**
     * Update the number of hours worked.
     * @param hoursWorked the additional hours worked.
     */
    public void addHoursWorked(int hoursWorked) {
        this.hoursWorked += hoursWorked;
    }

    /**
     * Set the employee's pay rate.
     * @param payRate the new pay rate.
     */
    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    /**
     * Return the date the employee started working.
     * @param startDate the start date.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Return the date the employee started working.
     * @param endDate the start date.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
