package interface_adapter.welcome;

import use_case.welcome.WelcomeInputBoundary;

public class WelcomeController {

    private final WelcomeInputBoundary welcomeInputBoundary;

    public WelcomeController(WelcomeInputBoundary welcomeInputBoundary) {
        this.welcomeInputBoundary = welcomeInputBoundary;
    }

    public void switchToLoginView() {
        welcomeInputBoundary.switchToLoginView();
        System.out.println("Got to WelcomeController");
    }

    public void switchToSignupView() {
        welcomeInputBoundary.switchToSignupView();
    }
}
