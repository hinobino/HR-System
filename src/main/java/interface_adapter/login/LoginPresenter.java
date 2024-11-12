package interface_adapter.login;

import entity.Employee;
import entity.Manager;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.EmployeeViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final EmployeeViewModel employeeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final WelcomeViewModel welcomeViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          EmployeeViewModel employeeViewModel, LoginViewModel loginViewModel,
                          WelcomeViewModel welcomeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.employeeViewModel = employeeViewModel;
        this.loginViewModel = loginViewModel;
        this.welcomeViewModel = welcomeViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the correct logged in view.

        // TODO: Currently still using the LoggedInState file, will need to make separate states
        //  for Manager and Employee views. Once we do this, these next 4 lines will probably have
        //  to be doubled and placed in their respective if statements
        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUser(response.getUser());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        // NOTE: We are assuming here that the user will always be either an Employee or a Manager.

        // Switches to Employee (logged in) View.
        if (response.getUser() instanceof Employee) {
            this.viewManagerModel.setState(employeeViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
        // Switches to Manager (logged in) View
        // TODO: still using LoggedInViewModel for Manager for now, need to make a ManagerViewModel
        //  (probably duplicate + change a new LoggedInViewModel file instead of changing existing
        //  one so we can keep the original while figuring stuff out...?
        else if (response.getUser() instanceof Manager) {
            this.viewManagerModel.setState(loggedInViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
//      // OLD CODE-- keeping in case of change in implementation
//        final LoggedInState loggedInState = loggedInViewModel.getState();
//        loggedInState.setUser(response.getUser());
//        this.loggedInViewModel.setState(loggedInState);
//        this.loggedInViewModel.firePropertyChanged();

        // This just changes which view is displayed
//        this.viewManagerModel.setState(loggedInViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
