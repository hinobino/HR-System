package interface_adapter.logout;

import data_access.InMemoryUserDataAccessObject;
import entity.Employee;
import entity.Manager;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.EmployeeViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.ManagerViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.welcome.WelcomeState;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private ManagerViewModel managerViewModel;
    private EmployeeViewModel employeeViewModel;
    private WelcomeViewModel welcomeViewModel;
    private ViewManagerModel viewManagerModel;
    private InMemoryUserDataAccessObject userDataAccessObject;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                          ManagerViewModel managerViewModel,
                           EmployeeViewModel employeeViewModel,
                           WelcomeViewModel welcomeViewModel,
                           InMemoryUserDataAccessObject userDataAccessObject) {
        this.viewManagerModel = viewManagerModel;
        this.managerViewModel = managerViewModel;
        this.employeeViewModel = employeeViewModel;
        this.welcomeViewModel = welcomeViewModel;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        // We need to switch to the login view, which should have
        // an empty username and password.

        // We also need to set the username in the LoggedInState to
        // the empty string.

        // Close running schedule view
        if (response.getLoggedInState().getScheduleView() != null) {
            response.getLoggedInState().getScheduleView().dispose();
        }

        // have prepareSuccessView update the LoggedInState
        // 1. get the LoggedInState out of the appropriate View Model,
        LoggedInState loggedInState = new LoggedInState();
        if (userDataAccessObject.get(response.getUserID()) instanceof Manager) {
            managerViewModel.setState(loggedInState);
            managerViewModel.firePropertyChanged("log out");
        }
        else if (userDataAccessObject.get(response.getUserID()) instanceof Employee) {
            employeeViewModel.setState(loggedInState);
            employeeViewModel.firePropertyChanged("log out");
        }
//
//        // 2. set the username in the state to the empty string
//        loggedInState.setUserID("");
//
//        // 3. set the state in the LoggedInViewModel to the updated state
//        this.loggedInViewModel.setState(loggedInState);
//
//        // 4. firePropertyChanged so that the View that is listening is updated.
//        loggedInViewModel.firePropertyChanged();
//
//        // 5. get the LoginState out of the appropriate View Model,
//        final LoginState loginState = loginViewModel.getState();
//
//        // 6. set the username and password in the state to the empty string
//        loginState.setUsername("");
//        loginState.setPassword("");
//
//        // 7. set the state in the LoginViewModel to the updated state
//        this.loginViewModel.setState(loginState);
//
//        // 8. firePropertyChanged so that the View that is listening is updated.
//        loginViewModel.firePropertyChanged();
//
//        // This code tells the View Manager to switch to the LoginView.
//        this.viewManagerModel.setState(loginViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();

//        final WelcomeState welcomeState = welcomeViewModel.getState();
        this.viewManagerModel.setState(welcomeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }
}
