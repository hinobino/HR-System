package use_case;

import data_access.InMemoryUserDataAccessObject;
import entity.Employee;
import entity.EmployeeFactory;
import interface_adapter.activate_account.ActivateAccountViewModel;
import org.junit.jupiter.api.Test;
import use_case.activate_account.*;
import view.ActivateAccountView;

import static org.junit.jupiter.api.Assertions.*;

public class ActivateAccountInteractorTest {

    @Test
    void successTest() {
        // create user input data
        ActivateAccountViewModel tempViewModel = new ActivateAccountViewModel();
        ActivateAccountView tempView = new ActivateAccountView(tempViewModel);
        ActivateAccountInputData inputData = new ActivateAccountInputData("employee1", "password", "password", tempView);

        ActivateAccountUserDataAccessInterface userDataAccessInterface = new InMemoryUserDataAccessObject();

        // when activating, user should already exist
        EmployeeFactory employeeFactory = new EmployeeFactory();
        Employee employee = employeeFactory.create("employee1", "");
        userDataAccessInterface.save(employee);

        ActivateAccountOutputBoundary activatePresenter = new ActivateAccountOutputBoundary() {

            @Override
            public void prepareSuccessView(ActivateAccountOutputData user) {
                assertEquals("password", employee.getPassword());
                assertEquals("active", employee.getStatus());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected. Reason: " + errorMessage);
            }

            @Override
            public void switchToLoginView() {

            }

            @Override
            public void switchToWelcomeView() {

            }
        };

        ActivateAccountInputBoundary interactor = new ActivateAccountInteractor(userDataAccessInterface, activatePresenter, new EmployeeFactory());
        interactor.execute(inputData);
    }

    @Test
    void failureUserActivatedTest() {
        ActivateAccountViewModel tempViewModel = new ActivateAccountViewModel();
        ActivateAccountView tempView = new ActivateAccountView(tempViewModel);
        ActivateAccountInputData inputData = new ActivateAccountInputData("employee2", "password", "password", tempView);

        ActivateAccountUserDataAccessInterface userDataAccessInterface = new InMemoryUserDataAccessObject();

        // when activating, user should already exist
        EmployeeFactory employeeFactory = new EmployeeFactory();
        Employee employee = employeeFactory.create("employee2", "password");
        userDataAccessInterface.save(employee);

        // set status to already activated
        userDataAccessInterface.activateUser("employee2", "password");

        ActivateAccountOutputBoundary activatePresenter = new ActivateAccountOutputBoundary() {

            @Override
            public void prepareSuccessView(ActivateAccountOutputData outputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("User has already been activated.", errorMessage);
            }

            @Override
            public void switchToLoginView() {

            }

            @Override
            public void switchToWelcomeView() {

            }
        };

        ActivateAccountInputBoundary interactor = new ActivateAccountInteractor(userDataAccessInterface, activatePresenter, new EmployeeFactory());
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordMismatchTest() {
        // create user input data
        ActivateAccountViewModel tempViewModel = new ActivateAccountViewModel();
        ActivateAccountView tempView = new ActivateAccountView(tempViewModel);
        ActivateAccountInputData inputData = new ActivateAccountInputData("employee1", "password", "wrong", tempView);

        ActivateAccountUserDataAccessInterface userDataAccessInterface = new InMemoryUserDataAccessObject();

        // when activating, user should already exist
        EmployeeFactory employeeFactory = new EmployeeFactory();
        Employee employee = employeeFactory.create("employee1", "");
        userDataAccessInterface.save(employee);

        ActivateAccountOutputBoundary activatePresenter = new ActivateAccountOutputBoundary() {

            @Override
            public void prepareSuccessView(ActivateAccountOutputData outputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Passwords don't match.", errorMessage);
            }

            @Override
            public void switchToLoginView() {

            }

            @Override
            public void switchToWelcomeView() {

            }
        };

        ActivateAccountInputBoundary interactor = new ActivateAccountInteractor(userDataAccessInterface, activatePresenter, new EmployeeFactory());
        interactor.execute(inputData);
    }
}


