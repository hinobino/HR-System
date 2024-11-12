package use_case.signup;

import entity.*;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
//    private final UserFactory userFactory;
    private final EmployeeFactory employeeFactory;
    private final ManagerFactory managerFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            EmployeeFactory employeeFactory, ManagerFactory managerFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
//        this.userFactory = userFactory;
        this.employeeFactory = employeeFactory;
        this.managerFactory = managerFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        // TODO: we use signupInputData.getUserID() and signupInputData.getUserID().charAt(0)
        //  quite often... maybe make variables?
        if (userDataAccessObject.existsByName(signupInputData.getUserID())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        // TODO: There may be a better way to implement this, since we are checking the first char
        //  twice, first time here and the other time in the last else..
        else if ("".equals(signupInputData.getUserID()) || (
                'e' != (Character.toLowerCase(signupInputData.getUserID().charAt(0))) &&
                'm' != (Character.toLowerCase(signupInputData.getUserID().charAt(0))))) {
            userPresenter.prepareFailView("Please enter a valid User ID.");
        }
        else if ("".equals(signupInputData.getPassword())) {
            userPresenter.prepareFailView("Please enter a valid password.");
        }

        else {
            User user;

            // TODO: This if is temporary, since we are going to make it so that only Managers can
            //  create Employees
            if ('e' == (Character.toLowerCase(signupInputData.getUserID().charAt(0)))) {
                user = employeeFactory.create(signupInputData.getUserID(), signupInputData.getPassword());
            }
//            else if ('m' == (Character.toLowerCase(signupInputData.getUserID().charAt(0)))) {

            // else if above is technically unnecessary, because at this point the first char is
            // either 'e' or 'm', so if not 'e', then 'm' is implied
            // if the else if is used, then we need to initialize User user = null, which, although
            // we are guaranteed to replace it, it's not ideal imo
            else { // 'm' == first char
                user = managerFactory.create(signupInputData.getUserID(), signupInputData.getPassword());
            }

//            final User user = userFactory.create(signupInputData.getUserID(), signupInputData.getPassword());
            userDataAccessObject.save(user);

            final SignupOutputData signupOutputData = new SignupOutputData(user.getUserID(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() { userPresenter.switchToLoginView(); }

    @Override
    public void switchToWelcomeView() { userPresenter.switchToWelcomeView(); }
}
