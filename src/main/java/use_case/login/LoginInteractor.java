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
            else {

                // TODO: This is where we grab the User object, which, based on our implementation,
                //  HAS to be either Employee or Manager object: 1. because why would we store
                //  a regular User lol and 2. because when we pass a User object in LoginOutputData,
                //  we need it to be either Employee or Manager so we can check its type and see
                //  what view we want to switch to.
                final User user = userDataAccessObject.get(loginInputData.getUserID());

                userDataAccessObject.setCurrentUserID(user.getUserID());
                // Like above, we should assume here that user is either an Employee or Manager obj
                final LoginOutputData loginOutputData = new LoginOutputData(user, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    @Override
    public void switchToWelcomeView() { loginPresenter.switchToWelcomeView(); }
}
