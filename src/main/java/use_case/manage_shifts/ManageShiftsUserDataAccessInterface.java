package use_case.manage_shifts;

import entity.Workday;

import java.time.LocalDate;

public interface ManageShiftsUserDataAccessInterface {

    /**
     * Find the workday with the given date.
     * @param day the date of the workday as a LocalDate object.
     * @return the Workday taking place on the given date or null if no such workday exists.
     */
    Workday getWorkdayByDate(LocalDate day);

}
