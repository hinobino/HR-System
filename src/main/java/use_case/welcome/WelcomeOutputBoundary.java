package use_case.welcome;

/**
 * The output boundary for the Welcome Use Case.
 */
public interface WelcomeOutputBoundary {

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();

    /**
     * Switches to the Sign-up View.
     */
    void switchToSignupView();

    /**
     * Switches ot the Activate Account View.
     */
    void switchToActivateAccountView();
}
