package use_case.logout;

import interface_adapter.logged_in.LoggedInState;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private LogoutUserDataAccessInterface userDataAccessObject;
    private LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessInterface,
                            LogoutOutputBoundary logoutOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        // * get the username out of the input data,
        final LoggedInState loggedInState = logoutInputData.getLoggedInState();
        // * set the username to null in the DAO
        userDataAccessObject.setCurrentUserID(null);
        // * instantiate the `LogoutOutputData`, which needs to contain the username.
        final LogoutOutputData logoutOutputData = new LogoutOutputData(loggedInState, false);
        // * tell the presenter to prepare a success view.
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }
}

