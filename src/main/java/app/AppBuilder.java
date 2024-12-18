package app;

import data_access.InMemoryUserDataAccessObject;
import data_access.DBUserDataAccessObject;
import data_access.TimeOffRequestDataAccessObject;
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
import interface_adapter.export_calendar.ExportCalendarController;
import interface_adapter.export_calendar.ExportCalendarPresenter;
import interface_adapter.export_calendar.ExportCalendarViewModel;
import interface_adapter.logged_in.EmployeeController;
import interface_adapter.logged_in.EmployeePresenter;
import interface_adapter.logged_in.EmployeeViewModel;
import interface_adapter.logged_in.ManagerController;
import interface_adapter.logged_in.ManagerPresenter;
import interface_adapter.logged_in.ManagerViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.manage_shifts.ManageShiftsController;
import interface_adapter.manage_shifts.ManageShiftsPresenter;
import interface_adapter.manage_shifts.ManageShiftsViewModel;
import interface_adapter.view_schedule.ScheduleController;
import interface_adapter.view_schedule.SchedulePresenter;
import interface_adapter.view_schedule.ScheduleViewModel;
import interface_adapter.manage_employee.ManageEmployeeController;
import interface_adapter.manage_employee.ManageEmployeePresenter;
import interface_adapter.manage_employee.ManageEmployeeViewModel;
import interface_adapter.schedule_shift.ScheduleShiftController;
import interface_adapter.schedule_shift.ScheduleShiftPresenter;
import interface_adapter.schedule_shift.ScheduleShiftViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeController;
import interface_adapter.welcome.WelcomePresenter;
import interface_adapter.welcome.WelcomeViewModel;
import interface_adapter.time_off.TimeOffRequestController;
import interface_adapter.time_off.TimeOffRequestPresenter;
import interface_adapter.time_off.TimeOffRequestViewModel;
import use_case.activate_account.ActivateAccountInputBoundary;
import use_case.activate_account.ActivateAccountInteractor;
import use_case.activate_account.ActivateAccountOutputBoundary;
import use_case.employee_list.EmployeeListInputBoundary;
import use_case.employee_list.EmployeeListInteractor;
import use_case.employee_list.EmployeeListOutputBoundary;
import use_case.export_calendar.ExportCalendarInputBoundary;
import use_case.export_calendar.ExportCalendarInteractor;
import use_case.export_calendar.ExportCalendarOutputBoundary;
import use_case.logged_in.employee.EmployeeInputBoundary;
import use_case.logged_in.employee.EmployeeInteractor;
import use_case.logged_in.employee.EmployeeOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.manage_employee.ManageEmployeeInputBoundary;
import use_case.manage_employee.ManageEmployeeInteractor;
import use_case.manage_employee.ManageEmployeeOutputBoundary;
import use_case.manage_shifts.ManageShiftsInputBoundary;
import use_case.manage_shifts.ManageShiftsInteractor;
import use_case.manage_shifts.ManageShiftsOutputBoundary;
import use_case.schedule_shift.ScheduleShiftInputBoundary;
import use_case.schedule_shift.ScheduleShiftInteractor;
import use_case.schedule_shift.ScheduleShiftOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.view_schedule.ScheduleInputBoundary;
import use_case.view_schedule.ScheduleInteractor;
import use_case.view_schedule.ScheduleOutputBoundary;
import use_case.welcome.WelcomeInputBoundary;
import use_case.welcome.WelcomeInteractor;
import use_case.welcome.WelcomeOutputBoundary;
import use_case.logged_in.manager.ManagerInputBoundary;
import use_case.logged_in.manager.ManagerInteractor;
import use_case.logged_in.manager.ManagerOutputBoundary;
import use_case.create_employee.CreateEmployeeInputBoundary;
import use_case.create_employee.CreateEmployeeInteractor;
import use_case.create_employee.CreateEmployeeOutputBoundary;
import use_case.time_off_request.TimeOffRequestInputBoundary;
import use_case.time_off_request.TimeOffRequestInteractor;
import use_case.time_off_request.TimeOffRequestOutputBoundary;
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

    private static final int SCALE_FACTOR = 50;
    private static final int ASPECT_RATIO_X = 16;
    private static final int ASPECT_RATIO_Y = 9;
    private static final int WINDOW_WIDTH = SCALE_FACTOR * ASPECT_RATIO_X;
    private static final int WINDOW_HEIGHT = SCALE_FACTOR * ASPECT_RATIO_Y;

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final EmployeeFactory employeeFactory = new EmployeeFactory();
    private final ManagerFactory managerFactory = new ManagerFactory();
    private final ShiftFactory shiftFactory = new ShiftFactory();
    private final WorkdayFactory workdayFactory = new WorkdayFactory();
    private final WorkWeekFactory workWeekFactory = new WorkWeekFactory();

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
    private ManageEmployeeViewModel manageEmployeeViewModel;
    private ManageEmployeeView manageEmployeeView;
    private ScheduleShiftView scheduleShiftView;
    private ScheduleShiftViewModel scheduleShiftViewModel;
    private ScheduleView scheduleView;
    private ScheduleViewModel scheduleViewModel;
    private ManageShiftsView manageShiftsView;
    private ManageShiftsViewModel manageShiftsViewModel;
    private TimeOffRequestView timeOffRequestView;
    private TimeOffRequestViewModel timeOffRequestViewModel;
    private ExportCalendarViewModel exportCalendarViewModel;

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

    /**
     * Adds the Employee View to the application.
     * @return this builder
     */
    public AppBuilder addEmployeeView() {
        employeeViewModel = new EmployeeViewModel();
        employeeView = new EmployeeView(employeeViewModel);
        cardPanel.add(employeeView, employeeView.getViewName());
        return this;
    }

    /**
     * Adds the Activate Account View to the application.
     * @return this builder
     */
    public AppBuilder addActivateAccountView() {
        activateAccountViewModel = new ActivateAccountViewModel();
        activateAccountView = new ActivateAccountView(activateAccountViewModel);
        cardPanel.add(activateAccountView, activateAccountView.getViewName());
        return this;
    }

    /**
     * Adds the Create Employee View to the application.
     * @return this builder
     */
    public AppBuilder addCreateEmployeeView() {
        createEmployeeViewModel = new CreateEmployeeViewModel();
        createEmployeeView = new CreateEmployeeView(createEmployeeViewModel);
        cardPanel.add(createEmployeeView, createEmployeeView.getViewName());
        return this;
    }

    /**
     * Adds the Employee List View to the application.
     * @return this builder
     */
    public AppBuilder addEmployeeListView() {
        employeeListViewModel = new EmployeeListViewModel();
        employeeListView = new EmployeeListView(employeeListViewModel);
        cardPanel.add(employeeListView, employeeListView.getViewName());
        return this;
    }

    /**
     * Adds the Manage Employee View to the application.
     * @return this builder
     */
    public AppBuilder addManageEmployeeView() {
        manageEmployeeViewModel = new ManageEmployeeViewModel();
        manageEmployeeView = new ManageEmployeeView(manageEmployeeViewModel);
        cardPanel.add(manageEmployeeView, manageEmployeeView.getViewName());
        return this;
    }

    /**
     * Adds the Schedule Shift View to the application.
     * @return this builder
     */
    public AppBuilder addScheduleShiftView() {
        scheduleShiftViewModel = new ScheduleShiftViewModel();
        scheduleShiftView = new ScheduleShiftView(scheduleShiftViewModel);
        cardPanel.add(scheduleShiftView, scheduleShiftView.getViewName());
        return this;
    }
    
    /**
     * Adds the Manage Shifts View to the application.
     * @return this builder
     */
    public AppBuilder addManageShiftsView() {
        manageShiftsViewModel = new ManageShiftsViewModel();
        manageShiftsView = new ManageShiftsView(manageShiftsViewModel);
        cardPanel.add(manageShiftsView, manageShiftsView.getViewName());
        return this;
    }

    /**
     * Adds the Schedule View to the application.
     * @return this builder
     */
    public AppBuilder addScheduleView() {
        scheduleViewModel = new ScheduleViewModel();
        scheduleView = new ScheduleView(scheduleViewModel, exportCalendarViewModel);
        // NOT ADDED TO CARD PANEL BC IT IS A NEW JFRAME
        return this;
    }
    /**
     * Adds the Time Off Request View to the application.
     * @return this builder
     */
    //implement

    /**
     * Adds the Welcome Use Case to the application.
     * @return this builder
     */
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

    /**
     * Adds the Employee Use Case to the application.
     * @return this builder
     */
    public AppBuilder addEmployeeUseCase() {
        final EmployeeOutputBoundary employeeOutputBoundary = new EmployeePresenter(
                viewManagerModel,
                scheduleViewModel);
        final EmployeeInputBoundary employeeInteractor = new EmployeeInteractor(
                userDataAccessObject,
                employeeOutputBoundary,
                workWeekFactory);
        final EmployeeController controller = new EmployeeController(employeeInteractor);
        employeeView.setEmployeeController(controller);
        return this;
    }

    /**
     * Adds the Manager Use Case to the application.
     * @return this builder
     */
    public AppBuilder addManagerUseCase() {
        final ManagerOutputBoundary managerOutputBoundary = new ManagerPresenter(
                createEmployeeViewModel,
                employeeListViewModel,
                scheduleShiftViewModel,
                scheduleViewModel,
                manageShiftsViewModel,
                viewManagerModel);
        final ManagerInputBoundary managerInteractor = new ManagerInteractor(
                userDataAccessObject,
                managerOutputBoundary,
                workWeekFactory);
        final ManagerController controller = new ManagerController(managerInteractor);
        managerView.setManagerController(controller);
        return this;
    }

    /**
     * Adds the Create Employee Use Case to the application.
     * @return this builder
     */
    public AppBuilder addCreateEmployeeUseCase() {
        final CreateEmployeeOutputBoundary createEmployeeOutputBoundary = new CreateEmployeePresenter(
                viewManagerModel,
                createEmployeeViewModel,
                managerViewModel,
                employeeListViewModel
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

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
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

    /**
     * Adds the Employee List Use Case to the application.
     * @return this builder
     */
    public AppBuilder addEmployeeListUseCase() {
        final EmployeeListOutputBoundary employeeListOutputBoundary = new EmployeeListPresenter(
                manageEmployeeViewModel, createEmployeeViewModel, managerViewModel, viewManagerModel);
        final EmployeeListInputBoundary employeeListInteractor = new EmployeeListInteractor(employeeListOutputBoundary);
        final EmployeeListController controller = new EmployeeListController(employeeListInteractor);
        employeeListView.setEmployeeListController(controller);
        return this;
    }

    /**
     * Adds the Manage Employee Use Case to the application.
     * @return this builder
     */
    public AppBuilder addManageEmployeeUseCase() {
        final ManageEmployeeOutputBoundary manageEmployeeOutputBoundary = new ManageEmployeePresenter(manageEmployeeViewModel, employeeListViewModel, viewManagerModel);
        final ManageEmployeeInputBoundary manageEmployeeInteractor = new ManageEmployeeInteractor(userDataAccessObject, manageEmployeeOutputBoundary);
        final ManageEmployeeController controller = new ManageEmployeeController(manageEmployeeInteractor);
        manageEmployeeView.setManageEmployeeController(controller);
        return this;
    }

    /**
     * Adds the Schedule Shift Use Case to the application.
     * @return this builder
     */
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

    public AppBuilder addManageShiftsUseCase() {
        final ManageShiftsOutputBoundary manageShiftsOutputBoundary = new ManageShiftsPresenter(
                managerViewModel,
                manageShiftsViewModel,
                viewManagerModel
        );
        final ManageShiftsInputBoundary manageShiftsInteractor = new ManageShiftsInteractor(userDataAccessObject,
                manageShiftsOutputBoundary);
        final ManageShiftsController controller = new ManageShiftsController(manageShiftsInteractor);
        manageShiftsView.setManageShiftsController(controller);
        return this;
    }
    /**
     * Adds the Time Off Request View to the application.
     * @return this builder
     */
    public AppBuilder addTimeOffRequestView() {
        timeOffRequestViewModel = new TimeOffRequestViewModel();
        timeOffRequestView = new TimeOffRequestView(timeOffRequestViewModel);
        cardPanel.add(timeOffRequestView, timeOffRequestView.getViewName());
        return this;
    }


    public AppBuilder addViewScheduleUseCase() {
        final ScheduleOutputBoundary scheduleOutputBoundary = new SchedulePresenter(
                scheduleViewModel);
        final ScheduleInputBoundary scheduleInteractor = new ScheduleInteractor(
                userDataAccessObject,
                scheduleOutputBoundary,
                workWeekFactory);
        final ScheduleController controller = new ScheduleController(scheduleInteractor);
        scheduleView.setScheduleController(controller);
        scheduleViewModel.getState().setScheduleController(controller);
        return this;
    }

    /**
     * Adds the Welcome Use Case to the application.
     * @return this builder
     */
    public AppBuilder addExportCalendarUseCase() {
        final ExportCalendarOutputBoundary exportCalendarOutputBoundary = new ExportCalendarPresenter(viewManagerModel,
                exportCalendarViewModel);
        final ExportCalendarInputBoundary exportCalendarInteractor = new ExportCalendarInteractor(
                userDataAccessObject, exportCalendarOutputBoundary);

        final ExportCalendarController exportCalendarController = new ExportCalendarController(exportCalendarInteractor);
        scheduleView.setExportCalendarController(exportCalendarController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("HR System");
        application.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(welcomeView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }

    /**
     * Adds the Time Off Request Use Case to the application.
     * @return this builder
     */
    public AppBuilder addTimeOffRequestUseCase() {
        final TimeOffRequestOutputBoundary timeOffRequestOutputBoundary = new TimeOffRequestPresenter(timeOffRequestViewModel);
        final TimeOffRequestDataAccessObject timeOffRequestDataAccessObject = new TimeOffRequestDataAccessObject(); // Correct type here
        final TimeOffRequestInputBoundary timeOffRequestInteractor = new TimeOffRequestInteractor(timeOffRequestDataAccessObject, timeOffRequestOutputBoundary);
        final TimeOffRequestController controller = new TimeOffRequestController(timeOffRequestInteractor);
        timeOffRequestView.setTimeOffRequestController(controller);
        return this;
    }




}
