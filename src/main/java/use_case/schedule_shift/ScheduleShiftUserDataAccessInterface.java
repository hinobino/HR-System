package use_case.schedule_shift;

import entity.Employee;
import entity.Shift;
import entity.Workday;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DAI for the ScheduleShift use case.
 */
public interface ScheduleShiftUserDataAccessInterface {
    /**
     * Save a given Shift in its Employee's shift instance variable.
     * @param newShift the Shift to save.
     */
    void save(Shift newShift);

    /**
     * Check if a User of the given ID exists.
     * @param userID the userID in question.
     * @return true if a User object with the userID specified exists.
     */
    boolean existsByName(String userID);

    /**
     * Check if a workday taking place on the given day exists or not.
     * @param day the date of the workday as a LocalDate object
     * @return true if a workday exists on the given date already.
     */
    boolean workdayExists(LocalDate day);

    /**
     * Find the workday with the given date.
     * @param day the date of the workday as a LocalDate object.
     * @return the Workday taking place on the given date or null if no such workday exists.
     */
    Workday getWorkdayByDate(LocalDate day);

    /**
     * Add the given shift to the given Workday.
     * @param newShift the shift.
     * @param workday the workday.
     */
    void addShiftToWorkday(Shift newShift, Workday workday);
}
