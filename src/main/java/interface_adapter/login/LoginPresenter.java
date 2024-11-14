package interface_adapter.login;

import entity.Employee;
import entity.Manager;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.EmployeeViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.ManagerViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ManagerViewModel managerViewModel;
    private final EmployeeViewModel employeeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final WelcomeViewModel welcomeViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          ManagerViewModel managerViewModel,
                          EmployeeViewModel employeeViewModel, LoginViewModel loginViewModel,
                          WelcomeViewModel welcomeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.managerViewModel = managerViewModel;
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
//        final LoggedInState loggedInState = managerViewModel.getState();
//        loggedInState.setUser(response.getUser());
//        this.managerViewModel.setState(loggedInState);
//        this.managerViewModel.firePropertyChanged();

        // NOTE: We are assuming here that the user will always be either an Employee or a Manager.

        // Switches to Employee (logged in) View.
        if (response.getUser() instanceof Employee) {
            final LoggedInState loggedInState = employeeViewModel.getState();
            loggedInState.setUser(response.getUser());

            this.employeeViewModel.setState(loggedInState);
            this.employeeViewModel.firePropertyChanged();

            this.viewManagerModel.setState(employeeViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }

        // Switches to Manager (logged in) View
        else if (response.getUser() instanceof Manager) {
            final LoggedInState loggedInState = managerViewModel.getState();
            loggedInState.setUser(response.getUser());

            this.managerViewModel.setState(loggedInState);
            this.managerViewModel.firePropertyChanged();

            this.viewManagerModel.setState(managerViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();
        }
//      // OLD CODE-- keeping in case of change in implementation
//        final LoggedInState loggedInState = managerViewModel.getState();
//        loggedInState.setUser(response.getUser());
//        this.managerViewModel.setState(loggedInState);
//        this.managerViewModel.firePropertyChanged();

        // This just changes which view is displayed
//        this.viewManagerModel.setState(managerViewModel.getViewName());
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
