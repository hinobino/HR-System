package use_case;

import data_access.InMemoryUserDataAccessObject;
import entity.Shift;
import entity.WorkWeek;
import entity.WorkWeekFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.view_schedule.*;

import java.awt.Container;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ScheduleInteractorTest {

    private ScheduleUserDataAccessInterface userDataAccessObject;

    private Container testContainer;
    private List<Shift> testShifts;
    private WorkWeekFactory testWorkWeekFactory;

    private WorkWeek testWorkWeek;

    @BeforeEach
    void setUp() {
        userDataAccessObject = new InMemoryUserDataAccessObject();

        testContainer = new Container();
        testShifts = Collections.emptyList();
        testWorkWeekFactory = new WorkWeekFactory();
        testWorkWeek = testWorkWeekFactory.create(LocalDate.now(), testShifts);
    }

    @Test
    void successShowStartWeekTest() {
        ScheduleInputData inputData = new ScheduleInputData(testContainer, testWorkWeek, testShifts);

        ScheduleOutputBoundary schedulePresenter = new ScheduleOutputBoundary() {
            @Override
            public void showStartWeek(ScheduleOutputData outputData) {
                assertEquals(testContainer, outputData.getWeekContainer());
                assertEquals(testWorkWeek, outputData.getWorkWeek());
                assertEquals(testShifts, outputData.getShifts());
            }

            @Override
            public void showPreviousWeek(ScheduleOutputData outputData) {
                fail("Use case showPreviousWeek is unexpected.");
            }

            @Override
            public void showNextWeek(ScheduleOutputData outputData) {
                fail("Use case showNextWeek is unexpected.");
            }
        };

        ScheduleInteractor interactor = new ScheduleInteractor(userDataAccessObject, schedulePresenter, testWorkWeekFactory);
        interactor.showStartWeek(inputData);
    }

    @Test
    void successShowPreviousWeekTest() {
        WorkWeek currentWorkWeek = testWorkWeekFactory.create(LocalDate.now(), testShifts);

        LocalDate expectedStartDate = currentWorkWeek.getStartOfWeek().plusWeeks(-1);
        WorkWeek previousWorkWeek = testWorkWeekFactory.create(expectedStartDate, testShifts);

        ScheduleInputData inputData = new ScheduleInputData(testContainer, currentWorkWeek, testShifts);

        ScheduleOutputBoundary schedulePresenter = new ScheduleOutputBoundary() {
            @Override
            public void showStartWeek(ScheduleOutputData outputData) {
                fail("Use case showStartWeek is unexpected.");
            }

            @Override
            public void showPreviousWeek(ScheduleOutputData outputData) {
                assertEquals(testContainer, outputData.getWeekContainer());
                assertEquals(previousWorkWeek.getStartOfWeek(), outputData.getWorkWeek().getStartOfWeek());
                assertEquals(previousWorkWeek.getShifts(), outputData.getWorkWeek().getShifts());
                assertEquals(testShifts, outputData.getShifts());
            }

            @Override
            public void showNextWeek(ScheduleOutputData outputData) {
                fail("Use case showNextWeek is unexpected.");
            }
        };

        ScheduleInteractor interactor = new ScheduleInteractor(userDataAccessObject, schedulePresenter, testWorkWeekFactory);
        interactor.showPreviousWeek(inputData);
    }

    @Test
    void successShowNextWeekTest() {
        WorkWeek currentWorkWeek = testWorkWeekFactory.create(LocalDate.now(), testShifts);

        LocalDate expectedStartDate = currentWorkWeek.getStartOfWeek().plusWeeks(1);
        WorkWeek nextWorkWeek = testWorkWeekFactory.create(expectedStartDate, testShifts);

        ScheduleInputData inputData = new ScheduleInputData(testContainer, currentWorkWeek, testShifts);

        ScheduleOutputBoundary schedulePresenter = new ScheduleOutputBoundary() {
            @Override
            public void showStartWeek(ScheduleOutputData outputData) {
                fail("Use case showStartWeek is unexpected.");
            }

            @Override
            public void showPreviousWeek(ScheduleOutputData outputData) {
                fail("Use case showPreviousWeek is unexpected.");
            }

            @Override
            public void showNextWeek(ScheduleOutputData outputData) {
                assertEquals(testContainer, outputData.getWeekContainer());
                assertEquals(nextWorkWeek.getStartOfWeek(), outputData.getWorkWeek().getStartOfWeek());
                assertEquals(nextWorkWeek.getShifts(), outputData.getWorkWeek().getShifts());
                assertEquals(testShifts, outputData.getShifts());
            }
        };

        ScheduleInteractor interactor = new ScheduleInteractor(userDataAccessObject, schedulePresenter, testWorkWeekFactory);
        interactor.showNextWeek(inputData);
    }
}
