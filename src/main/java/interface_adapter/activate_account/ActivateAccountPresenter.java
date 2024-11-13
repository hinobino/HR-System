package interface_adapter.activate_account;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.activate_account.ActivateAccountOutputBoundary;
import use_case.activate_account.ActivateAccountOutputData;

/**
 * The Presenter for the ActivateAccount use case.
 */
public class ActivateAccountPresenter implements ActivateAccountOutputBoundary {

    private final ActivateAccountViewModel activateAccountViewModel;
    private final LoginViewModel loginViewModel;
    private final WelcomeViewModel welcomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public ActivateAccountPresenter(ViewManagerModel viewManagerModel,
                           ActivateAccountViewModel activateAccountViewModel,
                           LoginViewModel loginViewModel, WelcomeViewModel welcomeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.activateAccountViewModel = activateAccountViewModel;
        this.loginViewModel = loginViewModel;
        this.welcomeViewModel = welcomeViewModel;
    }

    @Override
    public void prepareSuccessView(ActivateAccountOutputData response) {
        // On success, switch to the login view.
        final LoginState loginState = loginViewModel.getState();
        loginState.setUserID(response.getUserID());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final ActivateAccountState activateAccountState = activateAccountViewModel.getState();
        activateAccountState.setUserIDError(error);
        activateAccountViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
