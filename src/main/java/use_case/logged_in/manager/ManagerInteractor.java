package use_case.logged_in.manager;

import data_access.InMemoryUserDataAccessObject;
import entity.Employee;
import entity.Shift;
import entity.WorkWeek;
import entity.WorkWeekFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Manager Interactor.
 */
public class ManagerInteractor implements ManagerInputBoundary {

    private final InMemoryUserDataAccessObject userDataAccessObject;
    private final ManagerOutputBoundary managerOutputBoundary;
    private final WorkWeekFactory workWeekFactory;

    public ManagerInteractor(InMemoryUserDataAccessObject userDataAccessObject,
                             ManagerOutputBoundary managerOutputBoundary,
                             WorkWeekFactory workWeekFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.managerOutputBoundary = managerOutputBoundary;
        this.workWeekFactory = workWeekFactory;
    }

    @Override
    public void switchToCreateEmployeeView() {
        managerOutputBoundary.switchToCreateEmployeeView();
    }

    @Override
    public void switchToEmployeeListView() {
        ManagerOutputData outputData = new ManagerOutputData(
                userDataAccessObject.getEmployeesByManager(userDataAccessObject.getCurrentUserID())
        );
        managerOutputBoundary.switchToEmployeeListView(outputData);
    }

    public void switchToScheduleShiftView() {
        ManagerOutputData outputData = new ManagerOutputData(
                userDataAccessObject.getEmployeesByManager(userDataAccessObject.getCurrentUserID())
        );
        managerOutputBoundary.switchToScheduleShiftView(outputData);
    }

    @Override
    public void switchToManageShiftsView() {
        ManagerOutputData outputData = new ManagerOutputData(
                userDataAccessObject.getEmployeesByManager(userDataAccessObject.getCurrentUserID())
        );
        managerOutputBoundary.switchToManageShiftsView(outputData);
    }

    public void openScheduleView(ManagerInputData managerInputData) {
        List<Shift> shifts = new ArrayList<>();
        for (String employeeID : userDataAccessObject.getEmployeesByManager(userDataAccessObject.getCurrentUserID()).keySet()) {
            shifts.addAll(userDataAccessObject.getShifts(employeeID));
        }

        LocalDate currentWeekDate = LocalDate.now();
        List<WorkWeek> weeks = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            WorkWeek week = workWeekFactory.create(currentWeekDate.plusWeeks(i), shifts);
            weeks.add(week);
        }
        ManagerOutputData managerOutputData = new ManagerOutputData(
                managerInputData.getLoggedInState(),
                userDataAccessObject.getEmployeesByManager(userDataAccessObject.getCurrentUserID()),
                weeks);

        managerOutputBoundary.openScheduleView(managerOutputData);
    }
}
