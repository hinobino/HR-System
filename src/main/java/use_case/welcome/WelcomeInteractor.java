package use_case.welcome;

/**
 * The Welcome Interactor.
 */
public class WelcomeInteractor implements WelcomeInputBoundary {

    private final WelcomeOutputBoundary welcomeOutputBoundary;

    public WelcomeInteractor(WelcomeOutputBoundary welcomeOutputBoundary) {
        this.welcomeOutputBoundary = welcomeOutputBoundary;
    }

    @Override
    public void switchToLoginView() {

        welcomeOutputBoundary.switchToLoginView();

        System.out.println("Got to WelcomeInteractor");
    }

    @Override
    public void switchToSignupView() {
        welcomeOutputBoundary.switchToSignupView();
    }
}
