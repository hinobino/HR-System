package app;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.activate_account.ActivateAccountController;
import interface_adapter.activate_account.ActivateAccountPresenter;
import interface_adapter.activate_account.ActivateAccountViewModel;
import interface_adapter.create_employee.CreateEmployeeController;
import interface_adapter.create_employee.CreateEmployeePresenter;
import interface_adapter.create_employee.CreateEmployeeViewModel;
import interface_adapter.employee_list.EmployeeListController;
import interface_adapter.employee_list.EmployeeListPresenter;
import interface_adapter.employee_list.EmployeeListViewModel;
import interface_adapter.logged_in.*;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.schedule.ScheduleViewModel;
import interface_adapter.schedule_shift.ScheduleShiftController;
import interface_adapter.schedule_shift.ScheduleShiftPresenter;
import interface_adapter.schedule_shift.ScheduleShiftViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeController;
import interface_adapter.welcome.WelcomePresenter;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.activate_account.ActivateAccountInputBoundary;
import use_case.activate_account.ActivateAccountInteractor;
import use_case.activate_account.ActivateAccountOutputBoundary;
import use_case.create_employee.CreateEmployeeOutputData;
import use_case.employee_list.EmployeeListInputBoundary;
import use_case.employee_list.EmployeeListInteractor;
import use_case.employee_list.EmployeeListOutputBoundary;
import use_case.logged_in.employee.EmployeeInputBoundary;
import use_case.logged_in.employee.EmployeeInteractor;
import use_case.logged_in.employee.EmployeeOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.schedule_shift.ScheduleShiftInputBoundary;
import use_case.schedule_shift.ScheduleShiftInteractor;
import use_case.schedule_shift.ScheduleShiftOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.welcome.WelcomeInputBoundary;
import use_case.welcome.WelcomeInteractor;
import use_case.welcome.WelcomeOutputBoundary;
import use_case.logged_in.manager.ManagerInputBoundary;
import use_case.logged_in.manager.ManagerInteractor;
import use_case.logged_in.manager.ManagerOutputBoundary;
import use_case.create_employee.CreateEmployeeInputBoundary;
import use_case.create_employee.CreateEmployeeInteractor;
import use_case.create_employee.CreateEmployeeOutputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    // NOTE: TEMPORARILY USING EmployeeFactory INSTEAD OF CommonUserFactory (LIKE IN LAB 5),
    // NEED TO IMPLEMENT DIFFERENT FACTORIES LATER
    private final EmployeeFactory employeeFactory = new EmployeeFactory();
    private final ManagerFactory managerFactory = new ManagerFactory();
    private final ShiftFactory shiftFactory = new ShiftFactory();
    private final WorkdayFactory workdayFactory = new WorkdayFactory();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

    private WelcomeView welcomeView;
    private WelcomeViewModel welcomeViewModel;
    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private ManagerViewModel managerViewModel;
    private EmployeeViewModel employeeViewModel;
    private ManagerView managerView;
    private EmployeeView employeeView;
    private LoginView loginView;
    private ActivateAccountViewModel activateAccountViewModel;
    private ActivateAccountView activateAccountView;
    private CreateEmployeeViewModel createEmployeeViewModel;
    private CreateEmployeeView createEmployeeView;
    private EmployeeListViewModel employeeListViewModel;
    private EmployeeListView employeeListView;
    private ScheduleShiftView scheduleShiftView;
    private ScheduleShiftViewModel scheduleShiftViewModel;
    private ScheduleView scheduleView;
    private ScheduleViewModel scheduleViewModel;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Welcome View to the application.
     * @return this builder
     */
    public AppBuilder addWelcomeView() {
        welcomeViewModel = new WelcomeViewModel();
        welcomeView = new WelcomeView(welcomeViewModel);
        cardPanel.add(welcomeView, welcomeView.getViewName());
        return this;
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Manager View to the application.
     * @return this builder
     */
    public AppBuilder addManagerView() {
        managerViewModel = new ManagerViewModel();
        managerView = new ManagerView(managerViewModel);
        cardPanel.add(managerView, managerView.getViewName());
        return this;
    }

    public AppBuilder addEmployeeView() {
        employeeViewModel = new EmployeeViewModel();
        employeeView = new EmployeeView(employeeViewModel);
        cardPanel.add(employeeView, employeeView.getViewName());
        return this;
    }

    public AppBuilder addActivateAccountView() {
        activateAccountViewModel = new ActivateAccountViewModel();
        activateAccountView = new ActivateAccountView(activateAccountViewModel);
        cardPanel.add(activateAccountView, activateAccountView.getViewName());
        return this;
    }

    public AppBuilder addCreateEmployeeView() {
        createEmployeeViewModel = new CreateEmployeeViewModel();
        createEmployeeView = new CreateEmployeeView(createEmployeeViewModel);
        cardPanel.add(createEmployeeView, createEmployeeView.getViewName());
        return this;
    }

    public AppBuilder addEmployeeListView() {
        employeeListViewModel = new EmployeeListViewModel();
        employeeListView = new EmployeeListView(employeeListViewModel);
        cardPanel.add(employeeListView, employeeListView.getViewName());
        return this;
    }

    public AppBuilder addScheduleShiftView() {
        scheduleShiftViewModel = new ScheduleShiftViewModel();
        scheduleShiftView = new ScheduleShiftView(scheduleShiftViewModel);
        cardPanel.add(scheduleShiftView, scheduleShiftView.getViewName());
        return this;
    }

    public AppBuilder addScheduleView() {
        scheduleViewModel = new ScheduleViewModel();
        scheduleView = new ScheduleView(scheduleViewModel);
        return this;
    }

    public AppBuilder addWelcomeUseCase() {
        final WelcomeOutputBoundary welcomeOutputBoundary = new WelcomePresenter(signupViewModel,
                activateAccountViewModel, loginViewModel, viewManagerModel);
        final WelcomeInputBoundary welcomeInteractor = new WelcomeInteractor(
                welcomeOutputBoundary);

        final WelcomeController welcomeController = new WelcomeController(welcomeInteractor);
        welcomeView.setWelcomeController(welcomeController);
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel, welcomeViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, managerFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                managerViewModel, employeeViewModel, loginViewModel, welcomeViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Activate Account Use Case to the application.
     * @return this builder.
     */
    public AppBuilder addActivateAccountUseCase() {
        final ActivateAccountOutputBoundary activateAccountOutputBoundary = new ActivateAccountPresenter(
                viewManagerModel, activateAccountViewModel, loginViewModel, welcomeViewModel);
        final ActivateAccountInputBoundary activateAccountInteractor = new ActivateAccountInteractor(
                userDataAccessObject, activateAccountOutputBoundary, employeeFactory);
        final ActivateAccountController controller = new ActivateAccountController(activateAccountInteractor);
        activateAccountView.setActivateAccountController(controller);
        return this;
    }

    public AppBuilder addEmployeeUseCase() {
        final EmployeeOutputBoundary employeeOutputBoundary = new EmployeePresenter(viewManagerModel,scheduleViewModel);
        final EmployeeInputBoundary employeeInteractor = new EmployeeInteractor(userDataAccessObject, employeeOutputBoundary);
        final EmployeeController controller = new EmployeeController(employeeInteractor);
        employeeView.setEmployeeController(controller);
        return this;
    }

    public AppBuilder addManagerUseCase() {
        final ManagerOutputBoundary managerOutputBoundary = new ManagerPresenter(createEmployeeViewModel,
                employeeListViewModel, scheduleShiftViewModel, viewManagerModel);
        final ManagerInputBoundary managerInteractor = new ManagerInteractor(userDataAccessObject,
                managerOutputBoundary);
        final ManagerController controller = new ManagerController(managerInteractor);
        managerView.setManagerController(controller);
        return this;
    }

    public AppBuilder addCreateEmployeeUseCase() {
        final CreateEmployeeOutputBoundary createEmployeeOutputBoundary = new CreateEmployeePresenter(
                viewManagerModel,
                createEmployeeViewModel,
                managerViewModel
        );

        final CreateEmployeeInputBoundary createEmployeeInteractor = new CreateEmployeeInteractor(
                userDataAccessObject,
                createEmployeeOutputBoundary,
                employeeFactory
        );

        final CreateEmployeeController controller = new CreateEmployeeController(createEmployeeInteractor);
        createEmployeeView.setCreateEmployeeController(controller);
        return this;
    }

    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                managerViewModel, employeeViewModel, welcomeViewModel, userDataAccessObject);
        final LogoutInputBoundary logoutInteractor = new LogoutInteractor(userDataAccessObject,
                logoutOutputBoundary);
        final LogoutController logoutController = new LogoutController(logoutInteractor);
        managerView.setLogoutController(logoutController);
        employeeView.setLogoutController(logoutController);
        return this;
    }

    public AppBuilder addEmployeeListUseCase() {
        final EmployeeListOutputBoundary employeeListOutputBoundary = new EmployeeListPresenter(employeeListViewModel, managerViewModel, viewManagerModel);
        final EmployeeListInputBoundary employeeListInteractor = new EmployeeListInteractor(
                userDataAccessObject,
                employeeListOutputBoundary
        );

        final EmployeeListController controller = new EmployeeListController(employeeListInteractor);
        employeeListView.setEmployeeListController(controller);
        return this;
    }

    public AppBuilder addScheduleShiftUseCase() {
        final ScheduleShiftOutputBoundary scheduleShiftOutputBoundary = new ScheduleShiftPresenter(
                viewManagerModel,
                scheduleShiftViewModel,
                managerViewModel
        );
        final ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                userDataAccessObject,
                scheduleShiftOutputBoundary,
                shiftFactory,
                workdayFactory
        );
        final ScheduleShiftController controller = new ScheduleShiftController(scheduleShiftInteractor);
        scheduleShiftView.setScheduleShiftController(controller);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("HR System");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(welcomeView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
