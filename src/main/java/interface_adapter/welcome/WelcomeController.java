package interface_adapter.welcome;

import use_case.welcome.WelcomeInputBoundary;

public class WelcomeController {

    private final WelcomeInputBoundary welcomeInputBoundary;

    public WelcomeController(WelcomeInputBoundary welcomeInputBoundary) {
        this.welcomeInputBoundary = welcomeInputBoundary;
    }

    public void switchToLoginView() {
        welcomeInputBoundary.switchToLoginView();
    }

    public void switchToSignupView() {
        welcomeInputBoundary.switchToSignupView();
    }

    public void switchToActivateAccountView() {
        welcomeInputBoundary.switchToActivateAccountView();
    }
}
