package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;
import view.SignupView;

/**
 * Controller for the signup usecase.
 */
public class SignupController {

    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup use case.
     * @param userID the userID to sign up.
     * @param password1 the password to use.
     * @param password2 the password, repeated.
     * @param view the view to reset.
     */
    public void execute(String userID, String password1, String password2, SignupView view) {
        final SignupInputData signupInputData = new SignupInputData(
                userID, password1, password2, view);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userSignupUseCaseInteractor.switchToLoginView();
    }

    public void switchToWelcomeView() { userSignupUseCaseInteractor.switchToWelcomeView(); }
}
