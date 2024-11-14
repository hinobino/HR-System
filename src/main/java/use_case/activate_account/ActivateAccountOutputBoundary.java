package use_case.activate_account;

/**
 * The output boundary for the Activate Account Use Case.
 */
public interface ActivateAccountOutputBoundary {

    /**
     * Prepares the success view for the Activate Account Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ActivateAccountOutputData outputData);

    /**
     * Prepares the failure view for the Activate Account Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();

    /**
     * Switches to the Welcome View.
     */
    void switchToWelcomeView();
}
