package use_case;

import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.schedule_shift.*;
import use_case.schedule_shift.ScheduleShiftInteractor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleShiftInteractorTest {

    private ScheduleShiftUserDataAccessInterface repository;

    private EmployeeFactory employeeFactory;
    private ManagerFactory managerFactory;
    private ShiftFactory shiftFactory;
    private WorkdayFactory workdayFactory;

    private Employee testEmployee;
    private Manager testManager;

    @BeforeEach
    void init() {
        repository = new InMemoryUserDataAccessObject();

        employeeFactory = new EmployeeFactory();
        managerFactory = new ManagerFactory();
        shiftFactory = new ShiftFactory();
        workdayFactory = new WorkdayFactory();

        testEmployee = employeeFactory.create("bob", "hi");
        testManager = managerFactory.create("alice", "bye");
        repository.save(testEmployee);
    }

    @Test
    void successTest() {
        LocalDate validDate = LocalDate.of(2024, 12, 5);
        LocalTime validStartTime = LocalTime.of(9, 0);
        LocalTime validEndTime = LocalTime.of(16, 0);

        ScheduleShiftInputData inputData = new ScheduleShiftInputData(validDate, validStartTime, validEndTime,
                testEmployee);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                assertEquals(outputData.getDay(), validDate);
                assertEquals(outputData.getStartTime(), validStartTime);
                assertEquals(outputData.getEndTime(), validEndTime);
                assertEquals(outputData.getEmployee(), testEmployee);

                assertNotNull(repository.getWorkdayByDate(validDate));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                System.out.println(errorMessage);
                fail("unexpected failure of use case.");
            }

            @Override
            public void returnToManagerView() {

            }
        };

        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        scheduleShiftInteractor.execute(inputData);
    }

    @Test
    void failureNullEmployee() {
        LocalDate validDate = LocalDate.of(2024, 12, 5);
        LocalTime validStart = LocalTime.of(9, 0);
        LocalTime validEnd = LocalTime.of(16, 0);
        ScheduleShiftInputData inputData = new ScheduleShiftInputData(validDate, validStart, validEnd,
                null);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                fail("unexpected success");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Please fill in all fields.", errorMessage);
            }

            @Override
            public void returnToManagerView() {

            }
        };
        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        scheduleShiftInteractor.execute(inputData);
    }

    @Test
    void failureNullEndTime() {
        LocalDate validDate = LocalDate.of(2024, 12, 5);
        LocalTime validTime = LocalTime.of(9, 30);
        ScheduleShiftInputData inputData = new ScheduleShiftInputData(validDate, validTime, null,
                testEmployee);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                fail("unexpected success");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Please fill in all fields.", errorMessage);
            }

            @Override
            public void returnToManagerView() {

            }
        };
        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        scheduleShiftInteractor.execute(inputData);
    }

    @Test
    void failureNullStartTime() {
        LocalDate validDate = LocalDate.of(2024, 12, 5);
        ScheduleShiftInputData inputData = new ScheduleShiftInputData(validDate, null, null,
                testEmployee);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                fail("unexpected success");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Please fill in all fields.", errorMessage);
            }

            @Override
            public void returnToManagerView() {

            }
        };
        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        scheduleShiftInteractor.execute(inputData);
    }

    @Test
    void failureNullDate() {
        ScheduleShiftInputData inputData = new ScheduleShiftInputData(null, null, null,
                testEmployee);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                fail("unexpected success");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Please fill in all fields.", errorMessage);
            }

            @Override
            public void returnToManagerView() {

            }
        };
        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        scheduleShiftInteractor.execute(inputData);
    }

    @Test
    void failurePastDate() {
        LocalDate invalidDate = LocalDate.of(2023, 12, 5);
        LocalTime validStartTime = LocalTime.of(9, 0);
        LocalTime validEndTime = LocalTime.of(16, 0);
        ScheduleShiftInputData inputData = new ScheduleShiftInputData(
                invalidDate, validStartTime, validEndTime,
                testEmployee);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                fail("unexpected success");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("The shift must take place tomorrow or later.", errorMessage);
            }

            @Override
            public void returnToManagerView() {

            }
        };
        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        scheduleShiftInteractor.execute(inputData);
    }

    @Test
    void failureEarlyStartTime() {
        LocalDate validDate = LocalDate.of(2024, 12, 5);
        LocalTime invalidStartTime = LocalTime.of(8, 0);
        LocalTime validEndTime = LocalTime.of(16, 0);
        ScheduleShiftInputData inputData = new ScheduleShiftInputData(
                validDate, invalidStartTime, validEndTime,
                testEmployee);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                fail("unexpected success");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("The shift must start at or after 9am.", errorMessage);
            }

            @Override
            public void returnToManagerView() {

            }
        };
        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        scheduleShiftInteractor.execute(inputData);
    }

    @Test
    void failureLateEndTime() {
        LocalDate validDate = LocalDate.of(2024, 12, 5);
        LocalTime validStartTime = LocalTime.of(9, 0);
        LocalTime invalidEndTime = LocalTime.of(19, 0);
        ScheduleShiftInputData inputData = new ScheduleShiftInputData(
                validDate, validStartTime, invalidEndTime,
                testEmployee);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                fail("unexpected success");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("The shift must end by 5pm.", errorMessage);
            }

            @Override
            public void returnToManagerView() {

            }
        };
        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        scheduleShiftInteractor.execute(inputData);
    }

    @Test
    void failureStartTimeAfterEndTime() {
        LocalDate validDate = LocalDate.of(2024, 12, 5);
        LocalTime invalidStartTime = LocalTime.of(17, 0);
        LocalTime validEndTime = LocalTime.of(16, 0);
        ScheduleShiftInputData inputData = new ScheduleShiftInputData(
                validDate, invalidStartTime, validEndTime,
                testEmployee);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                fail("unexpected success");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("The start time must be after the end time.", errorMessage);
            }

            @Override
            public void returnToManagerView() {

            }
        };
        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        scheduleShiftInteractor.execute(inputData);
    }

    @Test
    void failureEmployeeIDNonexistend() {
        LocalDate validDate = LocalDate.of(2024, 12, 5);
        LocalTime validStartTime = LocalTime.of(9, 0);
        LocalTime validEndTime = LocalTime.of(16, 0);

        Employee invalidEmployee = employeeFactory.create("alexander", "hello");

        ScheduleShiftInputData inputData = new ScheduleShiftInputData(
                validDate, validStartTime, validEndTime,
                invalidEmployee);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                fail("unexpected success");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("An employee with that user ID does not exist.", errorMessage);
            }

            @Override
            public void returnToManagerView() {

            }
        };
        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        scheduleShiftInteractor.execute(inputData);
    }

    @Test
    void failurePublicHoliday() {
        LocalDate invalidDate = LocalDate.of(2024, 12, 25);
        LocalTime validStartTime = LocalTime.of(9, 0);
        LocalTime validEndTime = LocalTime.of(16, 0);

        ScheduleShiftInputData inputData = new ScheduleShiftInputData(invalidDate, validStartTime, validEndTime,
                testEmployee);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                fail("unexpected success.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("That day is a public holiday; no shifts may be scheduled.", errorMessage);
            }

            @Override
            public void returnToManagerView() {

            }
        };

        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        // Sleep to avoid excess api calls per second
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        scheduleShiftInteractor.execute(inputData);
    }

    @Test
    void failureUserAlreadyScheduled() {
        LocalDate validDate = LocalDate.of(2024, 12, 5);
        LocalTime validStartTime = LocalTime.of(9, 0);
        LocalTime validEndTime = LocalTime.of(16, 0);

        ScheduleShiftInputData inputData = new ScheduleShiftInputData(validDate, validStartTime, validEndTime,
                testEmployee);

        Shift newShift = shiftFactory.create(
                validDate,
                validStartTime,
                validEndTime,
                testEmployee
        );
        Workday workday = workdayFactory.create();
        repository.addShiftToWorkday(newShift, workday);
        repository.save(newShift);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                fail("unexpected success.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Employee already has shift scheduled for this day", errorMessage);
            }

            @Override
            public void returnToManagerView() {

            }
        };

        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        scheduleShiftInteractor.execute(inputData);
    }

    @Test
    void successViewSwitched() {
        LocalDate validDate = LocalDate.of(2024, 12, 5);
        LocalTime validStartTime = LocalTime.of(9, 0);
        LocalTime validEndTime = LocalTime.of(16, 0);

        ScheduleShiftInputData inputData = new ScheduleShiftInputData(validDate, validStartTime, validEndTime,
                testEmployee);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                assertEquals(outputData.getDay(), validDate);
                assertEquals(outputData.getStartTime(), validStartTime);
                assertEquals(outputData.getEndTime(), validEndTime);
                assertEquals(outputData.getEmployee(), testEmployee);

                assertNotNull(repository.getWorkdayByDate(validDate));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                System.out.println(errorMessage);
                fail("unexpected failure of use case.");
            }

            @Override
            public void returnToManagerView() {

            }
        };

        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        scheduleShiftInteractor.returnToManagerView();
    }

    @Test
    void successWithWorkdayAlreadyExisting() {
        LocalDate validDate = LocalDate.of(2024, 12, 5);
        LocalTime validStartTime = LocalTime.of(9, 0);
        LocalTime validEndTime = LocalTime.of(16, 0);

        ScheduleShiftInputData inputData = new ScheduleShiftInputData(validDate, validStartTime, validEndTime,
                testEmployee);

        Workday workday = workdayFactory.create();
        repository.saveWorkday(validDate, workday);

        ScheduleShiftOutputBoundary scheduleShiftPresenter = new ScheduleShiftOutputBoundary() {
            @Override
            public void prepareSuccessView(ScheduleShiftOutputData outputData) {
                assertEquals(outputData.getDay(), validDate);
                assertEquals(outputData.getStartTime(), validStartTime);
                assertEquals(outputData.getEndTime(), validEndTime);
                assertEquals(outputData.getEmployee(), testEmployee);

                assertNotNull(repository.getWorkdayByDate(validDate));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                System.out.println(errorMessage);
                fail("unexpected failure of use case.");
            }

            @Override
            public void returnToManagerView() {

            }
        };

        ScheduleShiftInputBoundary scheduleShiftInteractor = new ScheduleShiftInteractor(
                repository,
                scheduleShiftPresenter,
                shiftFactory,
                workdayFactory
        );
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        scheduleShiftInteractor.execute(inputData);
    }

}
