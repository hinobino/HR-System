package use_case.activate_account;

/**
 * Input Boundary for actions which are related to activating an employee account.
 */
public interface ActivateAccountInputBoundary {

    /**
     * Executes the signup use case.
     * @param activateAccountInputData the input data
     */
    void execute(ActivateAccountInputData activateAccountInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();

    /**
     * Executes the switch to welcome view use case.
     */
    void switchToWelcomeView();
}
