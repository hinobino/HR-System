package interface_adapter.welcome;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.welcome.WelcomeOutputBoundary;

/**
 * The Presenter for the Welcome Use Case.
 */
public class WelcomePresenter implements WelcomeOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;

    public WelcomePresenter(ViewManagerModel viewManagerModel,
                            LoginViewModel loginViewModel,
                            SignupViewModel signupViewModel) {

        // To switch views, we use the setState method in the ViewManagerModel class for the
        // specific viewManagerModel object used in the AppBuilder class. So, we take it as an
        // input.
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    // These two methods override WelcomeInteractor which override WelcomeOutputBoundary and
    // are called on by WelcomeView.
    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSignupView() {
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
