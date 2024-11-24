package use_case.create_employee;

public interface CreateEmployeeOutputBoundary {

    /**
     * Prepares the success view for the Create Employee Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(CreateEmployeeOutputData outputData);

    /**
     * Prepares the failure view for the Create Employee Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Welcome View.
     */
    void switchToManagerView();

}
