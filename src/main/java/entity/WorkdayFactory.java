package entity;

import java.util.Map;

/**
 * Factory for creating Workday objects.
 */
public class WorkdayFactory {

    /**
     * Create an empty Workday.
     * @return a Workday object.
     */
    public Workday create() {
        return new Workday();
    }

    /**
     * Create a new Workday.
     * @param shifts the shifts already scheduled for the Workday.
     * @return a Workday object.
     */
    public Workday create(Map<String, Shift> shifts) {
        return new Workday(shifts);
    }

}