package use_case.create_employee;

/**
 * Output data for the Create Employee Use case.
 */
public class CreateEmployeeOutputData {

    private final String newUserID;

    private final boolean useCaseFailed;

    public CreateEmployeeOutputData(String userID, boolean useCaseFailed) {
        this.newUserID = userID;
        this.useCaseFailed = useCaseFailed;
    }

    public String getNewUserID() {
        return newUserID;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
