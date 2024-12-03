package entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWeekFactory {

    public WorkWeek create(LocalDate date, List<Shift> shifts) {
        return new WorkWeek(date, shifts);
    }
}
