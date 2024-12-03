package use_case.logged_in.employee;

import entity.Shift;
import entity.WorkWeek;
import entity.WorkWeekFactory;
import view.ScheduleWeekView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeInteractor implements EmployeeInputBoundary {
    private final InMemoryUserDataAccessObject userDataAccessObject;
    private final EmployeeOutputBoundary employeeOutputBoundary;
    private final WorkWeekFactory workWeekFactory;

    public EmployeeInteractor(EmployeeUserDataAccessObject userDataAccessObject,
                              EmployeeOutputBoundary employeeOutputBoundary,
                              WorkWeekFactory workWeekFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.employeeOutputBoundary = employeeOutputBoundary;
        this.workWeekFactory = workWeekFactory;
    }

    @Override
    public void openScheduleView(EmployeeInputData employeeInputData) {
        List<Shift> shifts = userDataAccessObject.getShifts(userDataAccessObject.getCurrentUserID());

        LocalDate currentWeekDate = LocalDate.now();
        List<WorkWeek> weeks = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            WorkWeek week = workWeekFactory.create(currentWeekDate.plusWeeks(i), shifts);
            weeks.add(week);
        }
        EmployeeOutputData employeeOutputData = new EmployeeOutputData(
                employeeInputData.getLoggedInState(),
                userDataAccessObject.getShifts(userDataAccessObject.getCurrentUserID()),
                weeks);

        employeeOutputBoundary.openScheduleView(employeeOutputData);
    }
}
