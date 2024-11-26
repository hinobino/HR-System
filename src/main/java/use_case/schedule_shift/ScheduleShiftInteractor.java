package use_case.schedule_shift;

import data_access.PublicHolidayAPIAccessObject;
import entity.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Interactor for the ScheduleShift use case.
 */
public class ScheduleShiftInteractor implements ScheduleShiftInputBoundary {

    private final ScheduleShiftUserDataAccessInterface userDataAccessObject;
    private final PublicHolidayAPIAccessObject publicHolidayAPIAccessObject = new PublicHolidayAPIAccessObject("CA");

    private final ScheduleShiftOutputBoundary scheduleShiftPresenter;

    private final ShiftFactory shiftFactory;
    private final WorkdayFactory workdayFactory;

    public ScheduleShiftInteractor(ScheduleShiftUserDataAccessInterface userDataAccessObject,
                                   ScheduleShiftOutputBoundary scheduleShiftPresenter, ShiftFactory shiftFactory,
                                   WorkdayFactory workdayFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.scheduleShiftPresenter = scheduleShiftPresenter;
        this.shiftFactory = shiftFactory;
        this.workdayFactory = workdayFactory;
    }

    @Override
    public void execute(ScheduleShiftInputData scheduleShiftInputData) {
        final LocalDate day = scheduleShiftInputData.getDay();
        final LocalTime startTime = scheduleShiftInputData.getStartTime();
        final LocalTime endTime = scheduleShiftInputData.getEndTime();
        final Employee employee = scheduleShiftInputData.getEmployee();

        // TODO: Implement checks for pre-approved leave
        // Ensure fields not blank
        if (day == null || startTime == null || endTime == null || employee == null) {
            scheduleShiftPresenter.prepareFailView("Please fill in all fields.");
        }

        // Ensure the scheduled takes place at least one day in the future
        else if (day.isBefore(LocalDate.now().plusDays(1))) {
            scheduleShiftPresenter.prepareFailView("The shift must take place tomorrow or later.");
        }

        // Ensure the shift takes place between 9am and 5pm
        else if (startTime.isBefore(LocalTime.of(9, 0))) {
            scheduleShiftPresenter.prepareFailView("The shift must start at or after 9am.");
        }
        else if (endTime.isAfter(LocalTime.of(17,0))) {
            scheduleShiftPresenter.prepareFailView("The shift must end by 5pm.");
        }

        // Ensure the start time is no later than the end time
        else if (!startTime.isBefore(endTime)) {
            scheduleShiftPresenter.prepareFailView("The start time must be after the end time.");
        }

        // Ensure the employee exists
        else if (!userDataAccessObject.existsByName(employee.getUserID())) {
            scheduleShiftPresenter.prepareFailView("An employee with that user ID does not exist.");
        }

        // Ensure not on a public holiday
//        else if (publicHolidayAPIAccessObject.holidayOn(day)) {
//            scheduleShiftPresenter.prepareFailView(
//                    "That day is a public holiday; no shifts may be scheduled."
//            );
//        }

        else {
            // Create a new shift with the given data
            Shift newShift = shiftFactory.create(day, startTime, endTime, employee);
            userDataAccessObject.save(newShift);

            // If a workday for the current date exists already, add this shift to it
            if (userDataAccessObject.workdayExists(day)) {
                Workday workday = userDataAccessObject.getWorkdayByDate(day);

                // Make sure the given employee is not already scheduled for a shift that day
                if (workday.isWorking(employee)) {
                    scheduleShiftPresenter.prepareFailView("Employee already has shift scheduled for this" +
                            " day");
                }
                else {
                    userDataAccessObject.addShiftToWorkday(newShift, workday);
                }
            }

            // Otherwise, create a workday and add the shift.
            else {
                Workday workday = workdayFactory.create();
                userDataAccessObject.addShiftToWorkday(newShift, workday);
            }

            // Success state
            ScheduleShiftOutputData outputData = new ScheduleShiftOutputData(day, startTime, endTime, employee, false);
            scheduleShiftPresenter.prepareSuccessView(outputData);
        }
    }

    @Override
    public void returnToManagerView() {
        scheduleShiftPresenter.returnToManagerView();
    }

}
