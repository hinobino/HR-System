package interface_adapter.create_employee;

/**
 * The state class for the create employee usecase.
 */
public class CreateEmployeeState {
    private String newUserID = "";
    private String newUserIDError;

    public String getNewUserID() {
        return newUserID;
    }

    public String getNewUserIDError() {
        return newUserIDError;
    }

    public void setNewUserID(String newUserID) {
        this.newUserID = newUserID;
    }

    public void setNewUserIDError(String newUserIDError) {
        this.newUserIDError = newUserIDError;
    }
}
