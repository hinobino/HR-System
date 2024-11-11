package use_case.welcome;

/**
 * The input boundary for the Welcome Use Case.
 */
public interface WelcomeInputBoundary {

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();

    /**
     * Switches to the Signup View.
     */
    void switchToSignupView();
}
