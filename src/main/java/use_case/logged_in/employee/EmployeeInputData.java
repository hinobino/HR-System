package use_case.logged_in.employee;

public class EmployeeInputData {

    private final String userID;

    public EmployeeInputData(String userID) { this.userID = userID; }

    String getUserID() { return userID; }
}