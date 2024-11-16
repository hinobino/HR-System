package use_case.create_employee;

import use_case.create_employee.CreateEmployeeOutputData;

public interface CreateEmployeeOutputBoundary {

    /**
     * Prepares the success view for the Create Employee Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(use_case.create_employee.CreateEmployeeOutputData outputData);

    /**
     * Prepares the failure view for the Create Employee Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Welcome View.
     */
    void switchToWelcomeView();

}
