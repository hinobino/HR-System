package use_case.create_employee;

/**
 * Input data for create employee use case.
 */
public class CreateEmployeeInputData {

    private final String newUserID;

    public CreateEmployeeInputData(String newUserID) {
        this.newUserID = newUserID;
    }

    public String getNewUserID() {
        return newUserID;
    }

}
