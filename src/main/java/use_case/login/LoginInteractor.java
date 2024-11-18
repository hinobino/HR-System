package use_case.login;

import entity.User;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String userID = loginInputData.getUserID();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(userID)) {
            loginPresenter.prepareFailView(userID + ": Account does not exist.");
        }
        else if ("".equals(password)) {
            loginPresenter.prepareFailView("Please enter a valid password.");
        }
        else {
            final String pwd = userDataAccessObject.get(userID).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + userID + "\".");
            }
            else { // LOG IN SUCCESSFUL
                // Since we are preparing to switch views, reset LoginView for future use.
                loginInputData.getView().resetView();

                // The given user ID exists, so we can use it to search for the associated User.
                final User user = userDataAccessObject.get(loginInputData.getUserID());

                // Lets us know who is the current User logged in.
                userDataAccessObject.setCurrentUserID(user.getUserID());

                // As we prepare the success view, we assume that user is either an Employee or a
                // Manager, for those are the only two options of user types when we create either.
                final LoginOutputData loginOutputData = new LoginOutputData(user, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void switchToWelcomeView() { loginPresenter.switchToWelcomeView(); }
}
