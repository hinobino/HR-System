package use_case.signup;

import entity.*;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;

    // This user case only creates managers
    private final ManagerFactory managerFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary, ManagerFactory managerFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.managerFactory = managerFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUserID())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else if ("".equals(signupInputData.getUserID())) {
            userPresenter.prepareFailView("Please enter a valid User ID.");
        }
        else if ("".equals(signupInputData.getPassword())) {
            userPresenter.prepareFailView("Please enter a valid password.");
        }

        else { // SIGN UP SUCCESSFUL
            // Since we are switching views, reset the SignupView for future use.
            signupInputData.getView().resetView();

            // The given user ID exists, so we can use it to create and save a new User object.
            Manager manager = managerFactory.create(signupInputData.getUserID(), signupInputData.getPassword());
            userDataAccessObject.save(manager);

            final SignupOutputData signupOutputData = new SignupOutputData(manager.getUserID(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() { userPresenter.switchToLoginView(); }

    @Override
    public void switchToWelcomeView() { userPresenter.switchToWelcomeView(); }
}
